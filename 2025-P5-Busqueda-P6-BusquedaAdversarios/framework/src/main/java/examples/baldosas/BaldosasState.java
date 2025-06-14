package examples.baldosas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import conventionalsearch.State;

public class BaldosasState implements State {
  private BaldosasState parent = null;
  private int xFree;
  private int yFree;
  private int[][] matrix;
  private int rowColSize = 4;

  public boolean repOK() {
    for (int i = 0; i < rowColSize; i++) {
      for (int j = 0; j < rowColSize; j++) {
        if (matrix[i][j] >= 16 || matrix[i][j] < 0) {
          return false;
        }
      }
    }
    return xFree >= 0 && xFree <= 3 && yFree >= 0 && yFree <= 3;
  }

  /**
   * Constructor for BaldosasState class.
   * Instanciate a random matrix where params (x, y) is zero
   * and the rest of the matrix is shuffled with numbers from 1 to 15.
   */
  public BaldosasState(int x, int y) {
    if (x < 0 || x >= rowColSize || y < 0 || y >= rowColSize) {
      throw new IllegalArgumentException("Coords out of range: (x, y) = (" + x + ", " + y + ")");
    }
    xFree = x;
    yFree = y;
    matrix = new int[rowColSize][rowColSize];

    List<Integer> numbers = new ArrayList<>();
    for (int i = 0; i < rowColSize * rowColSize; i++) {
      numbers.add(i);
    }
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
    if (!repOK()) {
      throw new UnsupportedOperationException("Cannot create BaldosasState: repOK was not satisfied");
    }
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
   * Setters
   */
  public void setXFree(int x) {
    xFree = x;
  }

  public void setYFree(int y) {
    yFree = y;
  }

  /**
   * This State is field by field copied.
   *
   * @return a copy of this BaldosasState.
   */
  public BaldosasState clone() {
    BaldosasState clon = new BaldosasState(0, 0);
    clon.xFree = this.xFree;
    clon.yFree = this.yFree;
    clon.parent = this.parent;
    for (int i = 0; i < rowColSize; i++) {
      clon.matrix[i] = this.matrix[i].clone();
    }
    return clon;
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

  /**
   * Set the parent of the current state. This method
   * must be implemented by all concrete classes implementing State.
   *
   * @param parent to be set to the current state.
   */
  public void setParent(BaldosasState parent) {
    this.parent = parent;
  }

  public int[][] getMatrix() {
    return matrix;
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

    BaldosasState other = (BaldosasState) o;

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
