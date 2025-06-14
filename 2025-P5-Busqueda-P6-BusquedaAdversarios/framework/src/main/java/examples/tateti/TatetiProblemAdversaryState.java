package examples.tateti;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import adversarysearch.StateAdversary;
import adversarysearch.StateProblemAdversary;

public class TatetiProblemAdversaryState implements StateProblemAdversary<TatetiAdversaryState> {
  private TatetiAdversaryState initialState;

  public TatetiProblemAdversaryState() {
    int[][] initialGrid = new int[3][3];
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        initialGrid[i][j] = 0;
      }
    }
    // Arrays.fill(initialGrid, 0);
    this.initialState = new TatetiAdversaryState(initialGrid, true);
  }

  public TatetiAdversaryState initialState() {
    return initialState;
  }

  public static int[][] clonarGrid(int[][] grid) {
    int[][] clone = new int[3][3];
    for (int i = 0; i < 3; i++) {
      clone[i] = Arrays.copyOf(grid[i], 3);
    }
    return clone;
  }

  public List<TatetiAdversaryState> getSuccessors(TatetiAdversaryState s) {
    int[][] sGrid = s.getGrid();
    List<TatetiAdversaryState> resultSuccessors = new ArrayList<>();
    int fichaTurno = (s.isMax()) ? 1 : -1;
    recorrerGrillaYPonerSegunTurno(sGrid, fichaTurno, s.isMax(), resultSuccessors);
    return resultSuccessors;
  }

  private void recorrerGrillaYPonerSegunTurno(int[][] originalGrid, int fichaTurno, boolean turno,
      List<TatetiAdversaryState> resultSuccessors) {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (originalGrid[i][j] == 0) {
          int[][] nuevoGrid = clonarGrid(originalGrid);
          nuevoGrid[i][j] = fichaTurno;
          TatetiAdversaryState tas = new TatetiAdversaryState(nuevoGrid, !turno);
          resultSuccessors.add(tas);
        }
      }
    }
  }

  public int minValue() {
    return -10;
  }

  public int maxValue() {
    return 10;
  }
}
