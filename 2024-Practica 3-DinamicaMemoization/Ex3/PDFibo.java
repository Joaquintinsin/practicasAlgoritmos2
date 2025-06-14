public class PDFibo {
  public static int dpFibo(int n) {
    if (n < 0) throw new IllegalArgumentException("Fibonnaci no definido para números negativos");
    if (n == 0) return 0;
    if (n == 1) return 1;
    int[] cache = new int[n];
    cache[0] = 0;
    cache[1] = 1;
    for (int i = 2; i < n; i++) {
      cache[i] = cache[i - 2] + cache[i - 1];
    }
    return cache[n - 1];
  }

  public static void main(String[] args) {
    int nroFib = 3;
    int res = dpFibo(nroFib);
    System.out.println("Fibonacci con programación dinámica de " + nroFib + " = " + res);
  }
}
