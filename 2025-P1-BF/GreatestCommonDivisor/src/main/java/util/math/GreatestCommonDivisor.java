package util.math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Computeds greatest common divisor of two nonnegative, not-both-zero
 * integers using diferents algorithms.
 *
 * @author scilingo
 */

public class GreatestCommonDivisor {

	/**
	 * Computes greatest common divisor by Euclid's algorithm
	 *
	 * @param m is a nonnegative integer fisrt argument.
	 * @param n is second nonnegative integer argument.
	 * @return the greatest common divisor between m and n.
	 */
	public static int euclidAlgorithm(int m, int n) {
		if (m < 0 || n < 0 || (m == 0 && n == 0))
			throw new IllegalArgumentException("numbers must be nonnegative and not-both-zero");
		if (n == 0)
			return m;
		return euclidAlgorithm(n, m % n);
	}

	/**
	 * Computes greatest common divisor by definition based algorithm
	 *
	 * @param m is a nonnegative integer fisrt argument.
	 * @param n is second nonnegative integer argument.
	 * @return the greatest common divisor between m and n.
	 */
	public static int definitionBasedAlgorithm(int m, int n) {
		if (m < 0 || n < 0 || (m == 0 && n == 0))
			throw new IllegalArgumentException("numbers must be nonnegative and not-both-zero");
		int r = 1;
		while (n != 0) {
			r = m % n;
			m = n;
			n = r;
		}
		return m;
	}

	/**
	 * Computes greatest common divisor by middle school procedure
	 *
	 * @param m is a nonnegative integer fisrt argument.
	 * @param n is second nonnegative integer argument.
	 * @return the greatest common divisor between m and n.
	 */
	public static int middleSchoolAlgorithm(int m, int n) {
		if (m < 0 || n < 0 || (m == 0 && n == 0))
			throw new IllegalArgumentException("Numbers must be nonnegative and not both zero");
		if (n == 0)
			return m;
		if (m == 0)
			return n;

		List<Integer> factorsM = primeFactors(m);
		List<Integer> factorsN = primeFactors(n);

		List<Integer> commonFactors = commonFactors(factorsM, factorsN);

		return arrayProduct(commonFactors);
	}

	private static int arrayProduct(List<Integer> array) {
		int res = 1;
		for (int x : array)
			res *= x;
		return res;
	}

	private static List<Integer> commonFactors(List<Integer> factorsM, List<Integer> factorsN) {
		List<Integer> common = new ArrayList<>();
		List<Integer> copyN = new ArrayList<>(factorsN);
		for (Integer factor : factorsM) {
			if (copyN.contains(factor)) {
				common.add(factor);
				copyN.remove(factor);
			}
		}
		return common;
	}

	private static List<Integer> primeFactors(int num) {
		List<Integer> factors = new ArrayList<>();
		int i = 2;
		while (num > 1) {
			if (num % i == 0) {
				factors.add(i);
				num = num / i;
			} else {
				i++;
			}
		}
		return factors;
	}

	/**
	 * Implements the sieve of Eratosthenes
	 *
	 * @param n is a number greater than 1
	 * @return Array of all prime numbers less than or equal to n.
	 */
	private static int[] sieve(int n) {
		if (n <= 1)
			throw new IllegalArgumentException("n must be greater than 1");

		boolean[] isPrime = new boolean[n + 1]; // Array para marcar primos
		Arrays.fill(isPrime, true); // Inicialmente todos son primos
		isPrime[0] = isPrime[1] = false; // 0 y 1 no son primos

		for (int p = 2; p * p <= n; p++)// Solo hasta √n
			if (isPrime[p])
				for (int j = p * p; j <= n; j += p) // Marcar múltiplos como no primos
					isPrime[j] = false;

		// Contar los números primos
		int count = 0;
		for (boolean prime : isPrime)
			if (prime)
				count++;

		// Crear el array de primos
		int[] primes = new int[count];
		int index = 0;
		for (int i = 2; i <= n; i++)
			if (isPrime[i])
				primes[index++] = i;

		return primes;
	}
}
