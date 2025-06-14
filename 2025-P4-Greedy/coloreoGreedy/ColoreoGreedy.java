import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class ColoreoGreedy {
  static class Par {
    Integer color;
    ArrayList<Integer> vecinos;

    public Par(Integer color, ArrayList<Integer> vecinos) {
      this.color = color;
      this.vecinos = vecinos;
    }

    public Integer getColor() {
      return color;
    }

    public void setColor(Integer color) {
      this.color = color;
    }

    public ArrayList<Integer> getVecinos() {
      return vecinos;
    }

    public void agregarVecino(Integer v) {
      if (vecinos.contains(v)) return;
      vecinos.add(v);
    }

    @Override
    public String toString() {
      return "(" + color + ", " + vecinos.toString() + ")";
    }
  }

  // { nodo, (color, vecinos) }
  public static Map<Integer, Par> grafo;

  public static void inicializarGrafo(Integer cantidadNodos) {
    grafo = new HashMap<>();
    for (Integer i = 1; i <= cantidadNodos; i++) {
      grafo.put(i, new Par(0, new ArrayList<>()));
    }
  }

  public static void agregarArista(Integer origen, Integer destino) {
    if (grafo.containsKey(origen) && grafo.containsKey(destino)) {
      grafo.get(origen).agregarVecino(destino);
      grafo.get(destino).agregarVecino(origen);
    } else {
      System.out.printf("AgregarArista: no se encontro el nodo origen %d o el nodo destino %d \n", origen, destino);
    }
  }

  public static void pintarNodo(Integer nodo, Integer color) {
    if (grafo.containsKey(nodo)) grafo.get(nodo).setColor(color);
    else System.out.printf("PintarNodo: no se encontro el nodo %d a pintar en el grafo", nodo);
  }

  public static int coloreoGrafoGreedy() {
    Integer colorActual = 1;
    for (Map.Entry<Integer, Par> n : grafo.entrySet()) {
      Integer nodoActual = n.getKey();
      Par parNodoActual = n.getValue();
      if (parNodoActual.getColor() == 0) {
        pintarNodo(nodoActual, colorActual);
        ArrayList<Integer> noPuedoPintarDelColorActual = new ArrayList<>(parNodoActual.getVecinos());
        for (Map.Entry<Integer, Par> buscadorVecinos : grafo.entrySet()) {
          Integer nodoVecino = buscadorVecinos.getKey();
          Par parVecinoActual = buscadorVecinos.getValue();
          if (!(noPuedoPintarDelColorActual.contains(nodoVecino))) {
            pintarNodo(nodoVecino, colorActual);
          }
        }
        colorActual++;
      }
    }
    return colorActual;
  }

  public static void grafoTeorico8Nodos() {
    inicializarGrafo(8);

    // aristas con el nodo 1:
    agregarArista(1, 2);
    agregarArista(1, 3);
    agregarArista(1, 4);
    agregarArista(1, 5);

    // aristas con el nodo 2:
    agregarArista(2, 3);
    agregarArista(2, 4);
    agregarArista(2, 8);

    // aristas con el nodo 3:
    // 3 ya esta: con 1 y con 2

    // aristas con el nodo 4:
    // 4 ya esta: con 1 y con 2

    // aristas con el nodo 5:
    agregarArista(5, 7);
    agregarArista(5, 6);

    // aristas con el nodo 5:
    agregarArista(6, 7);
    agregarArista(6, 8);

    // aristas con el nodo 7:
    agregarArista(7, 8);

    // aristas con el nodo 8:
    // 8 ya esta: con 2, con 6 y con 7
  }

  public static void grafoCiclicoPar() {
    inicializarGrafo(6);
    /*
     * 1 - 2
     * |   |
     * 6 - 3
     * |   |
     * 5 - 4
     */
    agregarArista(1, 2);
    agregarArista(2, 3);
    agregarArista(3, 4);
    agregarArista(4, 5);
    agregarArista(5, 6);
    agregarArista(6, 1);
  }

  public static void grafoCompleto() {
    inicializarGrafo(4);
    /*
     * 1 - 2
     * |\ /|
     * |/ \|
     * 3 - 4
     */
    agregarArista(1, 2);
    agregarArista(1, 3);
    agregarArista(1, 4);
    agregarArista(2, 3);
    agregarArista(2, 4);
    agregarArista(3, 4);
  }

  public static void arbolBinario7Nodos() {
    inicializarGrafo(7);
    /*
     *      1
     *     / \
     *    2   3
     *   / \  / \
     *  4  5 6   7
     */
    agregarArista(1, 2);
    agregarArista(1, 3);
    agregarArista(2, 4);
    agregarArista(2, 5);
    agregarArista(3, 6);
    agregarArista(3, 7);
  }

  public static void grafoEstrella() {
    inicializarGrafo(5);
    /*
     *       2
     *       |
     *  3 -- 1 -- 4
     *       |
     *       5
     */
    agregarArista(1, 2);
    agregarArista(1, 3);
    agregarArista(1, 4);
    agregarArista(1, 5);
  }

  public static void main(String[] args) {
    grafoTeorico8Nodos();
    System.out.println("grafoTeorico8Nodos: \tTermine pintando con " + coloreoGrafoGreedy() + " colores");

    grafoCiclicoPar();
    System.out.println("grafoCiclicoPar: \tTermine pintando con " + coloreoGrafoGreedy() + " colores");

    grafoCompleto();
    System.out.println("grafoCompleto: \t\tTermine pintando con " + coloreoGrafoGreedy() + " colores");

    arbolBinario7Nodos();
    System.out.println("arbolBinario7Nodos: \tTermine pintando con " + coloreoGrafoGreedy() + " colores");

    grafoEstrella();
    System.out.println("grafoEstrella: \t\tTermine pintando con " + coloreoGrafoGreedy() + " colores");
  }
}
