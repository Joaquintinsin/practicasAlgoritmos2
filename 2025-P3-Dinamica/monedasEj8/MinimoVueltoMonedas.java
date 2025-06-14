import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MinimoVueltoMonedas {
  /**
   * Longitud del arreglo de monedas o la moneda de maxima denominacion para dar
   * cambio
   */
  private int maximaDenominacionMonedas;
  /**
   * Arreglo con las monedas para dar cambio
   * Una vez construida la clase, el arreglo atributo queda ordenado de manera
   * ascendente
   */
  private int[] valoresMonedas;

  /**
   * Constructor para la clase
   *
   * El atributo valoresMonedas queda con el
   * arreglo parametro de manera ordenada
   * ascendente (Arrays.sort)
   *
   * @param valoresMonedas un arreglo de enteros con denominaciones, ejemplo [1,
   *                       10, 5, 2]: tenes monedas de 1, 10, 5 y 2 para dar
   *                       cambio
   * @throws IllegalArgumentException si el arreglo es null, es vacio o hay
   *                                  monedas negativas
   */
  public MinimoVueltoMonedas(int[] valoresMonedas) {
    // arreglo null
    if (valoresMonedas == null) {
      throw new IllegalArgumentException("Arreglo null");
    }
    // arreglo vacio
    if (valoresMonedas.length == 0) {
      throw new IllegalArgumentException("Arreglo vacio");
    }
    // arreglo con monedas negativas
    Arrays.sort(valoresMonedas);
    if (valoresMonedas[0] <= 0) {
      throw new IllegalArgumentException("Arreglo con monedas negativas");
    }
    // arreglo valido: continuamos (queda ordenado por el .sort)

    // nos pasan las monedas que tenemos para dar cambio, ejemplo
    // valoresMonedas = [1, 5, 10, 20]
    this.maximaDenominacionMonedas = valoresMonedas.length;
    this.valoresMonedas = Arrays.copyOf(valoresMonedas, valoresMonedas.length);
  }

  /**
   * Metodo publico con solucion divide al problema de dar cambio
   *
   * @param C el total de cambio que se quiere
   * @return la minima cantidad de monedas usadas para dar cambio
   */
  public int optimaFormaCambioDivide(int C) {
    return helperDivide(C, 0, maximaDenominacionMonedas - 1);
  }

  private int helperDivide(int C, int inicio, int fin) {
    // caso base, el cambio que tengo que dar es 0 o ya devolvi la cantidad total
    // pedida
    if (C == 0) {
      return 0;
    }
    // neutro del minimo
    // porque se pasan los indices o porque el cambio a dar es negativo (me pase de
    // cambio por ejemplo)
    if (C < 0 || inicio > fin) {
      return Integer.MAX_VALUE;
    }

    // si tengo 1 sola denominacion de moneda para dar
    if (inicio == fin) {
      int unicaMoneda = valoresMonedas[inicio];
      // devuelvo la division entre C y la unica moneda si son multiplos
      // el neutro del minimo en otro caso.
      return (C % unicaMoneda == 0) ? C / unicaMoneda : Integer.MAX_VALUE;
    }

    // sentencias divide

    int mid = (inicio + fin) / 2;
    // caso inductivo: revisa cuantas monedas devolves del lado izquierdo y cuantas
    // del lado derecho
    int cantMonedasIzquierda = helperDivide(C, inicio, mid);
    int cantMonedasDerecha = helperDivide(C, mid + 1, fin);
    // usar ambos lados: probar todas las divisiones del monto
    int cantMonedasAmbosLados = Integer.MAX_VALUE;

    // sumatoria del lado izquierdo y el lado derecho, desde 1 hasta el cambio que
    // tengo que dar
    for (int i = 1; i < C; i++) {
      // lado izquierdo
      int izq = helperDivide(i, inicio, mid);
      // lado derecho
      int der = helperDivide(C - i, mid + 1, fin);
      // si encontre que del lado izquierdo y del lado derecho puedo dar monedas,
      // pregunto por la minima cantidad total encontrada,
      // min(cantMonedasAmbosLados, izq+der)
      if (izq != Integer.MAX_VALUE && der != Integer.MAX_VALUE) {
        cantMonedasAmbosLados = Math.min(cantMonedasAmbosLados, izq + der);
      }
    }

    return Math.min(cantMonedasDerecha, Math.min(cantMonedasIzquierda, cantMonedasAmbosLados));
  }

  /**
   * Metodo publico con solucion dynamic programming al problema de dar cambio
   *
   * @param C el total de cambio que se quiere
   * @return la minima cantidad de monedas usadas para dar cambio
   */
  public int optimaFormaCambioPD(int C) {
    int[] dp = new int[C + 1];

    dp[0] = 0;
    for (int i = 1; i <= C; i++) {
      Integer temp = Integer.MAX_VALUE;
      int j = 0;
      while (j < valoresMonedas.length && i >= valoresMonedas[j]) {
        temp = Math.min(dp[i - valoresMonedas[j]], temp);
        j++;
      }
      dp[i] = temp + 1;
    }
    return dp[C];
  }

  /**
   * Metodo publico con solucion memoization al problema de dar cambio
   *
   * @param C el total de cambio que se quiere
   * @return la minima cantidad de monedas usadas para dar cambio
   */

  public int optimaFormaCambioMemo(int C) {
    Map<Integer, Integer> cache = new HashMap<>();
    return helperMemo(C, 0, maximaDenominacionMonedas - 1, cache);
  }

  private int helperMemo(int C, int inicio, int fin, Map<Integer, Integer> cache) {
    // caso base, el cambio que tengo que dar es 0 o ya devolvi la cantidad total
    // pedida
    if (C == 0) {
      return 0;
    }
    // neutro del minimo
    // porque se pasan los indices o porque el cambio a dar es negativo (me pase de
    // cambio por ejemplo)
    if (C < 0 || inicio > fin) {
      return Integer.MAX_VALUE;
    }
    // si tengo 1 sola denominacion de moneda para dar
    if (inicio == fin) {
      int unicaMoneda = valoresMonedas[inicio];
      // devuelvo la division entre C y la unica moneda si son multiplos
      // el neutro del minimo en otro caso.
      return (C % unicaMoneda == 0) ? C / unicaMoneda : Integer.MAX_VALUE;
    }

    if (!cache.containsKey(C)) {
      // sentencias divide
      int mid = (inicio + fin) / 2;
      // caso inductivo: revisa cuantas monedas devolves del lado izquierdo y cuantas
      // del lado derecho
      int cantMonedasIzquierda = helperMemo(C, inicio, mid, cache);
      int cantMonedasDerecha = helperMemo(C, mid + 1, fin, cache);
      // usar ambos lados: probar todas las divisiones del monto
      int cantMonedasAmbosLados = Integer.MAX_VALUE;
      // sumatoria del lado izquierdo y el lado derecho, desde 1 hasta el cambio que
      // tengo que dar
      for (int i = 1; i < C; i++) {
        // lado izquierdo
        int izq = helperMemo(i, inicio, mid, cache);
        // lado derecho
        int der = helperMemo(C - i, mid + 1, fin, cache);
        // si encontre que del lado izquierdo y del lado derecho puedo dar monedas,
        // pregunto por la minima cantidad total encontrada,
        // min(cantMonedasAmbosLados, izq+der)
        if (izq != Integer.MAX_VALUE && der != Integer.MAX_VALUE) {
          cantMonedasAmbosLados = Math.min(cantMonedasAmbosLados, izq + der);
        }
      }
      cache.put(C, Math.min(cantMonedasDerecha, Math.min(cantMonedasIzquierda, cantMonedasAmbosLados)));
    }

    return cache.get(C);
  }

  public static void main(String[] args) {
    int[] setMonedas = new int[] { 1, 3, 5, 10, 15 };
    int CVuelto = 18;

    MinimoVueltoMonedas mvm = new MinimoVueltoMonedas(setMonedas);

    int minVuelto = mvm.optimaFormaCambioDivide(CVuelto);
    System.out.println("cant minima de monedas q te doy: " + minVuelto);

    int minVueltoPD = mvm.optimaFormaCambioPD(CVuelto);
    System.out.println("cant minima de monedas q te doy: " + minVueltoPD);

    int minVueltoMemo = mvm.optimaFormaCambioMemo(CVuelto);
    System.out.println("cant minima de monedas q te doy: " + minVueltoMemo);
  }
}
