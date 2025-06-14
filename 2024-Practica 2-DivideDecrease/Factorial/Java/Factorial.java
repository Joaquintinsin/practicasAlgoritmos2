public class Factorial {
  //~ Divide and conquer
  public static long factorialDivideConquer(int n) {
    if (n < 0) {
      throw new IllegalArgumentException("El factorial no está definido para números negativos");
    }
    return divideConquer(1, n);
  }

  private static long divideConquer(int ini, int end) {
    if (ini == end) {
      return ini;
    } else {
      int mid = (ini + end) / 2;
      return divideConquer(ini, mid) * divideConquer(mid + 1, end);
    }
  }

  //~ Decrease and conquer
  public static long factorialDecreaseConquer(int n) {
    if (n < 0) {
      throw new IllegalArgumentException("El factorial no está definido para números negativos");
    }
    return decreaseConquer(1, n);
  }

  private static long decreaseConquer(long acc, int n) {
    if (n == 1) {
      return acc;
    } else {
      return decreaseConquer(acc * n, n - 1);
    }
  }

  public static void main(String[] args) {
    System.out.println("Factorial usando Divide and Conquer:");
    System.out.println(Factorial.factorialDivideConquer(5));

    System.out.println("Factorial usando Decrease and Conquer:");
    System.out.println(Factorial.factorialDecreaseConquer(5));
  }
}
