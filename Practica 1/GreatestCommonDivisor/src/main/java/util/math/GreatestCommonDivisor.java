package util.math;

import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Computeds greatest common divisor of two nonnegative, not-both-zero
 * integers using diferents algorithms.
 * 
 * @author scilingo
 */

public class GreatestCommonDivisor {

	/**
	* Computes greatest common divisor by Euclid's algorithm
	* @param m is a nonnegative integer fisrt argument.
	* @param n is second nonnegative integer argument.
	* @return the greatest common divisor between m and n.
	*/
	public static int euclidAlgorithm(int m, int n) {
		if (m < 0 || n < 0 || (m == 0 && n == 0)) throw new IllegalArgumentException("numbers must be nonnegative and not-both-zero");
		if(n == 0)
			return m;
		return euclidAlgorithm(n,m%n);
	}

	/**
	* Computes greatest common divisor by definition based algorithm
	* @param m is a nonnegative integer fisrt argument.
	* @param n is second nonnegative integer argument.
	* @return the greatest common divisor between m and n.
	*/
	public static int definitionBasedAlgorithm(int m, int n) {
		if (m < 0 || n < 0 || (m == 0 && n == 0)) throw new IllegalArgumentException("numbers must be nonnegative and not-both-zero");
    if (n == 0) return m;
    if (m == 0) return n;
    int minEntry = (m < n) ? m : n;
    while (((m % minEntry != 0) || (n % minEntry != 0)) && minEntry > 1) {
      minEntry--;
    }
    return minEntry;
	}

	/**
	* Computes greatest common divisor by middle school procedure
	* @param m is a nonnegative integer fisrt argument.
	* @param n is second nonnegative integer argument.
	* @return the greatest common divisor between m and n.
	*/
  public static int middleSchoolAlgorithm(int m, int n) {
    if (m < 0 || n < 0 || (m == 0 && n == 0)) throw new IllegalArgumentException("numbers must be nonnegative and not-both-zero");
    // Hardcode this cases: problems with dividing by zero at test cases
    if (n == 0) return m;
    if (m == 0) return n;
    ArrayList<Integer> primeFactorsOfM = primeFactors(m); // Step 1 Find the prime factors of m.
    ArrayList<Integer> primeFactorsOfN = primeFactors(n); // Step 2 Find the prime factors of n.
    // Step 3 Identify all the common factors in the two prime expansions found in Step 1 and Step 2.
    //        (If p is a common factor occurring p_m and p_n times in m and n, respectively, 
    //        it should be repeated min{p_m , p_n} times.)
    ArrayList<Integer> commonFactors = new ArrayList<Integer>();
    for (int pFM : primeFactorsOfM) {
      if (primeFactorsOfN.contains(pFM)) {
        commonFactors.add(pFM);
        primeFactorsOfN.remove((Integer) pFM); // Avoiding duplicates
      }
    }
    // Step 4 Compute the product of all the common factors and return it as the greatest common divisor of the numbers given.
    int result = 1;
    for (int cF : commonFactors) {
      result *= cF;
    }
    return result;
  }

  private static ArrayList<Integer> primeFactors(int x) {
    ArrayList<Integer> factors = new ArrayList<Integer>();
    for (int i = 2 ; i <= x / i ; i++) {
      while (x % i == 0) {
        factors.add(i);
        x /= i;
      }
    }
    if (x > 1) {
      factors.add(x);
    }
    return factors;
  }
  // Method that verifies if a given integer is prime or not
  public static boolean isPrime(int x) {
    if (x <= 1) return false;
    if (x <= 3) return true;
    for (int i = 2 ; i <= Math.sqrt(x) ; i++) {
      if (x % i == 0) {
        return false;
      }
    }
    return true;
  }

	/**
	* Implements the sieve of Eratosthenes
	* @param n is a number greater than 1
	* @return Array of all prime numbers less than or equal to n.
	*/
  private static int[] sieve(int n){
    int[] A = new int[n+1];
    int[] L = new int[n+1];
		for (int p = 2 ; p <= n ; p++) {
      A[p] = p;
    }
    for (int p = 2 ; p <= Math.floor(n) ; p++) {
      if (A[p] != 0) {  // p hasn’t been eliminated on previous passes
        int j = p * p;
        while (j <= n) {
          A[j] = 0; // mark element as eliminated
          j = j + p;
        }
      }
    }
    // copy the remaining elements of A to array L of the prime
    int i = 0;
    for (int p = 2 ; p <= n ; p++) {
      if (A[p] != 0) {
        L[i] = A[p];
        i++;
      }
    }
    return L;
	}
}
