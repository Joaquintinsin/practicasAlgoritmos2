import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class MinimoVueltoMonedas {
  private Tuple<Integer, Integer>[] valoresMonedas;

  private ArrayList<Integer> monedasDadasComoCambio;

  public MinimoVueltoMonedas(Tuple<Integer, Integer>[] valoresMonedas) {
    this.valoresMonedas = Arrays.copyOf(valoresMonedas, valoresMonedas.length);
    this.monedasDadasComoCambio = new ArrayList<>();
    ordenarMonedasPorDenominacion();
  }

  public int cantidadDeMonedasGreedy(int C) {
    int cantMonedasCambio = 0;
    for (int i = 0; i < valoresMonedas.length; i++) {
      Tuple<Integer, Integer> t = valoresMonedas[i];
      if (puedeDarCambio(C, t.getFirst(), t.getSecond())) {
        int[] actualizador = darCambioYActualizarVariables(C, t.getFirst(), t.getSecond(), cantMonedasCambio, i);
        C                 = actualizador[0];
        cantMonedasCambio = actualizador[1];
        t.setSecond(actualizador[2]);
        i                 = actualizador[3];
      } else if (termineDeDarCambio(C)) {
        System.out.println("Ya te di todo el cambio");
        break;
      } else if (mePaseDelCambio(C)) {
        System.out.println("Te regale unos centavos: " + C);
        break;
      } else if (monedaActualMayorQueElCambio(t.getFirst(), C)) {
        System.out.println("moneda actual: " + t.getFirst() + " mayor al C: " + C);
      } else if (sinMonedasActuales(t.getSecond())) {
        System.out.println("No hay mas monedas de denominacion " + t.getFirst() + " para dar");
        if (nonPossoDartiPiuCambi(C, i)) {
          System.out.println("Mi dispiace, non posso darti piu cambi, ho finito le monete");
        }
      }
    }
    return cantMonedasCambio;
  }

  private boolean puedeDarCambio(int C, int denominacionMoneda, int cantidadMoneda) {
    return C > 0 && C - denominacionMoneda >= 0 && cantidadMoneda > 0;
  }

  private boolean termineDeDarCambio(int C) {
    return C == 0;
  }

  private boolean mePaseDelCambio(int C) {
    return C < 0;
  }

  private boolean monedaActualMayorQueElCambio(int monedaActual, int C) {
    return monedaActual > C;
  }

  private boolean sinMonedasActuales(int cantidadMonedaActual) {
    return cantidadMonedaActual == 0;
  }

  private boolean nonPossoDartiPiuCambi(int C, int i) {
    return C > 0 && i == valoresMonedas.length - 1;
  }

  private int[] darCambioYActualizarVariables(int C, int denominacionMoneda, int cantidadMoneda, int cantidadMonedasCambio, int i) {
    this.monedasDadasComoCambio.add(denominacionMoneda);
    System.out.println("te doy cambio con " + denominacionMoneda);
    System.out.println("todavia te debo: " + (C - denominacionMoneda));
    return new int[] {
      C - denominacionMoneda,
      cantidadMonedasCambio + 1,
      cantidadMoneda - 1,
      i - 1
    };
  }

  public ArrayList<Integer> listaDeMonedasDadas() {
    return this.monedasDadasComoCambio;
  }

  private void ordenarMonedasPorDenominacion() {
    Arrays.sort(this.valoresMonedas, Comparator.comparing((Tuple<Integer, Integer> t) -> t.getFirst()).reversed());
    System.out.println("Arreglo postOrden:");
    System.out.println(Arrays.toString(this.valoresMonedas));
  }

  public static void main(String[] args) {
    @SuppressWarnings("unchecked")
    Tuple<Integer, Integer>[] setMonedas = (Tuple<Integer, Integer>[]) new Tuple[] {
      new Tuple<>(1, 2),
      new Tuple<>(5, 2),
      new Tuple<>(7, 2),
      new Tuple<>(10, 2)
    };
    int CVuelto = 14;
    // con esa configuracion de monedas no se obtiene el cambio optimo
    // porque va a dar 5 monedas (10, 1, 1, 1, 1) y podria dar 2 como minimo (dos de 7 por ejemplo)

    MinimoVueltoMonedas mvm = new MinimoVueltoMonedas(setMonedas);

    int minVuelto = mvm.cantidadDeMonedasGreedy(CVuelto);
    System.out.println("cant minima de monedas q te doy: " + minVuelto);
    System.out.println("Monedas que di como cambio: " + mvm.listaDeMonedasDadas().toString());
  }
}
