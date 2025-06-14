package util.math;

import java.util.HashMap;
import java.util.Map;

/**
 * This class implements several algorithms to solve the Matrix Chain
 * Multiplication problem. The goal is to find the optimal way to parenthesize
 * a chain of matrices so that the total number of scalar multiplications is
 * minimized.
 *
 * Matrix A[i] has dimensions chain[i-1] x chain[i] for i = 1 to n.
 */
public class MatrixChainMultiplication {

  /** Array containing the dimensions of the matrices. */
  private Integer[] chain;

  /** Table to store the index of the optimal split point */
  private int[][] splitPoints;

  /**
   * Constructs a new MatrixChainMultiplication object with the given matrix
   * dimensions.
   *
   * @param chain an array where matrix A[i] has dimensions chain[i-1] x chain[i]
   */
  public MatrixChainMultiplication(Integer[] chain) {
    this.chain = chain;
    this.splitPoints = new int[chain.length][chain.length];
  }

  /**
   * Solves the matrix chain multiplication problem using the specified algorithm.
   *
   * @param algorithm the algorithm to use (Divide & Conquer, Dynamic Programming,
   *                  Memoization)
   * @return an OptimalParenthesization object containing the minimum cost and the
   *         optimal parenthesization as a string
   */
  public OptimalParenthesization matrixChainMultiplication(MatrixChainMultAlgorithm algorithm) {
    int cost = 0;
    switch (algorithm) {
      case DIVIDEANDCONQUER:
        cost = divideAndConquerMCM(chain, 1, chain.length - 1);
        break;
      case DYNAMICPROGRAMMING:
        cost = dynamicProgrammingMCM(chain);
        break;
      case MEMOIZATION:
        cost = matrixChainMultiplicationMEMO(chain, 1, chain.length - 1);
        break;
      default:
        throw new UnsupportedOperationException("");
    }

    String parenthesized = buildParenthesized(1, chain.length - 1);

    return new OptimalParenthesization(cost, parenthesized);

  }

  /**
   * Recursively constructs the optimal parenthesization using the splitPoints
   * table.
   *
   * @param i start index
   * @param j end index
   * @return a string representing the optimal parenthesization from matrix M_i to
   *         M_j
   */
  private String buildParenthesized(int i, int j) {
    if (i == j) {
      return "M" + i; // Caso base: una sola matriz
    }

    int k = this.splitPoints[i][j]; // Obtenemos el punto de división óptimo

    // Construimos recursivamente la parentización para las dos subcadenas
    String left = buildParenthesized(i, k);
    String right = buildParenthesized(k + 1, j);

    // Combinamos las subcadenas con los paréntesis
    return "(" + left + right + ")";
  }

  /**
   * Solves the matrix chain multiplication problem using the Divide & Conquer
   * technique.
   *
   * @param chain the array of matrix dimensions
   * @param i     start index
   * @param j     end index
   * @return the minimum number of scalar multiplications needed
   */
  private int divideAndConquerMCM(Integer[] chain, int i, int j) {
    // This method MUST set splitPoints table
    if (i == j) {
      return 0;
    }

    int min = Integer.MAX_VALUE;

    // Probar todas las posibles posiciones para el paréntesis
    for (int k = i; k < j; k++) {
      int left = divideAndConquerMCM(chain, i, k);
      int right = divideAndConquerMCM(chain, k + 1, j);
      int costMult = chain[i - 1] * chain[k] * chain[j];

      int total = left + right + costMult;
      if (total < min) {
        min = total;
        splitPoints[i][j] = k;
      }
    }

    return min;
  }

  /**
   * Solves the matrix chain multiplication problem using Dynamic Programming.
   *
   * @param chain the array of matrix dimensions
   * @return the minimum number of scalar multiplications needed
   */
  private int dynamicProgrammingMCM(Integer chain[]) {
    int n = chain.length - 1; // Número de matrices
    int[][] dp = new int[n + 1][n + 1]; // Tabla para almacenar los costos mínimos

    // Los costos para una sola matriz son 0
    for (int i = 1; i <= n; i++) {
      dp[i][i] = 0;
    }

    // Iteramos sobre la longitud de la subcadena (l)
    for (int l = 2; l <= n; l++) {
      // Iteramos sobre el índice de inicio de la subcadena (i)
      for (int i = 1; i <= n - l + 1; i++) {
        int j = i + l - 1; // Índice de fin de la subcadena
        dp[i][j] = Integer.MAX_VALUE;

        // Iteramos sobre todos los posibles puntos de división k
        for (int k = i; k < j; k++) {
          int cost = dp[i][k] + dp[k + 1][j] + chain[i - 1] * chain[k] * chain[j];
          if (cost < dp[i][j]) {
            dp[i][j] = cost;
            this.splitPoints[i][j] = k; // Guardamos el punto de división óptimo
          }
        }
      }
    }

    return dp[1][n]; // El costo mínimo para multiplicar la cadena completa es dp[1][n]
  }

  /* solucion memoization...
   * cuando no contiene la tupla key (i,j)
   * la calcula, llama al memo izquierdo y derecho que almacene los resultados parciales,
   * resuelve la suma esa, la multiplicacion etc, y ahi lo guarda
   *
   * ya cuando termina de calcular y guardar, lo devuelve..
   *
   * para obtener el resultado del minimo costo tiene q hacerse igual que antes, se tiene que calcular de alguna forma
   * asi que esa parte es copiar y pegar de las soluciones de arriba
   */
  private Map<Tuple<Integer, Integer>, Integer> cache = new HashMap<>();

  private int matrixChainMultiplicationMEMO(Integer[] chain, int i, int j) {
    if (i == j) {
      return 0;
    }
    Tuple<Integer, Integer> t = new Tuple<Integer,Integer>(i, j);
    if (!cache.containsKey(t)) {
      int min = Integer.MAX_VALUE;
      // Probar todas las posibles posiciones para el paréntesis
      for (int k = i; k < j; k++) {
        int left = matrixChainMultiplicationMEMO(chain, i, k);
        int right = matrixChainMultiplicationMEMO(chain, k + 1, j);
        int costMult = chain[i - 1] * chain[k] * chain[j];

        int total = left + right + costMult;
        if (total < min) {
          min = total;
          splitPoints[i][j] = k;
        }
      }

      cache.put(t, min);
    }
    return cache.get(t);
  }

}
