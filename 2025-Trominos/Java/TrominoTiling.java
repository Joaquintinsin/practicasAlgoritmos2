/**
 * Super chat?
 *  embaixo tem o resposta do chat
 *  o main code nao lembro se foi somente eu + chat ou somente eu
 *  nao usar jejee!! =)
 */

import java.util.ArrayList;
import java.util.Arrays;

public class TrominoTiling {

  public ArrayList<Tupla<Punto, Punto, Punto>> tromino(int n, int xCuadradito, int yCuadradito) {
    Integer[][] grilla = new Integer[(int) Math.pow(n, 2)][(int) Math.pow(n, 2)];
    inicializarGrilla(grilla);
    grilla[xCuadradito][yCuadradito] = -1;
    ArrayList<Tupla<Punto, Punto, Punto>> res = new ArrayList<>();
    trominoDivide(n, grilla, res);
    return res;
  }

  public void inicializarGrilla(Integer[][] grilla) {
    for (int i = 0; i < grilla.length; i++) {
      for (int j = 0; j < grilla.length; j++) {
        grilla[i][j] = 0;
      }
    }
  }

  public void trominoDivide(int n, Integer[][] grilla, ArrayList<Tupla<Punto, Punto, Punto>> res) {
    Punto cuadradito = findCuadradito(grilla);
    int cuadranteCuadradito = cuadranteCuadradito(grilla, cuadradito);
    int size = (int) Math.pow(2, n);
    int mid = size / 2;

    if (n == 1) { // Caso base
      if (contains(grilla, cuadradito)) {
        switch (cuadranteCuadradito) {
          case 1:
            res.add(
                new Tupla<>(new Punto(0, 1), new Punto(0, 0), new Punto(1, 1)));
            break;
          case 2:
            res.add(
                new Tupla<>(new Punto(0, 0), new Punto(1, 0), new Punto(1, 1)));
            break;
          case 3:
            res.add(
                new Tupla<>(new Punto(0, 0), new Punto(1, 0), new Punto(1, 1)));
            break;
          case 4:
            res.add(
                new Tupla<>(new Punto(0, 0), new Punto(0, 1), new Punto(1, 0)));
            break;
        }
        return;
      } else {
        switch (cuadranteCuadradito) {
          case 1:
            res.add(
                new Tupla<>(new Punto(mid, mid - 1), new Punto(mid, mid), new Punto(mid, mid + 1)));
            break;
          case 2:
            res.add(
                new Tupla<>(new Punto(mid - 1, mid - 1), new Punto(mid - 1, mid), new Punto(mid - 1, mid + 1)));
            break;
          case 3:
            res.add(
                new Tupla<>(new Punto(mid, mid - 1), new Punto(mid, mid), new Punto(mid, mid + 1)));
            break;
          case 4:
            res.add(
                new Tupla<>(new Punto(mid, mid - 1), new Punto(mid - 1, mid - 1), new Punto(mid - 1, mid)));
            break;
        }
      }
    } else { // Inductividad
      switch (cuadranteCuadradito) {
        case 1:
          res.add(
              new Tupla<>(new Punto(mid, mid - 1), new Punto(mid, mid), new Punto(mid, mid + 1)));
          break;
        case 2:
          res.add(
              new Tupla<>(new Punto(mid - 1, mid - 1), new Punto(mid - 1, mid), new Punto(mid - 1, mid + 1)));
          break;
        case 3:
          res.add(
              new Tupla<>(new Punto(mid, mid - 1), new Punto(mid, mid), new Punto(mid, mid + 1)));
          break;
        case 4:
          res.add(
              new Tupla<>(new Punto(mid, mid - 1), new Punto(mid - 1, mid - 1), new Punto(mid - 1, mid)));
          break;
      }
      trominoDivide(n - 1, grilla, res);
      return;
    }
  }

  public Punto findCuadradito(Integer[][] grilla) {
    Punto cuadradito = new Punto(-1, -1);
    for (int i = 0; i < grilla.length; i++) {
      for (int j = 0; j < grilla.length; j++) {
        if (grilla[i][j] == -1) {
          cuadradito = new Punto(i, j);
        }
      }
    }
    return cuadradito;
  }

  public int cuadranteCuadradito(Integer[][] grilla, Punto cuadradito) {
    int x = cuadradito.getX();
    int y = cuadradito.getY();
    if (x < grilla.length / 2 && y < grilla.length / 2) {
      return 1;
    } else if (x >= grilla.length / 2 && y < grilla.length / 2) {
      return 2;
    } else if (x < grilla.length / 2 && y >= grilla.length / 2) {
      return 3;
    } else {
      return 4;
    }
  }

  public boolean contains(Integer[][] grilla, Punto cuadradito) {
    return grilla[cuadradito.getX()][cuadradito.getY()] == -1;
  }

  public static void main(String[] args) {
    int n = 3; // Size of the board will be 2^n x 2^n
    int size = (int) Math.pow(2, n);

    // Position of the missing square
    int xCuadradito = 1;
    int yCuadradito = 2;

    // Create an instance of TrominoTiling and solve the problem
    TrominoTiling trominoTiling = new TrominoTiling();
    ArrayList<Tupla<Punto, Punto, Punto>> result = trominoTiling.tromino(n, xCuadradito, yCuadradito);

    // Print the result
    System.out.println("Tromino tiling solution:");
    for (Tupla<Punto, Punto, Punto> tromino : result) {
      System.out.println(tromino);
    }
  }
}

// ============================
// ============================
// Solucion Chat

// public class TrominoTiling {
// private static int tile = 1; // Contador de trominos
// private static int[][] board; // Tablero donde almacenaremos las soluciones

// public static void main(String[] args) {
// int n = 3; // Tamaño del tablero 2^n x 2^n
// int size = (int) Math.pow(2, n);
// board = new int[size][size];

// int missingX = 0, missingY = 0; // Posición de la casilla bloqueada
// board[missingX][missingY] = -1; // Representamos la casilla bloqueada con -1

// tromino(0, 0, size, missingX, missingY); // Llamada inicial

// // Imprimir resultado
// for (int[] row : board) {
// System.out.println(Arrays.toString(row));
// }
// }

// private static void tromino(int startX, int startY, int size, int holeX, int
// holeY) {
// if (size == 2) {
// // Caso base: colocar un tromino en un 2x2
// tile++;
// for (int i = 0; i < 2; i++) {
// for (int j = 0; j < 2; j++) {
// if (startX + i != holeX || startY + j != holeY) {
// board[startX + i][startY + j] = tile;
// }
// }
// }
// return;
// }

// int half = size / 2;
// int midX = startX + half;
// int midY = startY + half;

// // Determinar en qué subcuadrante está la casilla bloqueada
// int quadrant = (holeX < midX ? 0 : 1) + (holeY < midY ? 0 : 2);

// // Colocar un tromino en el centro
// tile++;
// int[][] offsets = { { midX - 1, midY - 1 }, { midX - 1, midY }, { midX, midY
// - 1 }, { midX, midY } };
// for (int i = 0; i < 4; i++) {
// if (i != quadrant) {
// board[offsets[i][0]][offsets[i][1]] = tile;
// }
// }

// // Llamadas recursivas en los 4 subtableros
// tromino(startX, startY, half, quadrant == 0 ? holeX : midX - 1, quadrant == 0
// ? holeY : midY - 1);
// tromino(startX, midY, half, quadrant == 1 ? holeX : midX - 1, quadrant == 1 ?
// holeY : midY);
// tromino(midX, startY, half, quadrant == 2 ? holeX : midX, quadrant == 2 ?
// holeY : midY - 1);
// tromino(midX, midY, half, quadrant == 3 ? holeX : midX, quadrant == 3 ? holeY
// : midY);
// }
// }
// ============================
// ============================
