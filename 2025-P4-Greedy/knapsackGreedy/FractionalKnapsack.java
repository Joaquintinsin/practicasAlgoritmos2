import java.util.Arrays;
import java.util.Comparator;

public class FractionalKnapsack {
  public static class Tuple {
    private float peso = 0;
    private float valor = 0;

    public Tuple(float peso, float valor) {
      this.peso = peso;
      this.valor = valor;
    }

    public float first() {
      return this.peso;
    }

    public float second() {
      return this.valor;
    }

    public void setFirst(float np) {
      this.peso = np;
    }

    public void setSecond(float nv) {
      this.valor = nv;
    }
  }

  public static float greedy(float w, Tuple[] objetos) {
    float vTotal = 0;
    // objetos.sortMayorMenorBySecondComponent();
    Arrays.sort(objetos, new Comparator<Tuple>() {
      public int compare(Tuple a, Tuple b) {
        float ratioA = a.second() / a.first();
        float ratioB = b.second() / b.first();
        return Float.compare(ratioB, ratioA);
      }
    });

    for (int i = 0; i < objetos.length; i++) {
      if (w - objetos[i].first() >= 0) {
        w = w - objetos[i].first();
        vTotal += objetos[i].second();
      } else {
        float wOld = objetos[i].first();
        float vOld = objetos[i].second();
        objetos[i].setFirst(wOld / vOld);
        objetos[i].setSecond(wOld / objetos[i].second());
        if (w - objetos[i].first() >= 0) {
          w = w - objetos[i].first();
          vTotal += objetos[i].second();
        }
      }
    }
    return vTotal;
  }

  public static void main(String[] args) {
    int W = 50;
    Tuple[] objetos = new Tuple[] {
        new Tuple(50, 100),
        new Tuple(40, 30)
    };

    float v = greedy(W, objetos);
    System.out.println("valor maximo greedy: " + v);
  }
}
