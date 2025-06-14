public class VinosGreedy {
  private int[] vinos = new int[] { 1, 5, 5, 4 };
  private int gananciaTotal = 0;

  public VinosGreedy(int[] vinos) {
    this.vinos = vinos;
    this.gananciaTotal = 0;
  }

  public int solucionGreedy() {
    int currentYear = 1;
    int indicePrimerVino = 0;
    int indiceUltimoVino = vinos.length - 1;
    for (int i = 0; i < vinos.length; i++) {
      if (vinos[indicePrimerVino] <= vinos[indiceUltimoVino]) {
        gananciaTotal += vinos[indicePrimerVino] * currentYear;
        indicePrimerVino++;
      } else {
        gananciaTotal += vinos[indiceUltimoVino] * currentYear;
        indiceUltimoVino--;
      }
      currentYear++;
    }
    return gananciaTotal;
  }

  public static void main(String[] args) {
    VinosGreedy vg1 = new VinosGreedy(new int[] { 1, 5, 5, 4 });
    System.out.println("Vinos1: Total ganancia = " + vg1.solucionGreedy());

    VinosGreedy vg2 = new VinosGreedy(new int[] { 3, 5, 7, 2, 6 });
    System.out.println("Vinos1: Total ganancia = " + vg2.solucionGreedy());
  }
}
