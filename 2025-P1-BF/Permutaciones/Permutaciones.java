import java.util.Arrays;

public class Permutaciones {

  public static void permute(int[] arr, int i) {
    if (i == arr.length) {
      System.out.println(Arrays.toString(arr));
      return;
    }

    for (int j = i; j < arr.length; j++) {
      swap(arr, i, j);
      permute(arr, i + 1);
      swap(arr, i, j); // backtrack
    }
  }

  public static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  public static void main(String[] args) {
    int[] arr = {1, 2, 3, 4, 5, 6};
    permute(arr, 0);
  }
}
