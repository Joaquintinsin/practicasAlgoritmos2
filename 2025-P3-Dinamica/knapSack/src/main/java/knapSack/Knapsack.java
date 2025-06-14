package knapSack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * This class implements different versions of the Knapsack problem.
 *
 * Methods included:
 * - Pure recursive solution
 * - Dynamic Programming (bottom-up tabulation)
 * - Memoization
 */
public class Knapsack {

  /**
   * Solves the Knapsack problem using plain recursion.
   *
   * @param W           the maximum capacity of the knapsack
   * @param itemsWeight array of item weights
   * @param itemsValue  array of item values
   * @return the maximum value that can be obtained without exceeding capacity
   */
  public static int knapSack(int W, int[] itemsWeight, int[] itemsValue) {
    return knapSack(W, itemsWeight, itemsValue, itemsWeight.length);
  }

  /**
   * Recursive helper function for the knapsack problem.
   *
   * @param w           remaining capacity
   * @param itemsWeight array of item weights
   * @param itemsValue  array of item values
   * @param i           number of items to consider
   * @return the maximum value possible
   */
  private static int knapSack(int w, int[] itemsWeight, int[] itemsValue, int i) {
    if (i == 0 || w == 0)
      return 0;
    if (itemsWeight[i - 1] > w)
      return knapSack(w, itemsWeight, itemsValue, i - 1);
    else
      return Math.max(itemsValue[i - 1] + knapSack(w - itemsWeight[i - 1], itemsWeight, itemsValue, i - 1),
          knapSack(w, itemsWeight, itemsValue, i - 1));
  }

  /**
   * Solves the Knapsack problem using Dynamic Programming (bottom-up).
   *
   * @param W           maximum capacity of the knapsack
   * @param itemsWeight array of item weights
   * @param itemsValue  array of item values
   * @return the maximum value that can be obtained
   */
  public static int knapSackPD(int W, int[] itemsWeight, int[] itemsValue) {
    int[] v = Arrays.copyOf(itemsValue, itemsValue.length);
    int[] p = Arrays.copyOf(itemsWeight, itemsWeight.length);

    int[][] dp = new int[p.length + 1][W + 1];
    // casos base, pero ya inicializa en 0 Java asi que es al cuete
    // el segundo indexado es del W, el caso base es cuando W es cero
    for (int i = 0; i <= W; i++) {
      dp[0][i] = 0;
    }
    // el primer indexado es del items a considerar, el caso base es cuando itemsConsiderar es cero
    for (int i = 0; i <= p.length; i++) {
      dp[i][0] = 0;
    }

    // caso inductivo
    for (int i = 1; i <= p.length; i++) {
      for (int j = 1; j <= W; j++) {
        if (p[i - 1] > j)
          dp[i][j] = dp[i - 1][j];
        else
          dp[i][j] = Math.max(dp[i - 1][j - p[i - 1]] + v[i - 1], dp[i - 1][j]);
      }
    }
    return dp[p.length][W];
  }

  /**
   * Solves the Knapsack problem using memoization.
   *
   * @param W           maximum capacity
   * @param itemsWeight array of item weights
   * @param itemsValue  array of item values
   * @return the maximum achievable value
   */
  public static int knapSackMEMO(int W, int[] itemsWeight, int[] itemsValue) {
    Map<Tuple<Integer, Integer>, Integer> cache = new HashMap<>();
    return helperMemo(W, itemsWeight, itemsValue, itemsWeight.length - 1, cache);
  }

  private static int helperMemo(int W, int[] v, int[] p, int i, Map<Tuple<Integer, Integer>, Integer> cache) {
    if (i == 0 || W == 0)
      return 0;

    Tuple<Integer, Integer> t = new Tuple<Integer,Integer>(W, i);
    if (!cache.containsKey(t)) {
      if (p[i - 1] > W)
        cache.put(t, helperMemo(W, p, v, i - 1, cache));
      else
        cache.put(t, Math.max(v[i - 1] + helperMemo(W - p[i - 1], p, v, i - 1, cache), helperMemo(W, p, v, i - 1, cache)));
    }
    return cache.get(t);
  }
}
