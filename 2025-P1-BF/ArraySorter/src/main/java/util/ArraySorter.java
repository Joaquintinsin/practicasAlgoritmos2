package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Sorts arrays of comparable objects using a variety of options.
 *
 * @author aguirre
 *
 * @param <E> is the type of the elements of the array.
 */
public class ArraySorter<E extends Comparable<E>> {

	/**
	 * The array to sort.
	 */
	private E[] array;

	/**
	 * The algorithm to use for sorting.
	 */
	private SortAlgorithm algorithm = SortAlgorithm.SELECTIONSORT;

	/**
	 * Default constructor. Sets the array to sort and sorting algorithm to
	 * SELECTION SORT.
	 *
	 * @param array is the array to sort.
	 */
	public ArraySorter(E[] array) {
		if (array == null)
			throw new IllegalArgumentException("array must be non-null");
		this.array = array;
	}

	/**
	 * Constructor that sets array and sorting algorithm.
	 *
	 * @param array     is the array to sort.
	 * @param algorithm is the algorithm to use for sorting.
	 */
	public ArraySorter(E[] array, SortAlgorithm algorithm) {
		if (array == null)
			throw new IllegalArgumentException("array must be non-null");
		this.array = array;
		this.algorithm = algorithm;
	}

	/**
	 * Sets the algorithm to use for sorting.
	 *
	 * @param algorithm is the algorithm to set for sorting.
	 */
	public void setAlgorithm(SortAlgorithm algorithm) {
		if (algorithm == null)
			throw new IllegalArgumentException("algorithm can't be null");
		this.algorithm = algorithm;
	}

	/**
	 * Retrieves the (sorted or yet unsorted) array within the ArraySorter.
	 *
	 * @return the array stored within the ArraySorter object.
	 */
	public E[] getArray() {
		return this.array;
	}

	/**
	 * Sets the array to be sorted.
	 *
	 * @param array is the new array to sort.
	 */
	public void setArray(E[] array) {
		this.array = array;
	}

	/**
	 * Sorts the array.
	 * The array can then be retrieved using getArray() method.
	 */
	public void sort() {
		switch (this.algorithm) {
			case INSERTIONSORT:
				insertionSort(this.array);
				break;
			case BUBBLESORT:
				bubbleSort(this.array);
				break;
			case SELECTIONSORT:
				selectionSort(this.array);
				break;
			case MERGESORT:
				mergeSort(this.array);
				break;
			case SLOWSORT:
				slowSort(this.array);
				break;
			default:
				// Default is selection sort, according to default constructor.
				selectionSort(this.array);
		}
	}

	/**
	 * Sorts an array. Implements the insertion sort algorithm.
	 *
	 * @param <T>   is the type of the elements in the array.
	 * @param array is the array to be sorted.
	 */
	private static <T extends Comparable<T>> void insertionSort(T[] array) {
		if (array == null)
			throw new IllegalArgumentException("array is null, can't sort");
		for (int unsorted = 1; unsorted < array.length; unsorted++) {
			T key = array[unsorted];
			int loc = unsorted - 1;
			while ((loc >= 0) && (array[loc].compareTo(key) > 0)) {
				array[loc + 1] = array[loc];
				loc--;
			}
			array[loc + 1] = key;
		}
	}

	/**
	 * Sorts an array. Implements the selection sort algorithm.
	 *
	 * @param <T>   is the type of the elements in the array.
	 * @param array is the array to be sorted.
	 */
	private static <T extends Comparable<T>> void selectionSort(T[] array) {
		if (array == null)
			throw new IllegalArgumentException("array is null, can't sort");
		for (int i = 0; i < array.length; i++) {
			int min = i;
			for (int j = i; j < array.length; j++) {
				if (array[j].compareTo(array[min]) < 0) {
					min = j;
				}
			}
			T temp = array[i];
			array[i] = array[min];
			array[min] = temp;
		}
	}

	/**
	 * Sorts an array. Implements the bubblesort sort algorithm.
	 *
	 * @param <T>   is the type of the elements in the array.
	 * @param array is the array to be sorted.
	 */
	private static <T extends Comparable<T>> void bubbleSort(T[] array) {
		if (array == null)
			throw new IllegalArgumentException("array is null, can't sort");
		for (int i = array.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (array[j].compareTo(array[j + 1]) > 0) {
					T temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
	}

	/**
	 * Sorts an array. Implements the merge sort algorithm.
	 *
	 * @param <T>   is the type of the elements in the array.
	 * @param array is the array to be sorted.
	 */
	private static <T extends Comparable<T>> void mergeSort(T[] array) {
		if (array == null)
			throw new IllegalArgumentException("array is null, can't sort");
		if (array.length <= 1)
			return;

		T[] left = Arrays.copyOfRange(array, 0, array.length / 2);
		T[] right = Arrays.copyOfRange(array, array.length / 2, array.length);
		mergeSort(left);
		mergeSort(right);

		merge(array, left, right);
	}

	private static <T extends Comparable<T>> void merge(T[] array, T[] left, T[] right) {
		int i = 0, j = 0, k = 0;

		while (i < left.length && j < right.length) {
			if (left[i].compareTo(right[j]) <= 0) {
				array[k++] = left[i++];
			} else {
				array[k++] = right[j++];
			}
		}

		while (i < left.length) {
			array[k++] = left[i++];
		}

		while (j < right.length) {
			array[k++] = right[j++];
		}
	}

	/**
	 * Sorts an array. Implements the slow sort algorithm.
	 *
	 * @param <T>   is the type of the elements in the array.
	 * @param array is the array to be sorted.
	 */
	private static <T extends Comparable<T>> void slowSort(T[] array) {
		if (array == null)
			throw new IllegalArgumentException("array is null, can't sort");
		if (array.length <= 1)
			return;

		List<T[]> permutations = permutations(array);
		for (T[] perm : permutations) {
			if (isSorted(perm)) {
				System.arraycopy(perm, 0, array, 0, array.length); // Copia la permutación ordenada
				return; // Termina al encontrar la primera ordenada
			}
		}
	}

	private static <T> List<T[]> permutations(T[] list) {
		List<T[]> result = new ArrayList<>();
		permutationsHelper(list, 0, result);
		return result;
	}

	private static <T> void permutationsHelper(T[] list, int index, List<T[]> result) {
		if (index >= list.length) {
			result.add(Arrays.copyOf(list, list.length));
			return;
		}
		for (int i = index; i < list.length; i++) {
			swap(list, index, i);
			permutationsHelper(list, index + 1, result);
			swap(list, index, i); // Para restaurar la lista al estado original
		}
	}

	private static <T> void swap(T[] list, int i, int j) {
		T temp = list[i];
		list[i] = list[j];
		list[j] = temp;
	}

	/**
	 * Checks if a given array is sorted.
	 *
	 * @param <T>   is the type of the elements in the array.
	 * @param array is the array to be checked for sortedness.
	 * @return true iff the array is sorted.
	 */
	public static <T extends Comparable<T>> boolean isSorted(T[] array) {
		if (array == null)
			throw new IllegalArgumentException("array is null, can't sort");
		for (int i = 1; i < array.length; i++) {
			if (array[i - 1].compareTo(array[i]) > 0) {
				return false;
			}
		}
		return true;
	}
}
