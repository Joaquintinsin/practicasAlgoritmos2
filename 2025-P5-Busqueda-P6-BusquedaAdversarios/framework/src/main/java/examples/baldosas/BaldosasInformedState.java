package examples.baldosas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import conventionalsearch.State;
import informedsearch.StateInformed;

public class BaldosasInformedState implements StateInformed {
  private BaldosasInformedState parent = null;
  private int xFree;
  private int yFree;
  private int[][] matrix;
  private int rowColSize = 4;

  // invariante de la clase, revisar q el vacio inicial esta en los limites
  // que la matriz tiene numeros del 0 al 15 y que es posible de resolver
  public boolean repOK() {
    for (int i = 0; i < rowColSize; i++) {
      for (int j = 0; j < rowColSize; j++) {
        if (matrix[i][j] >= 16 || matrix[i][j] < 0) {
          return false;
        }
      }
    }
    return isSolvable() && xFree >= 0 && xFree <= 3 && yFree >= 0 && yFree <= 3;
  }

  // metodo q revisa si la matriz se puede resolver o no
  // recontra pedida xd
  private boolean isSolvable() {
    int[] oneDArray = new int[rowColSize * rowColSize];
    int index = 0;
    int zeroRow = 0; // Fila del 0

    for (int i = 0; i < rowColSize; i++) {
      for (int j = 0; j < rowColSize; j++) {
        oneDArray[index++] = matrix[i][j];
        if (matrix[i][j] == 0) {
          zeroRow = i; // fila del 0 desde arriba
        }
      }
    }

    int inversions = 0;
    for (int i = 0; i < oneDArray.length; i++) {
      for (int j = i + 1; j < oneDArray.length; j++) {
        if (oneDArray[i] != 0 && oneDArray[j] != 0 && oneDArray[i] > oneDArray[j]) {
          inversions++;
        }
      }
    }

    int zeroRowFromBottom = rowColSize - zeroRow;

    // Regla para tableros 4x4
    if (rowColSize % 2 == 0) {
      return (inversions % 2 == 0) == (zeroRowFromBottom % 2 != 0);
    } else {
      // Para tableros impares, solo depende de paridad de inversiones
      return inversions % 2 == 0;
    }
  }

  /**
   * Computes the value of this state. If the state is a leaf
   * (end state), then this value is an exact value.
   * If the state is not an end state, then
   * this value is an approximate value. Its estimation plays a
   * crucial role in the performance of search.
   *
   * @return an integer value, representing the value of the state.
   *         @pre. this!=null.
   *         @post. an integer value, representing the value of the state.
   */
  // Funcion de valoracion segun la Distancia Manhattan...
  // tiene q ser manhattan pq la de abajo segun piezas acomodadas anda mal
  public int value() {
    int totalDistance = 0;

    for (int i = 0; i < rowColSize; i++) {
      for (int j = 0; j < rowColSize; j++) {
        int val = matrix[i][j];

        if (val != 0) {
          // La posición final esperada para val (1-based) es (val - 1) en la grilla
          int goalRow = (val - 1) / rowColSize;
          int goalCol = (val - 1) % rowColSize;

          totalDistance += Math.abs(i - goalRow) + Math.abs(j - goalCol);
        }
      }
    }

    return totalDistance;
  }

  // Funcion de valoracion segun piezas acomodadas (anda mal)

  // public int value() {
  // boolean zeroAtStart = matrix[0][0] == 0;
  // boolean zeroAtEnd = matrix[rowColSize - 1][rowColSize - 1] == 0;
  // if (zeroAtStart) {
  // return valueCasoCeroInicio();
  // } else if (zeroAtEnd) {
  // return valueCasoCeroFinal();
  // }

  // int resValue = 0;
  // for (int i = 0; i < rowColSize; i++) {
  // for (int j = 0; j < rowColSize; j++) {
  // int index = i * rowColSize + j;
  // int expectedValue = index;
  // if (matrix[i][j] == expectedValue) {
  // resValue++;
  // }
  // }
  // }

  // // si el cero no esta al inicio ni al final, el valor maximo es 15 en vez de
  // 16
  // // entonces acotamos superiormente con el 15
  // // porque por ejemplo la grilla
  // /*
  // * 1 2 3 4
  // * 5 6 0 7
  // * 8 9 10 11
  // * 12 13 14 15
  // * tiene el cero al medio y el resto esta ordenado
  // *
  // * pero para que se devuelva el valor maximo (16), el cero tiene q estar al
  // * principio
  // * o al final y el resto quedar ordenado...
  // */
  // return Math.min(resValue, 15);
  // }

  // private int valueCasoCeroInicio() {
  // int resValueOption1 = 0;
  // for (int i = 0; i < rowColSize; i++) {
  // for (int j = 0; j < rowColSize; j++) {
  // int index = i * rowColSize + j;
  // int expectedValue = index == 0 ? 0 : index;
  // if (matrix[i][j] == expectedValue) {
  // resValueOption1++;
  // }
  // }
  // }
  // return resValueOption1;
  // }

  // private int valueCasoCeroFinal() {
  // int resValueOption2 = 0;
  // for (int i = 0; i < rowColSize; i++) {
  // for (int j = 0; j < rowColSize; j++) {
  // int index = i * rowColSize + j;
  // int expectedValue = index == 15 ? 0 : index + 1;
  // if (matrix[i][j] == expectedValue) {
  // resValueOption2++;
  // }
  // }
  // }
  // return resValueOption2;
  // }

  /**
   * Constructor for BaldosasInformedState class.
   * Instanciate a random matrix where params (x, y) is zero
   * and the rest of the matrix is shuffled with numbers from 1 to 15.
   */
  public BaldosasInformedState(int x, int y) {
    if (x < 0 || x >= rowColSize || y < 0 || y >= rowColSize) {
      throw new IllegalArgumentException("Coords out of range: (x, y) = (" + x + ", " + y + ")");
    }
    xFree = x;
    yFree = y;
    matrix = new int[rowColSize][rowColSize];

    generarEstadoValido(x, y);
    if (!repOK()) {
      throw new UnsupportedOperationException("Cannot create BaldosasInformedState: repOK was not satisfied");
    }
  }

  // metodo para que el collections.shuffle me pueda dar matrices validas
  // mientras mas grande la matriz mas probable que no se pueda resolver
  // entonces como siempre me daba matrices invalidas, le pongo la restriccion
  // de que las matrices siempre sean validas
  private void generarEstadoValido(int x, int y) {
    List<Integer> numbers = new ArrayList<>();
    for (int i = 0; i < rowColSize * rowColSize; i++) {
      numbers.add(i);
    }

    do {
      Collections.shuffle(numbers);
      int index = 0;
      int xZeroTmp = 0;
      int yZeroTmp = 0;
      for (int i = 0; i < rowColSize; i++) {
        for (int j = 0; j < rowColSize; j++) {
          matrix[i][j] = numbers.get(index++);
          if (matrix[i][j] == 0) {
            xZeroTmp = i;
            yZeroTmp = j;
          }
        }
      }
      matrix[xZeroTmp][yZeroTmp] = matrix[x][y];
      matrix[x][y] = 0;
    } while (!isSolvable());
  }

  /**
   * Getters
   */
  public int getXFree() {
    return xFree;
  }

  public int getYFree() {
    return yFree;
  }

  /**
   * Returns the parent of the current state.
   *
   * @return the parent of the current state or null if this does not have a
   *         parent.
   */
  @Override
  public State getParent() {
    return parent;
  }

  public int[][] getMatrix() {
    return matrix;
  }

  /**
   * Setters
   */
  public void setXFree(int x) {
    xFree = x;
  }

  public void setYFree(int y) {
    yFree = y;
  }

  /**
   * Set the parent of the current state. This method
   * must be implemented by all concrete classes implementing State.
   *
   * @param parent to be set to the current state.
   */
  public void setParent(BaldosasInformedState parent) {
    this.parent = parent;
  }

  /**
   * This State is field by field copied.
   *
   * @return a copy of this BaldosasInformedState.
   */
  // el metodo clone cambia porque el constructor no te hace la grila tal cual,
  // sino que hace una aleatoria
  // entonces creas una clase nueva y despues clonas la matriz
  public BaldosasInformedState clone() {
    BaldosasInformedState clon = new BaldosasInformedState(0, 0);
    clon.xFree = this.xFree;
    clon.yFree = this.yFree;
    clon.parent = this.parent;
    for (int i = 0; i < rowColSize; i++) {
      clon.matrix[i] = this.matrix[i].clone();
    }
    return clon;
  }

  /**
   * Indicates whether a given state is a successful state, in the context of
   * the current problem. Concrete implementations of AbstractSearchProblem
   * must implement this routine, to indicate when the search has been
   * successful.
   *
   * @return true iff s is a successful state.
   */
  public boolean isSuccess() {
    List<Integer> matrixToArray = new ArrayList<>();
    for (int i = 0; i < rowColSize; i++) {
      for (int j = 0; j < rowColSize; j++) {
        matrixToArray.add(matrix[i][j]);
      }
    }
    boolean zeroAtStart = matrixToArray.get(0) == 0;
    boolean zeroAtEnd = matrixToArray.get(matrixToArray.size() - 1) == 0;

    if (!zeroAtStart && !zeroAtEnd) {
      return false;
    }

    List<Integer> matrixWithoutZero = new ArrayList<>(matrixToArray);
    if (zeroAtStart) {
      matrixWithoutZero.remove(0);
    } else {
      matrixWithoutZero.remove(matrixWithoutZero.size() - 1);
    }

    List<Integer> expected = new ArrayList<>();
    for (int i = 1; i < rowColSize * rowColSize; i++) {
      expected.add(i);
    }
    return matrixWithoutZero.equals(expected);
  }

  /**
   * Checks whether 'this' is equal to another state. This must be implemented
   * by every concrete class implementing State.
   *
   * @param other State object to compare with this.
   * @return true iff 'this' is equal, as a state, to 'other'.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;

    BaldosasInformedState other = (BaldosasInformedState) o;

    // Comparación profunda de matrices
    for (int i = 0; i < rowColSize; i++) {
      if (!Arrays.equals(this.matrix[i], other.matrix[i])) {
        return false;
      }
    }

    return true;
  }

  /**
   * Returns a hash code value for the the concrete State object.
   * This method is supported for the benefit of hashtables such as those
   * provided by java.util.Hashtable.
   *
   * @return a hash code value for the concrete State object.
   */
  @Override
  public int hashCode() {
    int result = 1;
    for (int i = 0; i < rowColSize; i++) {
      result = 31 * result + Arrays.hashCode(matrix[i]);
    }
    return result;
  }

  /**
   * Returns a representation as a string of the current state. This method
   * must be implemented by all concrete classes implementing State.
   *
   * @return a String representation of the current state.
   */
  @Override
  public String toString() {
    StringBuilder s = new StringBuilder("[\n");
    for (int i = 0; i < rowColSize; i++) {
      for (int j = 0; j < rowColSize; j++) {
        if (matrix[i][j] == 0) {
          s.append(" _");
        } else {
          s.append(String.format("%2d", matrix[i][j]));
        }
        if (j < rowColSize - 1) {
          s.append(", ");
        }
      }
      s.append("\n");
    }
    s.append("]");
    return s.toString();
  }
}
