package util.sequences;

import java.util.Arrays;
import java.lang.Math;

/**
 * Computes minimum sum subsequence of Integer sequences
 * 
 * @author scilingo
 */

public class MinimumSumSubsequence{

	/**
	 * Computes a minimum sum subsequence by divide and conquer strategy.
	 * @param sequence is an Integer sequence.
	 * @return return a tuple containing,  value of the sum, the begin index
	 * of subsequence and the end of the subsequence
	 */
  public static Tuple<Integer, Integer, Integer> minimumSumSubsequence(Integer[] sequence) {
    // Un arreglo vacío o la suma de todos ellos es positiva, devuelve una tupla con 0, -1, -1
    if (sequence.length == 0 || sumOfArray(sequence) > 0) 
      return new Tuple<>(0, -1, -1);
    // Un arreglo con 1 solo elemento, devuelve la tupla con ese elemento y posiciones 0 y 0
    if (sequence.length == 1) 
      return new Tuple<>(sequence[0], 0, 0);
    // Tipo un merge, que resuelve ya y devuelve la tupla, pero necesita esos dos parámetros más
    return minimumSumSubsequence(sequence, 0, sequence.length - 1);
  }

  // Toma un arreglo de enteros y devuelve la suma de todos ellos
  private static Integer sumOfArray(Integer[] arr) {
    Integer res = 0;
    for (Integer element : arr)
      res += element;
    return res;
  }

  private static Tuple<Integer, Integer, Integer> minimumSumSubsequence(Integer[] sequence, int ini, int end) {
    if (ini == end) 
      return new Tuple<>(sequence[ini], ini, end);
    int mid = (ini+end)/2;
    Tuple<Integer, Integer, Integer> leftTuple = minimumSumSubsequence(sequence, ini, mid);
    Tuple<Integer, Integer, Integer> rightTuple = minimumSumSubsequence(sequence, mid+1, end);
    Tuple<Integer, Integer, Integer> middleTuple = findCrossSubsequence(sequence, ini, mid, end);
    if (leftTuple.getFirst() <= rightTuple.getFirst() && leftTuple.getFirst() <= middleTuple.getFirst()) {
      return leftTuple;
    } else if (rightTuple.getFirst() <= leftTuple.getFirst() && rightTuple.getFirst() <= middleTuple.getFirst()) {
      return rightTuple;
    } else {
      return middleTuple;
    }
  }

  private static Tuple<Integer, Integer, Integer> findCrossSubsequence(Integer[] arr, int low, int mid, int high) {
    int leftSum = Integer.MAX_VALUE;
    int sum = 0;
    int minLeftIndex = mid;
    for (int i = mid; i >= low; i--) {
      sum += arr[i];
      if (sum < leftSum) {
        leftSum = sum;
        minLeftIndex = i;
      }
    }

    int rightSum = Integer.MAX_VALUE;
    sum = 0;
    int minRightIndex = mid + 1;
    for (int i = mid + 1; i <= high; i++) {
      sum += arr[i];
      if (sum < rightSum) {
        rightSum = sum;
        minRightIndex = i;
      }
    }

    return new Tuple<>(leftSum + rightSum, minLeftIndex, minRightIndex);
  }
}
