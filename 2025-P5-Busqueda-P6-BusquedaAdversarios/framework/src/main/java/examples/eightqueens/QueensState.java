package examples.eightqueens;

import java.util.Arrays;

import conventionalsearch.State;
import examples.baldosas.BaldosasState;

public class QueensState implements State {
  private QueensState parent = null;
  private int gridSize;
  // grid te dice donde estan las reinas segun el indexado
  // osea grid[] = {0, 1, 7}
  // significa que (0,0) (1,1) (2,7) hay reinas
  // grid[i] == -1 significa q no hay reina
  private int[] grid;

  public boolean repOK() {
    for (int v : grid) {
      if (v < -1 || v >= gridSize) {
        return false;
      }
    }
    return gridSize > 0 && gridSize == grid.length;
  }

  public QueensState(int size) {
    gridSize = size;
    grid = new int[size];
    Arrays.fill(grid, -1);
    if (!repOK()) {
      throw new IllegalArgumentException("QueenState: repOK not satisfied");
    }
  }

  public QueensState(int size, QueensState parent) {
    gridSize = size;
    grid = new int[size];
    this.parent = parent;
    Arrays.fill(grid, -1);
    if (!repOK()) {
      throw new IllegalArgumentException("QueenState: repOK not satisfied");
    }
  }

  /**
   * Getters
   */
  public int getSize() {
    return gridSize;
  }

  public int[] getGrid() {
    return grid;
  }

  @Override
  public State getParent() {
    return parent;
  }

  /**
   * Setters
   */
  public void setGrid(int[] grid) {
    this.grid = grid;
  }

  public void setSize(int newSize) {
    this.gridSize = newSize;
  }

  public void setParent(QueensState parent) {
    this.parent = parent;
  }

  public QueensState clone() {
    QueensState clon = new QueensState(gridSize);
    clon.grid = Arrays.copyOf(this.grid, this.grid.length);
    clon.parent = this.parent;
    return clon;
  }

  public boolean isSuccess() {
    for (int i = 0; i < gridSize - 1; i++) {
      for (int j = i + 1; j < gridSize; j++) {
        if (queensAreBeingAttacked(i, j)) {
          return false;
        }
      }
    }
    return true;
  }

  // este metodo asume q la reina ya esta puesta
  public boolean queensAreBeingAttacked(int i, int j) {
    return grid[i] == grid[j] || Math.abs(i - j) == Math.abs(grid[i] - grid[j]);
  }

  // revisa si poner una reina en una posicion queda atacada o no
  // no la asume como que ya esta puesta
  public boolean isAttacked(int rowToPlace, int colToPlace) {
    for (int row = 0; row < gridSize; row++) {
      int col = grid[row];
      if (col == -1)
        continue;

      // Misma columna
      if (col == colToPlace)
        return true;

      // Misma diagonal
      if (Math.abs(rowToPlace - row) == Math.abs(colToPlace - col))
        return true;
    }
    return false;
  }

  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }

    if (!(other instanceof QueensState)) {
      return false;
    }

    QueensState otherOne = (QueensState) other;

    return Arrays.equals(this.grid, otherOne.getGrid());
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(this.grid);
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder("");
    for (int i = 0; i < gridSize; i++) {
      for (int j = 0; j < gridSize; j++) {
        if (grid[i] == j) {
          s.append("|R");
        } else {
          s.append("|_");
        }
        if (j < gridSize - 1) {
          s.append(", ");
        }
      }
      s.append("\n");
    }
    return s.toString();
  }
}
