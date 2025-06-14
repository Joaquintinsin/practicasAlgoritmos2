package examples.tateti;

import java.util.Arrays;

import adversarysearch.StateAdversary;

public class TatetiAdversaryState implements StateAdversary {
  private TatetiAdversaryState parent = null;
  private boolean max = true;
  private int[][] grid;
  private int gridLength = 3;

  private int maxValue = 10;
  private int minValue = -10;

  // dos constructores uno con un estado cualquiera y el turno de quien es
  public TatetiAdversaryState(int[][] state, boolean turno) {
    this.grid = state;
    this.max = turno;
  }

  public TatetiAdversaryState(int[][] state, boolean turno, TatetiAdversaryState parent) {
    this.grid = state;
    this.max = turno;
    this.parent = parent;
  }

  public StateAdversary getParent() {
    return this.parent;
  }

  public int[][] getGrid() {
    return this.grid;
  }

  public boolean isSuccess() {
    return ganoMax();
  }

  public boolean isMax() {
    return max;
  }

  public boolean end() {
    return ganoMax() || ganoMin() || tableroLleno();
  }

  private boolean ganoMax() {
    // revisar si hay tres unos por fila
    for (int i = 0; i < gridLength; i++) {
      if (grid[i][0] == 1 && grid[i][1] == 1 && grid[i][2] == 1) {
        return true;
      }
    }
    // revisar si hay tres unos por columna
    for (int i = 0; i < gridLength; i++) {
      if (grid[0][i] == 1 && grid[1][i] == 1 && grid[2][i] == 1) {
        return true;
      }
    }
    // revisar si hay tres unos en las diagonales
    return grid[1][1] == 1 && ((grid[0][0] == 1 && grid[2][2] == 1) || (grid[2][0] == 1 && grid[0][2] == 1));
  }

  private boolean ganoMin() {
    // revisar si hay tres menosUno por fila
    for (int i = 0; i < gridLength; i++) {
      if (grid[i][0] == -1 && grid[i][1] == -1 && grid[i][2] == -1) {
        return true;
      }
    }
    // revisar si hay tres menosUno por columna
    for (int i = 0; i < gridLength; i++) {
      if (grid[0][i] == -1 && grid[1][i] == -1 && grid[2][i] == -1) {
        return true;
      }
    }
    // revisar si hay tres menosUno en las diagonales
    return grid[1][1] == -1 && ((grid[0][0] == -1 && grid[2][2] == -1) || (grid[2][0] == -1 && grid[0][2] == -1));
  }

  private boolean tableroLleno() {
    for (int i = 0; i < gridLength; i++) {
      for (int j = 0; j < gridLength; j++) {
        if (grid[i][j] == 0) {
          return false;
        }
      }
    }
    return true;
  }

  public int value() {
    if (ganoMax())
      return maxValue; // 10
    if (ganoMin())
      return minValue; // -10
    if (tableroLleno())
      return 0;

    int filasParaMin = recorrerGrillaRevisandoFilasColumasYDiagonalesSegunTurno(-1);
    int filasParaMax = recorrerGrillaRevisandoFilasColumasYDiagonalesSegunTurno(1);

    return filasParaMax - filasParaMin;
  }

  private int recorrerGrillaRevisandoFilasColumasYDiagonalesSegunTurno(int turno) {
    int result = 0;
    for (int i = 0; i < gridLength; i++) {
      if (grid[i][0] == turno && grid[i][1] == 0 && grid[i][2] == 0) {
        result++;
      }
      if (grid[0][i] == turno && grid[1][i] == 0 && grid[2][i] == 0) {
        result++;
      }
    }
    if (grid[1][1] == turno) {
      if (grid[0][0] == 0 && grid[2][2] == 0) {
        result++;
      }
      if (grid[2][0] == 0 && grid[0][2] == 0) {
        result++;
      }
    }
    return result;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }

    if (!(other instanceof TatetiAdversaryState)) {
      return false;
    }

    TatetiAdversaryState otherOne = (TatetiAdversaryState) other;

    return this.max == otherOne.isMax() && Arrays.deepEquals(this.grid, otherOne.getGrid());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < gridLength; i++) {
      for (int j = 0; j < gridLength; j++) {
        switch (grid[i][j]) {
          case 1:
            sb.append("X ");
            break;
          case -1:
            sb.append("O ");
            break;
          default:
            sb.append("- ");
        }
      }
      sb.append("\n");
    }
    return sb.toString();
  }

  public Object ruleApplied() {
    return null;
  }
}
