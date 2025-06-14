import java.util.Arrays;

public class FakeCoin {
  public static void main(String[] args) {
    int[] monedas = new int[] { 1, 1, 1, 1, 1, 0, 1 };
    int indiceMonedaFalsa = f(monedas);
    System.out.println("El indice de la moneda falsa es: " + indiceMonedaFalsa);
  }

  public static int f(int[] monedas) {
    return g(monedas, 0, monedas.length - 1);
  }

  private static int g(int[] m, int i, int n) {
    if (i == n) {
      return i;
    }
    if (i + 1 == n) {
      return (m[i] < m[n]) ? i : n;
    }

    int mid = i + (n - i) / 2;

    int pesoLeft = pesar(m, i, mid - 1);
    int pesoRight = pesar(m, mid + 1, n);

    if (pesoLeft == (mid - i) && pesoRight == (n - mid)) {
      return mid;
    } else if (pesoLeft < (mid - i)) {
      return g(m, 0, mid - 1);
    } else {
      return g(m, mid + 1, n);
    }
  }

  private static int pesar(int[] a, int inicio, int fin) {
    int res = 0;
    for (int i = inicio; i <= fin; i++){
      res += a[i];
    }
    return res;
  }
}
