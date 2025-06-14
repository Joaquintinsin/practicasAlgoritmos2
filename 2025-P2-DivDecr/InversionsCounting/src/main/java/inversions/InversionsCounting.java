package inversions;

import java.util.Arrays;

class InversionsCounting {

	/*
	 * Calcula el número de inversiones en un arreglo dado.
	 * Una inversión se define como un par de valores (A[i], A[j]) en el arreglo
	 * tales que A[i] > A[j], con i < j.
	 *
	 * @param a Un arreglo de números enteros sobre el cual se contarán las
	 * inversiones.
	 *
	 * @return El número total de inversiones en el arreglo.
	 */
	public static int inversionsCounter(int[] array) {
		int[] copyArray = Arrays.copyOf(array, array.length);
		return inversionHelper(copyArray);
	}

	public static int inversionHelper(int[] array) {
		if (array == null)
			throw new IllegalArgumentException("array is null, can't sort");
		if (array.length <= 1)
			return 0;

		int[] left = Arrays.copyOfRange(array, 0, array.length / 2);
		int[] right = Arrays.copyOfRange(array, array.length / 2, array.length);

		int leftInversions = inversionHelper(left);
		int rightInversions = inversionHelper(right);
		int mergeInversions = merge(array, left, right);

		return leftInversions + rightInversions + mergeInversions;
	}

	private static int merge(int[] array, int[] left, int[] right) {
		int i = 0;
		int j = 0;
		int k = 0;
		int acum = 0;

		while (i < left.length && j < right.length) {
			if (left[i] <= right[j]) {
				array[k++] = left[i++];
			} else {
				array[k++] = right[j++];
				acum += left.length - i;
			}
		}

		while (i < left.length) {
			array[k++] = left[i++];
		}

		while (j < right.length) {
			array[k++] = right[j++];
		}

		return acum;
	}
}
