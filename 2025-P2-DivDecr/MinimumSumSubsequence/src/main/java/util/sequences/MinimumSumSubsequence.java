package util.sequences;

import java.util.Arrays;

/**
 * It Implements algorithms for computing minimum sum subsequences.
 *
 * @author Scilingo
 */

 public class MinimumSumSubsequence{

	/**
	 * Finds the contiguous subsequence with the minimum sum in a given array using the Divide and Conquer approach.
	 * @param sequence The input array of integers.
	 * @return A {@code Subseq<Integer>} object containing the sum of the minimum subsequence
	 *         and its corresponding start and end indices.
	 */
	public static Subseq<Integer> minimumSumSubsequence(Integer[] sequence) {
		// Casos base
		if (sequence.length == 0) {
			return new Subseq<>(0, -1, -1);
		} else if (sequence.length == 1) {
			if (sequence[0] < 0) {
				return new Subseq<>(sequence[0], 0, 0);
			} else {
				return new Subseq<>(0, -1, -1);
			}
		}

		// Divide y conquista
		return minSumHelper(sequence, 0, sequence.length - 1);
	}

	private static Subseq<Integer> minSumHelper(Integer[] sequence, int lower, int upper) {
		// casos base
		if (lower == upper) {
			// un solo elemento.
			// es negativo? esa es la minima subsecuencia.
			// es positivo? la minima subsecuencia es la subsecuencia vacia con 0, -1 y -1.
			return sequence[lower] < 0 ? new Subseq<>(sequence[lower], lower, upper) : new Subseq<>(0, -1, -1);
		}

		// Divide
		int mid = (lower + upper) / 2;

		Subseq<Integer> minLeft = minSumHelper(sequence, lower, mid);
    Subseq<Integer> minRight = minSumHelper(sequence, mid + 1, upper);
    Subseq<Integer> minCrossing = minSumCrossing(sequence, lower, mid, upper);

		// Conquer, retornar la subsecuencia con la menor suma
    if (minLeft.getSum() <= minRight.getSum() && minLeft.getSum() <= minCrossing.getSum()) {
			return minLeft;
    } else if (minRight.getSum() <= minLeft.getSum() && minRight.getSum() <= minCrossing.getSum()) {
			return minRight;
    } else {
			return minCrossing;
    }
	}

	private static Subseq<Integer> minSumCrossing(Integer[] seq, int left, int mid, int right) {
		int minLeftSum = Integer.MAX_VALUE;
		int sum = 0;
		int minIndiceLeft = -1;

		// Minima subsecuencia desde mid hacia la izquierda
		for (int i = mid; i >= left; i--) {
			sum += seq[i];
			if (sum < minLeftSum) {
				minLeftSum = sum;
				minIndiceLeft = i;
			}
		}

		int minRightSum = Integer.MAX_VALUE;
		sum = 0;
		int minIndiceRight = -1;

		// Minima subsecuencia desde mid + 1 hacia la derecha
		for (int i = mid + 1; i <= right; i++) {
			sum += seq[i];
			if (sum < minRightSum) {
				minRightSum = sum;
				minIndiceRight = i;
			}
		}

		return new Subseq<>(minLeftSum + minRightSum, minIndiceLeft, minIndiceRight);
	}
}
