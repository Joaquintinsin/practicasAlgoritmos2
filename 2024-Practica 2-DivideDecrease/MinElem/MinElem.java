import java.lang.Math;

public class MinElem {
  public static int minElem(int[] xs) {
    return divide(xs, 0, xs.length - 1);
  }

  private static int divide(int[] xs, int ini, int end) {
    if (ini < end) {
      int mid = (ini + end) / 2;
      int minLeft = divide(xs, ini, mid);
      int minRight = divide(xs, mid + 1, end);
      return Math.min(minLeft, minRight);
    } else if (ini == end) {
      return xs[ini];
    } else {
      throw new IllegalArgumentException("Indices invalidos");
    }
  }
  
  public static int minElemDecrease(int[] xs) {
    return decrease(xs, 0, xs.length - 1);
  }
  
  private static int decrease(int[] xs, int ini, int end) {
    if (ini < end) {
      int partialIniMin = divide(xs, ini + 1, end);
      int partialEndMin = divide(xs, ini, end - 1);
      return Math.min(partialEndMin, partialIniMin);
    } else if (ini == end) {
      return xs[ini];
    } else {
      throw new IllegalArgumentException("Indices invalidos");
    }
  }

  public static void main(String[] args) {
    int[] arr = new int[] {7,1,2,3,4,8,5,4,3,2,1,-1,6};
    System.out.println("MinElem con Divide de arr: " + minElem(arr));
    System.out.println("MinElem con Decrease de arr: " + minElemDecrease(arr));
  }
}
