public class MultiplicacionDecrease {
  static int a;
  static int b;

  static int constantDecrementDecrease(int a, int b) {
    if (a < 0 || b < 0) {
      System.out.println("Los números deben ser positivos.");
      return -1;
    }

    return (b == 0) ? 0 : a + constantDecrementDecrease(a, b - 1);
  }

  static int factorDecrementDecrease(int a, int b) {
    if (b == 0) {
      return 0;
    } else if (par(b)) {
      return factorDecrementDecrease(a * 2, b / 2);
    } else {
      return a + factorDecrementDecrease(a * 2, b / 2);
    }
  }

  static boolean par(int n) {
    return n % 2 == 0;
  }

  static boolean impar(int n) {
    return n % 2 != 0;
  }

  public static void main(String[] args) {
    int a = 10;
    int b = 5;

    int res = constantDecrementDecrease(a, b);
    System.out.println("El resultado es: " + res);

    if (impar(a)) {
      res = (a / 2) * (b * 2);
      System.out.println("El resultado es: " + res);

      int factorCte = 4;
      int res2 = (a / factorCte) * (b * factorCte);
    } else {

    }


  }
}
