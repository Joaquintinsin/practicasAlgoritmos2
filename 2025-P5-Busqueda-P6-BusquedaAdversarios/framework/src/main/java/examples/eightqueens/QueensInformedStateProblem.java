package examples.eightqueens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import conventionalsearch.StateProblem;

public class QueensInformedStateProblem implements StateProblem<QueensInformedState> {
  private QueensInformedState initial;

  public QueensInformedStateProblem(int size) {
    initial = new QueensInformedState(size);
  }

  @Override
  public QueensInformedState initialState() {
    return initial;
  }

  @Override
  public List<QueensInformedState> getSuccessors(QueensInformedState s) {
    List<QueensInformedState> successors = new ArrayList<>();

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
        QueensInformedState newState = s.clone();
        newState.getGrid()[rowToPlace] = col;
        newState.setParent(s);
        successors.add(newState);
      }
    }

    return successors;
  }
}
