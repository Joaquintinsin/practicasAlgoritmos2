package catalannumber;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@code CatalanNumber} class provides different methods to compute
 * the n-th Catalan number, a sequence of natural numbers that have many
 * applications
 * in combinatorial mathematics.
 *
 * This class includes implementations using plain recursion, dynamic
 * programming (DP),
 * and memoization.
 *
 * Catalan numbers follow the recurrence relation:
 * Cₙ = Σ (Cₖ₋₁ * Cₙ₋ₖ) for k = 1 to n, with C₀ = 1
 *
 * Example of Catalan numbers: 1, 1, 2, 5, 14, 42, 132, ...
 */
public class CatalanNumber {
  /**
   * Computes the n-th Catalan number using a plain recursive approach.
   *
   * This implementation has exponential time complexity due to redundant
   * subproblem computation.
   *
   * @param n the index of the Catalan number to compute (n ≥ 0)
   * @return the n-th Catalan number
   */

  public static long computeCatalanNumber(int n) {
    if (n == 0) {
      return 1;
    }

    long res = 0;
    for (int k = 1; k <= n; k++) {
      res += computeCatalanNumber(k - 1) * computeCatalanNumber(n - k);
    }
    return res;
  }

  /**
   * Computes the n-th Catalan number using dynamic programming (DP).
   *
   * This implementation avoids redundant computation by storing intermediate
   * results
   * in a bottom-up manner.
   *
   * @param n the index of the Catalan number to compute (n ≥ 0)
   * @return the n-th Catalan number
   */
  public static long computeCatalanNumberPD(int n) {
    if (n == 0) {
      return 1;
    }

    long[] dp = new long[n + 1];
    dp[0] = 1;
    for (int k = 1; k <= n; k++) {
      for (int j = 1; j <= k; j++) {
        dp[k] += (dp[j - 1] * dp[k - j]);
      }
    }
    return dp[n];
  }

  /**
   * Computes the n-th Catalan number using memoization (top-down dynamic
   * programming).
   *
   * This implementation improves the basic recursive version by caching results
   * of subproblems to avoid redundant calculations.
   *
   * @param n the index of the Catalan number to compute (n ≥ 0)
   * @return the n-th Catalan number
   */
  private static Map<Integer, Long> cache = new HashMap<>();

  public static long computeCatalanNumberMemo(int n) {
    if (n == 0) {
      return 1;
    }
    if (!cache.containsKey(n)) {
      long res = 0;
      for (int i = 1; i <= n; i++) {
        res += computeCatalanNumberMemo(i - 1) * computeCatalanNumberMemo(n - i);
      }
      cache.put(n, res);
    }
    return cache.get(n);
  }
}
