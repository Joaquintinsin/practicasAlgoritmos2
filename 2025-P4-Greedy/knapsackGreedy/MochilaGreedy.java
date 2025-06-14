import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Comparator;

public class MochilaGreedy {
  public static int[] itemsValue = new int[] {
    50, 25, 90, 45, 8,  1, 3, 10, 2
  };
  public static int[] itemsWeight = new int[] {
    35, 25, 50, 25, 15, 2, 7, 10, 10
  };
  public static int cantidadItemsCasa = itemsValue.length;
  public static int capacidadMochila = 35;

  // Clase auxiliar para representar un ítem con valor y peso
  static class Item {
    int value;
    int weight;

    Item(int value, int weight) {
      this.value = value;
      this.weight = weight;
    }

    @Override
    public String toString() {
      return "(" + value + ", " + weight + ")";
    }
  }

  public static void ordenarLosCosos() {
    // Paso 1: Combinar en un solo arreglo de objetos Item
    Item[] items = new Item[itemsValue.length];
    for (int i = 0; i < items.length; i++) {
      items[i] = new Item(itemsValue[i], itemsWeight[i]);
    }

    // Paso 2: Ordenar en base al valor descendente
    Arrays.sort(items, Comparator.comparingInt((Item i) -> i.value).reversed());

    // Paso 3: Separar nuevamente los valores y pesos si es necesario
    for (int i = 0; i < items.length; i++) {
      itemsValue[i] = items[i].value;
      itemsWeight[i] = items[i].weight;
    }

    // Verificación
    System.out.println("Valores ordenados:");
    System.out.println(Arrays.toString(itemsValue));
    System.out.println("Pesos correspondientes:");
    System.out.println(Arrays.toString(itemsWeight));
  }

  public static void main(String[] args) {
    ordenarLosCosos();
    int totalValorRobado = 0;
    for (int i = 0; i < cantidadItemsCasa; i++) {
      if (!(itemsWeight[i] > capacidadMochila)) {
        capacidadMochila = capacidadMochila - itemsWeight[i];
        totalValorRobado += itemsValue[i];
      }
    }
    System.out.println("Cantidad robada con greedy: " + totalValorRobado);
  }
}
