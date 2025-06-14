package examples.eightqueens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import conventionalsearch.StateProblem;

public class QueensStateProblem implements StateProblem<QueensState> {
  private QueensState initial;

  public QueensStateProblem(int size) {
    this.initial = new QueensState(size);
  }

  @Override
  public QueensState initialState() {
    return this.initial;
  }

  @Override
  public List<QueensState> getSuccessors(QueensState s) {
    List<QueensState> successors = new ArrayList<>();

    // Buscar la primera fila libre
    int rowToPlace = -1;
    for (int i = 0; i < s.getSize(); i++) {
      if (s.getGrid()[i] == -1) {
        rowToPlace = i;
        break;
      }
    }

    // Si ya están todas las reinas, no hay sucesores
    if (rowToPlace == -1) {
      return successors;
    }

    for (int col = 0; col < s.getSize(); col++) {
      if (!s.isAttacked(rowToPlace, col)) {
        QueensState newState = s.clone();
        newState.getGrid()[rowToPlace] = col;
        newState.setParent(s);
        successors.add(newState);
      }
    }

    return successors;
  }
}
