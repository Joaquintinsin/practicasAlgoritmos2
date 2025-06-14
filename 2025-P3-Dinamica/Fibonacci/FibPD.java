public class FibPD {
  static int fib_wanted = 100;

  public static void main(String[] args) {
    if (fib_wanted == 0) {
      System.out.println("fib(0) = 0");
      return;
    }

    Long fib_n1 = 0L;
    Long fib_n2 = 1L;
    Long fib_acum = 0L;
    for (int i = 1; i < fib_wanted; i++) {
      fib_acum = fib_n1 + fib_n2;
      fib_n1 = fib_n2;
      fib_n2 = fib_acum;
      // System.out.println("fib(" + i + ") = " + fib_acum);
    }
    System.out.println("Final fib(" + fib_wanted + ") = " + fib_n2);

    Long[] fib = new Long[fib_wanted + 1];
    fib[0] = 0L;
    fib[1] = 1L;
    for (int i = 2; i <= fib_wanted; i++) {
      fib[i] = fib[i - 1] + fib[i - 2];
      // System.out.println("fib[" + i + "] = " + fib[i]);
    }
    System.out.println("Final fib(" + fib_wanted + ") = " + fib[fib_wanted]);
  }
}
