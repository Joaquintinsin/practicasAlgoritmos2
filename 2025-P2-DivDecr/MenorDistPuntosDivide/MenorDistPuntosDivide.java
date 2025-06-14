import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MenorDistPuntosDivide {
  private int cantPuntos = 0;
  private Pair<Integer, Integer>[] puntos;

  public MenorDistPuntosDivide(Pair<Integer, Integer>[] puntos) {
    if (puntos == null) {
      throw new NullPointerException("El arreglo de puntos no puede ser nulo");
    }
    if (puntos.length < 2) {
      throw new UnsupportedOperationException("El arreglo de puntos debe tener al menos dos elementos");
    }
    this.puntos = puntos;
    this.cantPuntos = puntos.length;
  }

  public Pair<Integer, Integer>[] bruteForceEuclid(Pair<Integer, Integer>[] arr) {
    Pair<Integer, Integer>[] min = new Pair[2];
    min[0] = arr[0];
    min[1] = arr[1];
    for (int i = 0; i < arr.length; i++) {
      Pair<Integer, Integer> A = arr[i];
      for (int j = i + 1; j < arr.length; j++) {
        Pair<Integer, Integer> B = arr[j];
        if (distEucl(A, B) < distEucl(min[0], min[1])) {
          min[0] = A;
          min[1] = B;
        }
      }
    }
    return min;
  }

  public Pair<Integer, Integer>[] bruteForceEuclid() {
    Pair<Integer, Integer>[] min = new Pair[2];
    min[0] = this.puntos[0];
    min[1] = this.puntos[1];
    for (int i = 0; i < this.puntos.length; i++) {
      Pair<Integer, Integer> A = this.puntos[i];
      for (int j = i + 1; j < this.puntos.length; j++) {
        Pair<Integer, Integer> B = this.puntos[j];
        if (distEucl(A, B) < distEucl(min[0], min[1])) {
          min[0] = A;
          min[1] = B;
        }
      }
    }
    return min;
  }

  public Pair<Integer, Integer>[] divideAndConquerEuclid() {
    return closestPairs(ordCrecienteP(this.puntos), ordCrecienteQ(this.puntos));
  }

  public Pair<Integer, Integer>[] divideAndConquerEuclid(Pair<Integer, Integer>[] arr) {
    return closestPairs(ordCrecienteP(arr), ordCrecienteQ(arr));
  }

  private Pair<Integer, Integer>[] casoBase(Pair<Integer, Integer>[] arr) {
    return bruteForceEuclid(arr);
  }

  private Pair<Integer, Integer>[] closestPairs(Pair<Integer, Integer>[] P, Pair<Integer, Integer>[] Q) {
    Set<Pair<Integer, Integer>> pLSet = new HashSet<>();
    Set<Pair<Integer, Integer>> pRSet = new HashSet<>();

    if (P.length <= 3) {
      return casoBase(P);
    } else {
      Pair<Integer, Integer>[] pL = copiarPLSET(P, 0, (int) Math.ceil(cantPuntos / 2), pLSet);
      Pair<Integer, Integer>[] qL = copySameQL(Q, (int) Math.ceil(cantPuntos / 2), pLSet);
      Pair<Integer, Integer>[] pR = copiarPRSET(P, (int) Math.floor(cantPuntos / 2), cantPuntos, pRSet);
      Pair<Integer, Integer>[] qR = copySameQR(Q, (int) Math.floor(cantPuntos / 2), pRSet);

      Pair<Integer, Integer>[] dL = closestPairs(pL, qL);
      Pair<Integer, Integer>[] dR = closestPairs(pR, qR);

      Pair<Integer, Integer>[] d = min(dL, dR);
      Integer m = P[(int) Math.ceil(cantPuntos / 2) - 1].getFirst();

      Pair<Integer, Integer>[] arrayS = copyAll(Q, m, d);
      Integer num = arrayS.length;
      double dminsq = distEucl(d[0], d[1]);
      Pair<Integer, Integer>[] finalResult = new Pair[2];
      for (int i = 0; i < num; i++) {
        int k = i + 1;
        while (k < num && Math.pow(arrayS[i].snd() - arrayS[k].snd(), 2) < dminsq) {
          double dist_Si_Sk = distEucl(arrayS[i], arrayS[k]);
          dminsq = Math.min(dist_Si_Sk, dminsq);
          finalResult[0] = arrayS[i];
          finalResult[1] = arrayS[k];
          k++;
        }
      }
      return finalResult;
    }
  }

  private static Pair<Integer, Integer>[] ordCrecienteP(Pair<Integer, Integer>[] array) {
    if (array.length <= 1) {
      return array;
    }

    int mid = array.length / 2;
    Pair<Integer, Integer>[] left = Arrays.copyOfRange(array, 0, mid);
    Pair<Integer, Integer>[] right = Arrays.copyOfRange(array, mid, array.length);

    return mergeByFst(ordCrecienteP(left), ordCrecienteP(right));
  }

  private static Pair<Integer, Integer>[] mergeByFst(Pair<Integer, Integer>[] left, Pair<Integer, Integer>[] right) {
    Pair<Integer, Integer>[] result = (Pair<Integer, Integer>[]) new Pair[left.length + right.length];
    int i = 0, j = 0, k = 0;
    while (i < left.length && j < right.length) {
      if (left[i].fst() <= right[j].fst()) {
        result[k++] = left[i++];
      } else {
        result[k++] = right[j++];
      }
    }
    while (i < left.length) {
      result[k++] = left[i++];
    }
    while (j < right.length) {
      result[k++] = right[j++];
    }
    return result;
  }

  private static Pair<Integer, Integer>[] ordCrecienteQ(Pair<Integer, Integer>[] array) {
    if (array.length <= 1) {
      return array;
    }

    int mid = array.length / 2;
    Pair<Integer, Integer>[] left = Arrays.copyOfRange(array, 0, mid);
    Pair<Integer, Integer>[] right = Arrays.copyOfRange(array, mid, array.length);

    return mergeBySnd(ordCrecienteQ(left), ordCrecienteQ(right));
  }

  private static Pair<Integer, Integer>[] mergeBySnd(Pair<Integer, Integer>[] left, Pair<Integer, Integer>[] right) {
    Pair<Integer, Integer>[] result = (Pair<Integer, Integer>[]) new Pair[left.length + right.length];
    int i = 0, j = 0, k = 0;

    while (i < left.length && j < right.length) {
      if (left[i].snd() <= right[j].snd()) {
        result[k++] = left[i++];
      } else {
        result[k++] = right[j++];
      }
    }

    while (i < left.length) {
      result[k++] = left[i++];
    }

    while (j < right.length) {
      result[k++] = right[j++];
    }

    return result;
  }

  private Pair<Integer, Integer>[] copiarPLSET(Pair<Integer, Integer>[] arr, int i, int j,
      Set<Pair<Integer, Integer>> pLSet) {
    for (Pair<Integer, Integer> p : arr) {
      pLSet.add(p);
    }
    return Arrays.copyOfRange(arr, i, j);
  }

  private Pair<Integer, Integer>[] copiarPRSET(Pair<Integer, Integer>[] arr, int i, int j,
      Set<Pair<Integer, Integer>> pRSet) {
    for (Pair<Integer, Integer> p : arr) {
      pRSet.add(p);
    }
    return Arrays.copyOfRange(arr, i, j);
  }

  private Pair<Integer, Integer>[] copySameQL(Pair<Integer, Integer>[] q, int until,
      Set<Pair<Integer, Integer>> pLSet) {
    Pair<Integer, Integer>[] copyArray = new Pair[until];
    for (int i = 0; i < until; i++) {
      if (pLSet.contains(copyArray[i])) {
        copyArray[i] = q[i];
      }
    }
    return copyArray;
  }

  private Pair<Integer, Integer>[] copySameQR(Pair<Integer, Integer>[] q, int until,
      Set<Pair<Integer, Integer>> pRSet) {
    Pair<Integer, Integer>[] copyArray = new Pair[until];
    for (int i = 0; i < until; i++) {
      if (pRSet.contains(copyArray[i])) {
        copyArray[i] = q[i];
      }
    }
    return copyArray;
  }

  private Pair<Integer, Integer>[] copyAll(Pair<Integer, Integer>[] q, Integer m, Pair<Integer, Integer>[] d) {
    List<Pair<Integer, Integer>> copyArray = new ArrayList<>();
    for (Pair<Integer, Integer> pair : q) {
      if (Math.abs(pair.fst() - m) < d[0].fst()) {
        copyArray.add(pair);
      }
    }
    Pair<Integer, Integer>[] result = new Pair[copyArray.size()];
    for (int i = 0; i < copyArray.size(); i++) {
      result[i] = copyArray.get(i);
    }
    return result;
  }

  private Pair<Integer, Integer>[] min(Pair<Integer, Integer>[] s1, Pair<Integer, Integer>[] s2) {
    Pair<Integer, Integer>[] minPair = new Pair[2];
    double min = Integer.MAX_VALUE;
    for (Pair<Integer, Integer> p1 : s2) {
      for (Pair<Integer, Integer> p2 : s1) {
        double dist = distEucl(p1, p2);
        if (dist < min) {
          minPair[0] = p1;
          minPair[1] = p2;
          min = dist;
        }
      }
    }
    return minPair;
  }

  private double distEucl(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
    if (p1 == null) {
      throw new NullPointerException("Punto1 null");
    }
    if (p2 == null) {
      throw new NullPointerException("Punto2 null");
    }
    return Math.sqrt(Math.pow(p1.fst() - p2.fst(), 2) + Math.pow(p1.snd() - p2.snd(), 2));
  }

  public static void main(String[] args) {
    Pair<Integer, Integer>[][] testArrPuntos = new Pair[7][];

    Pair<Integer, Integer>[] puntos1 = new Pair[] {
        new Pair<>(1, 2),
        new Pair<>(4, 6),
        new Pair<>(7, 1),
        new Pair<>(3, 5),
        new Pair<>(2, 8),
        new Pair<>(1, 1)
    };
    Pair<Integer, Integer>[] puntos2 = new Pair[] {
        new Pair<>(2, 3),
        new Pair<>(12, 30),
        new Pair<>(40, 50),
        new Pair<>(5, 1),
        new Pair<>(12, 10),
        new Pair<>(3, 4)
    };
    Pair<Integer, Integer>[] puntos3 = new Pair[] {
        new Pair<>(2, 3),
        new Pair<>(12, 30),
        new Pair<>(40, 50),
        new Pair<>(5, 1),
        new Pair<>(12, 10),
        new Pair<>(3, 4)
    };
    Pair<Integer, Integer>[] puntos4 = new Pair[] {
        new Pair<>(1, 10),
        new Pair<>(2, 10),
        new Pair<>(3, 10),
        new Pair<>(4, 10),
        new Pair<>(5, 10),
        new Pair<>(100, 10)
    };
    Pair<Integer, Integer>[] puntos5 = new Pair[] {
      new Pair<>(20, 20),
      new Pair<>(15, 35),
      new Pair<>(9, 9),
      new Pair<>(30, 40),
      new Pair<>(22, 21),
      new Pair<>(21, 21)
    };
    Pair<Integer, Integer>[] puntos6 = new Pair[] {
      new Pair<>(-10, -10),
      new Pair<>(-20, 5),
      new Pair<>(5, -20),
      new Pair<>(15, 15),
      new Pair<>(0, 0),
      new Pair<>(1, 1)
    };
    Pair<Integer, Integer>[] puntos7 = new Pair[] {
        new Pair<>(0, 0),
        new Pair<>(1, 1),
        new Pair<>(1, 2),
        new Pair<>(2, 2),
        new Pair<>(2, 3),
        new Pair<>(3, 3),
        new Pair<>(3, 4)
    };

    testArrPuntos[0] = puntos1;
    testArrPuntos[1] = puntos2;
    testArrPuntos[2] = puntos3;
    testArrPuntos[3] = puntos4;
    testArrPuntos[4] = puntos5;
    testArrPuntos[5] = puntos6;
    testArrPuntos[6] = puntos7;

    Pair<Integer, Integer>[] divideResultado;
    Pair<Integer, Integer>[] bruteForceResultado;

    int i = 0;
    for (Pair<Integer, Integer>[] puntos : testArrPuntos) {
      i++;
      bruteForceResultado = solverBruteForce(puntos);
      printResults(i, bruteForceResultado, "Fuerza Bruta");
      divideResultado = solverDivide(puntos);
      printResults(i, divideResultado, "Divide and Conquer");
    }
  }

  public static void printResults(int index, Pair<Integer, Integer>[] resultado, String technique) {
    System.out.println("Caso " + index + ": Puntos más cercanos usando " + technique + ":");
    for (Pair<Integer, Integer> p : resultado) {
      System.out.println(p);
    }
  }

  public static Pair<Integer, Integer>[] solverBruteForce(Pair<Integer, Integer>[] puntos) {
    MenorDistPuntosDivide solver = new MenorDistPuntosDivide(puntos);
    return solver.bruteForceEuclid();
  }

  public static Pair<Integer, Integer>[] solverDivide(Pair<Integer, Integer>[] puntos) {
    MenorDistPuntosDivide solver = new MenorDistPuntosDivide(puntos);
    return solver.divideAndConquerEuclid();
  }
}
