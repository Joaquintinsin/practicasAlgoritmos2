package combinatoria;

import java.util.HashMap;
import java.util.Map;

public class Combinatoria {

	/**
	 * Calculates the binomial coefficient C(n, m), also known as "n choose m",
	 * using a recursive approach.
	 *
	 * @param n the total number of items
	 * @param m the number of items to choose
	 * @return the number of combinations of n items taken m at a time
	 * @throws IllegalArgumentException if n or m is negative
	 */

	public static int combinatoria(int n, int m) {
		if (n < 0 || m < 0)
			throw new IllegalArgumentException("error");
		if (m > n)
			return 0;
		if (m == 0 || n == m)
			return 1;
		return combinatoria(n - 1, m - 1) + combinatoria(n - 1, m);
	}

	/**
	 * This method should be implemented using recursion with caching (memoization)
	 * to avoid redundant calculations and improve performance.
	 *
	 * @param n the total number of items
	 * @param m the number of items to choose
	 * @return the number of combinations of n items taken m at a time
	 * @throws IllegalArgumentException if n or m is negative
	 */
	private static Map<Tuple<Integer, Integer>, Integer> cache = new HashMap<>();

	public static int memoCombinatoria(int n, int m) {
		if (n < 0 || m < 0)
			throw new IllegalArgumentException("error");
		if (m > n)
			return 0;
		if (m == 0 || n == m)
			return 1;

		Tuple<Integer, Integer> t = new Tuple<>(n, m);
		if (!cache.containsKey(t)) {
			cache.put(t, memoCombinatoria(n - 1, m - 1) + memoCombinatoria(n - 1, m));
		}
		return cache.get(t);
	}

	/**
	 * This method should use a bottom-up approach to build a table of values
	 * for all combinations up to n and m.
	 *
	 * @param n the total number of items
	 * @param m the number of items to choose
	 * @return the number of combinations of n items taken m at a time
	 * @throws IllegalArgumentException if n or m is negative
	 */
	public static int PDCombinatoria(int n, int m) {
		if (n < 0 || m < 0)
			throw new IllegalArgumentException("error");
		if (m > n)
			return 0;
		if (m == 0 || n == m)
			return 1;

		int[][] comb = new int[n + 1][m + 1];

		for (int i = 0; i <= n; i++) {
			// pongo 1 en la primer columna
			comb[i][0] = 1;
			if (i <= m) {
				// pongo 1 en la diagonal
				comb[i][i] = 1;
			}
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= Math.min(i, m); j++) {
				if (j > 0 && j < i) {
					comb[i][j] = comb[i - 1][j - 1] + comb[i - 1][j];
				}
			}
		}

		return comb[n][m];
	}
}
