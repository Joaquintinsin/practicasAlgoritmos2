package examples.tateti;

import java.util.Scanner;
import adversarysearch.EngineAdversary;
import engine.MinMaxEngine;

public class TatetiMainApp {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    TatetiProblemAdversaryState problem = new TatetiProblemAdversaryState();
    TatetiAdversaryState currentState = problem.initialState();

    // profundidad = 9
    EngineAdversary<TatetiProblemAdversaryState, TatetiAdversaryState> engine = new MinMaxEngine<>(9, problem);

    System.out.println("Inicio del Tateti");
    System.out.println("Humano = MAX = X (1 en la grilla internamente)");
    System.out.println("Computadora = MIN = O (-1 en la grilla internamente)");
    System.out.println("Poner una ficha = fila y columna (de 0 a 2)");

    while (!currentState.end()) {
      System.out.println("Tablero actual:");
      System.out.println(currentState);

      if (currentState.isMax()) {
        // Juega MAX
        boolean jugadaValida = false;
        while (!jugadaValida) {
          System.out.print("Tu turno (ingresa fila y columna): ");
          int fila = scanner.nextInt();
          int col = scanner.nextInt();

          if (fila >= 0 && fila < 3 && col >= 0 && col < 3 && currentState.getGrid()[fila][col] == 0) {
            int[][] newGrid = TatetiProblemAdversaryState.clonarGrid(currentState.getGrid());
            newGrid[fila][col] = 1; // Humano es MAX = 1
            currentState = new TatetiAdversaryState(newGrid, false); // Siguiente turno: computadora
            jugadaValida = true;
          } else {
            System.out.println("Movimiento inválido. Intenta de nuevo.");
          }
        }
      } else {
        // Turno de la computadora (MIN)
        System.out.println("Turno de la computadora...");
        currentState = engine.computeSuccessor(currentState);
      }
    }

    System.out.println("\nTablero final:");
    System.out.println(currentState);

    int resultado = currentState.value();
    if (resultado == 10) {
      System.out.println("¡Felicidades, ganaste!");
    } else if (resultado == -10) {
      System.out.println("¡La computadora ganó!");
    } else {
      System.out.println("¡Empate!");
    }

    scanner.close();
  }
}
