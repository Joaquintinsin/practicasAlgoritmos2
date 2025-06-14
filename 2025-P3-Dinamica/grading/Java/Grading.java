import java.util.Arrays;

public class Grading {
  public static void main(String[] args) {
    int[] solucionAlumno = new int[] { 1, 2, 3, 4, 5, 6 };
    int[] solucionProfesor = solucionAlumno.clone();
    Arrays.sort(solucionProfesor);
    int notaAlumno = gradingDC(solucionAlumno, solucionProfesor);
    System.out.println("nota alumno1 DC: " + notaAlumno);
    int notaAlumnoPD = gradingDP(solucionAlumno, solucionProfesor);
    System.out.println("nota alumno1 PD: " + notaAlumnoPD);
    // Expected: 6

    solucionAlumno = new int[] { 6, 1, 2, 3, 4, 5 };
    solucionProfesor = solucionAlumno.clone();
    Arrays.sort(solucionProfesor);
    notaAlumno = gradingDC(solucionAlumno, solucionProfesor);
    System.out.println("nota alumno2 DC: " + notaAlumno);
    notaAlumnoPD = gradingDP(solucionAlumno, solucionProfesor);
    System.out.println("nota alumno2 PD: " + notaAlumnoPD);
    // Expected: 5

    solucionAlumno = new int[] { 6, 5, 4, 3, 2, 1 };
    solucionProfesor = solucionAlumno.clone();
    Arrays.sort(solucionProfesor);
    notaAlumno = gradingDC(solucionAlumno, solucionProfesor);
    System.out.println("nota alumno3 DC: " + notaAlumno);
    notaAlumnoPD = gradingDP(solucionAlumno, solucionProfesor);
    System.out.println("nota alumno3 PD: " + notaAlumnoPD);
    // Expected: 1

    solucionAlumno = new int[] { 1, 3, 2, 4 };
    solucionProfesor = solucionAlumno.clone();
    Arrays.sort(solucionProfesor);
    notaAlumno = gradingDC(solucionAlumno, solucionProfesor);
    System.out.println("nota alumno4 DC: " + notaAlumno);
    notaAlumnoPD = gradingDP(solucionAlumno, solucionProfesor);
    System.out.println("nota alumno4 PD: " + notaAlumnoPD);
    // Expected: 3
  }

  public static int gradingDC(int[] sA, int[] sP) {
    return helperDC(sA, sP, 0, 0);
  }

  private static int helperDC(int[] sA, int[] sP, int iniAlumno, int iniProfesor) {
    if (iniAlumno == sA.length || iniProfesor == sP.length)
      return 0;

    if (sA[iniAlumno] == sP[iniProfesor]) {
      return 1 + helperDC(sA, sP, iniAlumno + 1, iniProfesor + 1);
    } else {
      int avanzandoEnAlumno = helperDC(sA, sP, iniAlumno + 1, iniProfesor);
      int avanzandoEnProfesor = helperDC(sA, sP, iniAlumno, iniProfesor + 1);
      return Math.max(avanzandoEnAlumno, avanzandoEnProfesor);
    }
  }

  /*
   * d(sA, sP, sA.length, _) = 0
   * d(sA, sP, _, sP.length) = 0
   * d(sA, sP, i, j) =
   *  ((sA[i] = sP[j]) -> 1 + 1 + d(sA, sP, i+1, j+1))
   *  or
   *  (~(sA[i] = sP[j]) -> max(d(sA, sP, i+1, j), d(sA, sP, i, j+1)))
   */

  public static int gradingDP(int[] sA, int[] sP) {
    int[][] dp = new int[sA.length + 1][sP.length + 1];
    for(int i = 0; i <= sA.length; i++) {
      dp[sA.length][i] = 0;
      dp[i][sP.length] = 0;
    }
    for(int i = sA.length - 1; i >= 0; i--) {
      for (int j = sP.length - 1; j >= 0; j--) {
        if (sA[i] == sP[j]) {
          dp[i][j] = 1 + dp[i+1][j+1];
        } else {
          dp[i][j] = Math.max(dp[i+1][j], dp[i][j+1]);
        }
      }
    }
    return dp[0][0];
  }
}
