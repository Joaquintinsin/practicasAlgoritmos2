import java.util.HashMap;
import java.util.Map;

public class Vinoteca {
  /** Arreglo de precios: precio del i-esimo vino == p[i] */
  private int[] p;

  public Vinoteca(int[] precioVinos) {
    this.p = precioVinos;
  }

  /////////////////////////////////
  /////////////////////////////////
  /////////////////////////////////

  public int maximaGananciaDivide() {
    return helperDivide(0, p.length - 1, 1);
  }

  private int helperDivide(int inicio, int fin, int year) {
    if (inicio > fin) {
      return Integer.MIN_VALUE;
    }
    if (inicio == fin) {
      return p[inicio] * year;
    }
    int gananciaPrimerVino = helperDivide(inicio + 1, fin, year + 1) + p[inicio] * year;
    int gananciaUltimoVino = helperDivide(inicio, fin - 1, year + 1) + p[fin] * year;
    return Math.max(gananciaPrimerVino, gananciaUltimoVino);
  }

  /////////////////////////////////
  /////////////////////////////////
  /////////////////////////////////

  private Map<Integer, Integer> cache = new HashMap<>();

  public int maximaGananciaMemoYear() {
    return helperMemoYear(0, p.length - 1, 1);
  }

  private int helperMemoYear(int inicio, int fin, int year) {
    if (inicio > fin) {
      return Integer.MIN_VALUE;
    }
    if (inicio == fin) {
      return p[inicio] * year;
    }
    if (!cache.containsKey(year)) {
      int gananciaPrimerVino = helperMemoYear(inicio + 1, fin, year + 1) + p[inicio] * year;
      int gananciaUltimoVino = helperMemoYear(inicio, fin - 1, year + 1) + p[fin] * year;
      cache.put(year, Math.max(gananciaPrimerVino, gananciaUltimoVino));
    }
    return cache.get(year);
  }

  /////////////////////////////////
  /////////////////////////////////
  /////////////////////////////////

  private Map<Pair<Integer, Integer>, Integer> cacheTupla = new HashMap<>();

  public int maximaGananciaMemoTupla() {
    return helperMemoTupla(0, p.length - 1, 1);
  }

  private int helperMemoTupla(int inicio, int fin, int year) {
    Pair<Integer, Integer> t = new Pair<>(inicio, fin);
    if (inicio > fin)
      return Integer.MIN_VALUE;
    if (inicio == fin)
      return p[inicio] * year;
    int gananciaPrimerVino = Integer.MIN_VALUE;
    int gananciaUltimoVino = Integer.MIN_VALUE;
    if (!cacheTupla.containsKey(t)) {
      gananciaPrimerVino = helperMemoTupla(inicio + 1, fin, year + 1) + p[inicio] * year;
      gananciaUltimoVino = helperMemoTupla(inicio, fin - 1, year + 1) + p[fin] * year;
      cacheTupla.put(t, Math.max(gananciaPrimerVino, gananciaUltimoVino));
    }
    return cacheTupla.get(t);
  }

  /////////////////////////////////
  /////////////////////////////////
  /////////////////////////////////

  public static void main(String[] args) {
    Vinoteca v = new Vinoteca(new int[] { 1, 5, 5, 4 });
    int gananciaTotalDivide = v.maximaGananciaDivide();
    System.out.println("ganancia total divide: " + gananciaTotalDivide);

    v = new Vinoteca(new int[] { 1, 5, 5, 4 });
    int gananciaTotalMemo = v.maximaGananciaMemoYear();
    System.out.println("ganancia total memo por año: " + gananciaTotalMemo);

    v = new Vinoteca(new int[] { 1, 5, 5, 4 });
    int gananciaTotalMemoTupla = v.maximaGananciaMemoTupla();
    System.out.println("ganancia total memo por tupla (inicio, fin): " + gananciaTotalMemoTupla);
  }
}
