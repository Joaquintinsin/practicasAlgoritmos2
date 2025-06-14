import java.util.*;
import java.lang.Math;

public class OrdenRelativo {
  private static HashMap<Tupla, Integer> cache = new HashMap<>();

  public static int gradeDC (int[] secAlumno) {
    if (secAlumno == null) throw new IllegalArgumentException("El alumno debe dar una respuesta");
    int[] sortedSequence = new int[secAlumno.length];
    for (int i = 0 ; i < secAlumno.length ; i++) {
      sortedSequence[i] = secAlumno[i];
    }
    Arrays.sort(sortedSequence);
    return gradeDC(sortedSequence, secAlumno, 0);
  }

  private static int gradeDC (int[] solucionProfesor, int[] secAlumno, int index) {
    if (solucionProfesor.length == 0 || secAlumno.length == 0 || index == solucionProfesor.length || index == secAlumno.length) {
      return 0;
    } else {
      if (solucionProfesor[index] == secAlumno[index]) {
        return 1 + gradeDC(solucionProfesor, secAlumno, index + 1);
      } else {
        int[] copyAlumno = Arrays.copyOfRange(secAlumno, 1, secAlumno.length);
        int[] copyProfesor = Arrays.copyOfRange(solucionProfesor, 1, solucionProfesor.length);
        return Math.max(gradeDC(solucionProfesor, copyAlumno, index), gradeDC(copyProfesor, secAlumno, index));
      }
    }
  }

  public static int gradeMemo (int[] secAlumno) {
    if (secAlumno == null) throw new IllegalArgumentException("El alumno debe dar una respuesta");
    int[] sortedSequence = new int[secAlumno.length];
    for (int i = 0 ; i < secAlumno.length ; i++) {
      sortedSequence[i] = secAlumno[i];
    }
    Arrays.sort(sortedSequence);
    return gradeMemo(sortedSequence, secAlumno, 0);
  }

  private static int gradeMemo (int[] solucionProfesor, int[] secAlumno, int index) {
    if (solucionProfesor.length == 0 || secAlumno.length == 0 || index == solucionProfesor.length || index == secAlumno.length) {
      return 0;
    } else {
      if (solucionProfesor[index] == secAlumno[index]) {
        return 1 + memoProgram(solucionProfesor, secAlumno, index + 1);
      } else {
        int[] copyAlumno = Arrays.copyOfRange(secAlumno, 1, secAlumno.length);
        int[] copyProfesor = Arrays.copyOfRange(solucionProfesor, 1, solucionProfesor.length);
        return Math.max(memoProgram(solucionProfesor, copyAlumno, index), memoProgram(copyProfesor, secAlumno, index));
      }
    }
  }

  private static int memoProgram(int[] xs, int[] ys, int ind) {
    if (!cache.containsKey(new Tupla(xs, ys))) {
      cache.put(new Tupla(xs, ys), gradeMemo(xs, ys, ind));
    }
    return cache.get(new Tupla(xs, ys));
  }

  public static void main (String[] args) {
    int[] secuenciaAlumno1 = new int[4];
    secuenciaAlumno1[0] = 1;
    secuenciaAlumno1[1] = 2;
    secuenciaAlumno1[2] = 3;
    secuenciaAlumno1[3] = 4;
    int[] secuenciaAlumno2 = new int[4];
    secuenciaAlumno2[0] = 1;
    secuenciaAlumno2[1] = 3;
    secuenciaAlumno2[2] = 2;
    secuenciaAlumno2[3] = 4;
    int[] secuenciaAlumno3 = new int[4];
    secuenciaAlumno3[0] = 4;
    secuenciaAlumno3[1] = 3;
    secuenciaAlumno3[2] = 2;
    secuenciaAlumno3[3] = 1;
    int r1 = gradeDC(secuenciaAlumno1);
    System.out.println("Secuencia alumno1: ");
    for (int i = 0 ; i < secuenciaAlumno1.length ; i++) {
      System.out.print(secuenciaAlumno1[i]);
    }
    System.out.println();
    System.out.println("Puntaje alumno1 : " + r1);
    int r2 = gradeDC(secuenciaAlumno2);
    System.out.println("Secuencia alumno2: ");
    for (int i = 0 ; i < secuenciaAlumno2.length ; i++) {
      System.out.print(secuenciaAlumno2[i]);
    }
    System.out.println();
    System.out.println("Puntaje alumno2 : " + r2);
    int r3 = gradeDC(secuenciaAlumno3);
    System.out.println("Secuencia alumno3: ");
    for (int i = 0 ; i < secuenciaAlumno3.length ; i++) {
      System.out.print(secuenciaAlumno3[i]);
    }
    System.out.println();
    System.out.println("Puntaje alumno3 : " + r3);
  }
}
