public class JavaProblems {

  public class Levenshtein {
    // Levenshtein CLASS

    /**
     * Computes the Levenshtein Distance using dynamic programming (bottom-up
     * approach).
     *
     * @param str1 the first string
     * @param str2 the second string
     * @return the Levenshtein distance between str1 and str2
     */
    public static int computeLevenshteinDistancePD(String str1, String str2) {
      int[][] distance = new int[str1.length() + 1][str2.length() + 1];

      for (int i = 0; i <= str1.length(); i++) {
        distance[i][0] = i;
      }
      for (int j = 0; j < str2.length() + 1; j++)
        distance[0][j] = j;

      for (int i = 1; i <= str1.length(); i++) {
        for (int j = 1; j <= str2.length(); j++) {
          distance[i][j] = minimum(
              distance[i - 1][j] + 1,
              distance[i][j - 1] + 1,
              distance[i - 1][j - 1] + ((str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0 : 1));
        }
      }
      return distance[str1.length()][str2.length()];
    }

    private static int minimum(int a, int b, int c) {
      int min = 0;
      min = (a < b) ? a : b;
      return (min < c) ? min : c;
    }
  }

  public class KnapSackProblem {
    // KnapSackProblem CLASS

    /**
     * Recursive helper function for the knapsack problem.
     *
     * @param w           remaining capacity
     * @param itemsWeight array of item weights
     * @param itemsValue  array of item values
     * @param i           number of items to consider
     * @return the maximum value possible
     */
    public static int knapSack(int w, int[] itemsWeight, int[] itemsValue, int i) {
      if (i == 0 || w == 0)
        return 0;
      if (itemsWeight[i - 1] > w)
        return knapSack(w, itemsWeight, itemsValue, i - 1);
      else
        return Math.max(
            itemsValue[i - 1] + knapSack(w - itemsWeight[i - 1], itemsWeight, itemsValue, i - 1),
            knapSack(w, itemsWeight, itemsValue, i - 1));
    }

    /**
     * Solves the Knapsack problem using memoization.
     *
     * @param w           maximum capacity
     * @param itemsWeight array of item weights
     * @param itemsValue  array of item values
     * @return the maximum achievable value
     */
    public static int knapSackMEMO(int w, int[] itemsWeight, int[] itemsValue) {
      int[][] cache = new int[itemsWeight.length + 1][w + 1];
      int n = itemsWeight.length;

      // Creamos la tabla de memoization, inicializada en -1 (significa "no
      // calculado")
      for (int i = 0; i <= n; i++) {
        for (int j = 0; j <= w; j++) {
          cache[i][w] = -1;
        }
      }

      return knapSackMEMO(w, itemsWeight, itemsValue, n, cache);
    }

    /**
     * Recursive helper for memoization
     *
     * @param w           reimaining capacity
     * @param itemsWeight array of weights
     * @param itemsValue  array of values
     * @param i           items remaining to consider
     * @param cache       memoization table
     * @return maximum value possible
     */
    private static int knapSackMEMO(int w, int[] itemsWeight, int[] itemsValue, int i, int[][] cache) {
      // Caso base
      if (i == 0 || w == 0)
        return 0;
      if (cache[i][w] == -1) {
        // Si el peso del item actual excede la capacidad, no lo tomamos
        if (itemsWeight[i - 1] > w) {
          cache[i][w] = knapSackMEMO(w, itemsWeight, itemsValue, i - 1, cache);
        } else {
          // Consideramos la mejor opcion: incluir o no el item actual
          cache[i][w] = Math.max(
              itemsValue[i - 1] + knapSackMEMO(w - itemsWeight[i - 1], itemsWeight, itemsValue, i - 1, cache),
              knapSackMEMO(w - itemsWeight[i - 1], itemsWeight, itemsValue, i - 1, cache));
        }
      }
      return cache[i][w];
    }

    public static int knapSackMEMO2(int w, int[] itemsWeight, int[] itemsValue) {
      Map<Tuple, Integer> cache = new HashMap<Tuple, Integer>();
      int n = itemsWeight.length;

      return knapSackMEMO2(w, itemsWeight, itemsValue, n, cache);
    }

    private static int knapSackMEMO2(int w, int[] itemsWeight, int[] itemsValue, int i, Map<Tuple, Integer> cache) {
      // Caso base
      if (i == 0 || w == 0)
        return 0;
      if (!cache.containsKey(new Tuple(i, w))) {
        // Si el peso del item actual excede la capacidad, no lo tomamos
        if (itemsWeight[i - 1] > w) {
          cache.put(new Tuple(i, w), knapSackMEMO2(w, itemsWeight, itemsValue, i - 1, cache));
        } else {
          // Consideramos la mejor opcion: incluir o no el item actual
          int max = Math.max(
              itemsValue[i - 1] + knapSackMEMO(w - itemsWeight[i - 1], itemsWeight, itemsValue, i - 1, cache),
              knapSackMEMO(w - itemsWeight[i - 1], itemsWeight, itemsValue, i - 1, cache));
          cache.put(new Tuple(i, w), max);
        }
      }
      return cache.get(new Tuple(i, w));
    }

    /**
     * Solves the Knapsack problem using Dynamic Programming (bottom-up).
     *
     * @param w           maximum capacity of the knapsack
     * @param itemsWeight array of item weights
     * @param itemsValue  array of item values
     * @return the maximum value that can be obtained
     */
    public static int knapSackPD(int w, int[] itemsWeight, int[] itemsValue) {
      int[][] pd = new int[itemsWeight.length + 1][w + 1];
      for (int i = 0; i <= w; i++) {
        dp[0][i] = 0;
      }
      for (int i = 0; i < itemsWeight.length; i++) {
        dp[i][0] = 0;
      }
      for (int i = 1; i <= itemsWeight.length; i++) {
        for (int j = 1; j <= w; j++) {
          if (j - itemsWeight[i - 1] < 0)
            dp[i][j] = dp[i - 1][j];
          else
            dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - itemsWeight[i - 1]] + itemsValue[i - 1]);
        }
      }
      return dp[itemsWeight.length][w];
    }
  }
}
