package levenshtein;

import java.util.HashMap;
import java.util.Map;

/**
 * This class provides methods to compute the Levenshtein Distance between two
 * strings.
 *
 * The Levenshtein Distance is the minimum number of single-character edits
 * (insertions, deletions, or substitutions) required to change one string into
 * the other.
 *
 * Three implementations are provided:
 * - Plain recursion
 * - Dynamic programming (bottom-up)
 * - Memoization
 */
public class Levenshtein {

  /**
   * Computes the Levenshtein Distance using Divide and Conquer.
   *
   * This method is inefficient for large strings due to repeated calculations.
   *
   * @param str1 the first string
   * @param str2 the second string
   * @return the Levenshtein distance between str1 and str2
   */
  public static int computeLevenshteinDistance(String str1, String str2) {
    return helperDivide(str1, str2, 0, str1.length() - 1, 0, str2.length() - 1);
  }

  private static int helperDivide(String s1, String s2, int inicioS1, int finS1, int inicioS2, int finS2) {
    if (inicioS1 == finS1) {
      return finS2 - inicioS2;
    }
    if (inicioS2 == finS2) {
      return finS1 - inicioS1;
    }
    if (s1.charAt(inicioS1) == s2.charAt(inicioS2)) {
      return helperDivide(s1, s2, inicioS1 + 1, finS1, inicioS2 + 1, finS2);
    } else {
      int eliminarDeLaSegunda = helperDivide(s1, s2, inicioS1, finS1, inicioS2 + 1, finS2);
      int eliminarDeLaPrimera = helperDivide(s1, s2, inicioS1 + 1, finS1, inicioS2, finS2);
      int sustituirCaracter = helperDivide(s1, s2, inicioS1 + 1, finS1, inicioS2 + 1, finS2);
      return 1 + Math.min(eliminarDeLaPrimera, Math.min(eliminarDeLaSegunda, sustituirCaracter));
    }
  }

  /**
   * Computes the Levenshtein Distance using dynamic programming (bottom-up
   * approach).
   *
   * @param str1 the first string
   * @param str2 the second string
   * @return the Levenshtein distance between str1 and str2
   */
  public static int computeLevenshteinDistancePD(String str1, String str2) {
    int finS1 = str1.length();
    int finS2 = str2.length();

    int[][] dp = new int[finS1 + 1][finS2 + 1];

    // casos base, el resto se ponen ceros por default
    for (int i = 0; i <= finS1; i++) {
      dp[i][0] = i;
    }
    for (int j = 0; j <= finS2; j++) {
      dp[0][j] = j;
    }

    // caso inductivo
    for (int i = 1; i <= finS1; i++) {
      for (int j = 1; j <= finS2; j++) {
        if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1];
        } else {
          dp[i][j] = 1 + minimum(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]);
        }
      }
    }
    return dp[finS1][finS2];
  }

  private static int minimum(int a, int b, int c) {
    int min = 0;
    min = (a < b) ? a : b;

    if (min > c)
      min = c;

    return min;
  }

  /**
   * Computes the Levenshtein Distance using memoization
   *
   * @param str1 the first string
   * @param str2 the second string
   * @return the Levenshtein distance between str1 and str2
   */
  public static int computeLevenshteinDistanceMemo(String str1, String str2) {
    Map<Tuple<Integer, Integer>, Integer> cache = new HashMap<>();
    return helperMemo(str1, str2, 0, 0, cache);
  }

  private static int helperMemo(String s1, String s2, int i, int j, Map<Tuple<Integer, Integer>, Integer> cache) {
    if (i == s1.length())
      return s2.length() - j;
    if (j == s2.length())
      return s1.length() - i;

    Tuple<Integer, Integer> t = new Tuple<>(i, j);

    if (!cache.containsKey(t)) {
      if (s1.charAt(i) == s2.charAt(j)) {
        cache.put(t, helperMemo(s1, s2, i + 1, j + 1, cache));
      } else {
        int eliminarDeLaSegunda = helperMemo(s1, s2, i, j + 1, cache);
        int eliminarDeLaPrimera = helperMemo(s1, s2, i + 1, j, cache);
        int sustituirCaracter = helperMemo(s1, s2, i + 1, j + 1, cache);
        cache.put(t, 1 + Math.min(eliminarDeLaPrimera, Math.min(eliminarDeLaSegunda, sustituirCaracter)));
      }
    }
    return cache.get(t);
  }
}
