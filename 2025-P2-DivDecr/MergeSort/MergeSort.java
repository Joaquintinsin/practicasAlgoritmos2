import java.util.Arrays;

public class MergeSort {
  public static void ordenar(int[] a) {
    if (a.length == 0) return;
    if (a.length == 1) return;
    if (a.length == 2) {
      if (a[0] > a[1]) {
        int tmp = a[0];
        a[0] = a[1];
        a[1] = tmp;
      }
      return;
    }
    int[] left = Arrays.copyOfRange(a, 0, a.length / 2);
    int[] right = Arrays.copyOfRange(a, a.length / 2, a.length);
    ordenar(left);
    ordenar(right);
    int[] merged = merge(left, right);
    System.arraycopy(merged, 0, a, 0, a.length);
    return;
  }

  private static int[] merge(int[] L, int[] R) {
    int[] result = new int[L.length + R.length];
    int i = 0, j = 0, k = 0;

    while (i < L.length && j < R.length) {
      if (L[i] <= R[j]) {
        result[k++] = L[i++];
      } else {
        result[k++] = R[j++];
      }
    }

    while (i < L.length) {
      result[k++] = L[i++];
    }

    while (j < R.length) {
      result[k++] = R[j++];
    }

    return result;
  }

  public static void main(String args[]) {
    // created an unsorted array
    int[] array = { 6, 5, 12, 10, 9, 1 };
    MergeSort.ordenar(array);
    System.out.println("Sorted Array:");
    System.out.println(Arrays.toString(array));
  }
}
