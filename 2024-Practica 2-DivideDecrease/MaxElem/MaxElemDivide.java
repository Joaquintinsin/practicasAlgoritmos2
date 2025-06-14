import java.lang.Math;

public class MaxElemDivide {
  public static int maxElem(int[] xs) {
    return divide(xs, 0, xs.length - 1);
  }

  private static int divide(int[] xs, int ini, int end) {
    if (ini < end) {
      int mid = (ini + end) / 2;
      int maxLeft = divide(xs, ini, mid);
      int maxRight = divide(xs, mid + 1, end);
      return Math.max(maxLeft, maxRight);
    } else if (ini == end) {
      return xs[ini];
    } else {
      throw new IllegalArgumentException("Indices invalidos");
    }
  }

  public static void main(String[] args) {
    int[] arr = new int[] {7,1,2,3,4,8,5,4,3,2,1,6};
    System.out.println("MaxElemDivide de arr: " + maxElem(arr));
  }
}
