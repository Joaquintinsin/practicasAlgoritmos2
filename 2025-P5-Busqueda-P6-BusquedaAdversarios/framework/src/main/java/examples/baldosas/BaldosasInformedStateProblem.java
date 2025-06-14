package examples.baldosas;

import java.util.ArrayList;
import java.util.List;

import conventionalsearch.StateProblem;

public class BaldosasInformedStateProblem implements StateProblem<BaldosasInformedState> {
  private BaldosasInformedState initial;
  private int xFree;
  private int yFree;
  private int rowColSize = 4;

  /**
   * Constructor for BaldosasInformedStateProblem class.
   * Instanciate a random matrix where params (x, y) is zero
   * and the rest of the matrix is shuffled with numbers from 1 to 15.
   */
  public BaldosasInformedStateProblem(int x, int y) {
    if (x < 0 || x >= rowColSize || y < 0 || y >= rowColSize) {
      throw new IllegalArgumentException("Coords out of range: (x, y) = (" + x + ", " + y + ")");
    }
    xFree = x;
    yFree = y;
    initial = new BaldosasInformedState(x, y);
    System.out.println("constructor baldosas informed state problem... Initial:");
    System.out.println(initial.toString());
  }

  @Override
  public BaldosasInformedState initialState() {
    return initial;
  }

  @Override
  public List<BaldosasInformedState> getSuccessors(BaldosasInformedState s) {
    List<BaldosasInformedState> result = new ArrayList<BaldosasInformedState>();
    int xFree = s.getXFree();
    int yFree = s.getYFree();
    int[][] currentMatrix = s.getMatrix();

    boolean cornersFree = (xFree == 0 && yFree == 0) || (xFree == rowColSize - 1 && yFree == rowColSize - 1);
    boolean freeOnTop = (xFree == 0);
    boolean freeOnBottom = (xFree == rowColSize - 1);
    boolean freeOnLeftSide = (yFree == 0);
    boolean freeOnRightSide = (yFree == rowColSize - 1);
    boolean freeOnMid = true;

    int abajo = xFree + 1;
    int arriba = xFree - 1;
    int izquierda = yFree - 1;
    int derecha = yFree + 1;

    if (cornersFree) {
      if (xFree == 0 && yFree == 0) {
        // abajo
        result.add(createSuccessor(s, xFree + 1, yFree));
        // derecha
        result.add(createSuccessor(s, xFree, yFree + 1));
      } else {
        // arriba
        result.add(createSuccessor(s, xFree - 1, yFree));
        // izquierda
        result.add(createSuccessor(s, xFree, yFree - 1));
      }
    } else if (freeOnTop) {
      // abajo
      result.add(createSuccessor(s, xFree + 1, yFree));
      // izquierda (si no está en esquina)
      if (yFree > 0)
        result.add(createSuccessor(s, xFree, yFree - 1));
      // derecha (si no está en esquina)
      if (yFree < rowColSize - 1)
        result.add(createSuccessor(s, xFree, yFree + 1));
    } else if (freeOnBottom) {
      // arriba
      result.add(createSuccessor(s, xFree - 1, yFree));
      // izquierda (si no está en esquina)
      if (yFree > 0)
        result.add(createSuccessor(s, xFree, yFree - 1));
      // derecha (si no está en esquina)
      if (yFree < rowColSize - 1)
        result.add(createSuccessor(s, xFree, yFree + 1));
    } else if (freeOnLeftSide) {
      // arriba (si no está en esquina)
      if (xFree > 0)
        result.add(createSuccessor(s, xFree - 1, yFree));
      // abajo
      result.add(createSuccessor(s, xFree + 1, yFree));
      // derecha
      result.add(createSuccessor(s, xFree, yFree + 1));
    } else if (freeOnRightSide) {
      // arriba (si no está en esquina)
      if (xFree > 0)
        result.add(createSuccessor(s, xFree - 1, yFree));
      // abajo
      result.add(createSuccessor(s, xFree + 1, yFree));
      // izquierda
      result.add(createSuccessor(s, xFree, yFree - 1));
    } else if (freeOnMid) {
      // arriba
      result.add(createSuccessor(s, xFree - 1, yFree));
      // abajo
      result.add(createSuccessor(s, xFree + 1, yFree));
      // izquierda
      result.add(createSuccessor(s, xFree, yFree - 1));
      // derecha
      result.add(createSuccessor(s, xFree, yFree + 1));
    }
    return result;
  }

  private BaldosasInformedState createSuccessor(BaldosasInformedState original, int newX, int newY) {
    BaldosasInformedState newState = original.clone();
    int[][] matrix = newState.getMatrix();
    int xFree = newState.getXFree();
    int yFree = newState.getYFree();

    // Intercambio del 0 con la nueva posición
    matrix[xFree][yFree] = matrix[newX][newY];
    matrix[newX][newY] = 0;

    // Actualizamos la nueva posición del 0
    newState.setXFree(newX);
    newState.setYFree(newY);
    newState.setParent(original);

    // System.out.println("Moviendo 0 de (" + xFree + ", " + yFree + ") a (" + newX + ", " + newY + ")");

    return newState;
  }
}
