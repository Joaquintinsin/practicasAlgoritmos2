package examples.baldosas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import conventionalsearch.StateProblem;

public class BaldosasStateProblem implements StateProblem<BaldosasState> {
  private BaldosasState initial;
  private int xFree;
  private int yFree;
  private int rowColSize = 4;

  /**
   * Constructor for BaldosasStateProblem class.
   * Instanciate a random matrix where params (x, y) is zero
   * and the rest of the matrix is shuffled with numbers from 1 to 15.
   */
  public BaldosasStateProblem(int x, int y) {
    if (x < 0 || x >= rowColSize || y < 0 || y >= rowColSize) {
      throw new IllegalArgumentException("Coords out of range: (x, y) = (" + x + ", " + y + ")");
    }
    xFree = x;
    yFree = y;
    initial = new BaldosasState(x, y);
  }

  @Override
  public BaldosasState initialState() {
    return initial;
  }

  @Override
  public List<BaldosasState> getSuccessors(BaldosasState s) {
    BaldosasState currentState = s.clone();
    List<BaldosasState> result = new ArrayList<BaldosasState>();
    int xFree = currentState.getXFree();
    int yFree = currentState.getYFree();
    int[][] currentMatrix = currentState.getMatrix();

    boolean cornersFree =
      (xFree == 0 && yFree == 0) ||
      (xFree == 0 && yFree == rowColSize - 1) ||
      (xFree == rowColSize - 1 && yFree == 0) ||
      (xFree == rowColSize - 1 && yFree == rowColSize - 1);
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
      boolean cornerTopLeft = (xFree == 0 && yFree == 0);
      boolean cornerTopRight = (xFree == 0 && yFree == rowColSize - 1);
      boolean cornerBotLeft = (xFree == rowColSize - 1 && yFree == 0);
      boolean cornerBotRight = (xFree == rowColSize - 1 && yFree == rowColSize - 1);
      if (cornerTopLeft) {
        // abajo
        result.add(createSuccessor(s, xFree + 1, yFree));
        // derecha
        result.add(createSuccessor(s, xFree, yFree + 1));
      } else if (cornerTopRight) {
        // abajo
        result.add(createSuccessor(s, xFree + 1, yFree));
        // izquierda
        result.add(createSuccessor(s, xFree, yFree - 1));
      } else if (cornerBotLeft) {
        // arriba
        result.add(createSuccessor(s, xFree - 1, yFree));
        // derecha
        result.add(createSuccessor(s, xFree, yFree + 1));
      } else if (cornerBotRight) {
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

  private BaldosasState createSuccessor(BaldosasState original, int newX, int newY) {
    BaldosasState newState = original.clone();
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

    System.out.println("Moviendo 0 de (" + xFree + ", " + yFree + ") a (" + newX + ", " + newY + ")");

    return newState;
  }
}
