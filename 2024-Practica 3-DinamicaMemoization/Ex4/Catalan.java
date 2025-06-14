import java.util.Map;
import java.util.HashMap;

public class Catalan {
  // Computes Catalan of a given n
  public static int c(int n) {
    if (n == 0 || n == 1) return 1; // Casos base
    int[] arrAux = new int[n + 1];
    arrAux[0] = 1;  // Guardo los valores previos más pequeños para luego utilizarlos
    arrAux[1] = 1;  // Como lo hago iterativo, estoy haciendo la forma de tabulacion
    // C(n) = Sumatoria (k=1, n) (C(k-1) * C(n-k))
    for (int i = 2; i <= n; i++) {
      for (int k = 0; k < i; k++) {
        arrAux[i] += arrAux[k] * arrAux[i - k - 1];
      }
    }
    return arrAux[n];
  }

  //~ public static int memoCatalan(int n, Integer[] t) {
    //~ if (n == 0 || n == 1)
      //~ return 1;
    //~ if (t[n] != null)
      //~ return t[n];

    //~ int result = 0;
    //~ for (int i = 0; i < n; i++)
      //~ result += memoCatalan(i, t) * memoCatalan(n - i - 1, t);

    //~ return t[n] = result;
  //~ }
  //~ private static Map<Integer, Integer> cache = new HashMap<>();
  //~ public static int memoCatalan(int n) {
    //~ if (n == 0 || n == 1) return 1;
    //~ cache.put(0, 1);
    //~ cache.put(1, 1);
    //~ int res = 0;
    //~ for (int i = 2; i <= n; i++) {
      //~ for (int k = 0; k < i; k++) {
        //~ res = memoProgram(i, k) * memoProgram(i, i - k - 1);
      //~ }
    //~ }
    //~ return res;
  //~ }

  //~ private static int memoProgram(int i, int k) {
    //~ if (!cache.containsKey(i)) {
      //~ cache.put(i, k);
    //~ }
    //~ return cache.get(i);
  //~ }

  public static void main (String[] args) {
    int numCatalan = 5;
    System.out.println("Catalan " + numCatalan + " = " + c(numCatalan));
    Integer[] t = new Integer[numCatalan + 1];
    System.out.println("Memo Catalan " + numCatalan + " = " + memoCatalan(numCatalan, t));
  }
}




