import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ColoreoGrafos {
  public static HashMap<Integer, Pair<Integer, List<Integer>>> grafo = new HashMap<>();
  public static int sizeGraph = 0;

  public static void agregarNodo(int nodo) {
    grafo.putIfAbsent(nodo, Pair.of(0, new ArrayList<>()));
    sizeGraph++;
  }

  public static void agregarArista(int origen, int destino) {
    // Si el grafo es no dirigido
    grafo.get(origen).getRight().add(destino);
    grafo.get(destino).getRight().add(origen);
  }

  public static List<Integer> obtenerVecinos(int nodo) {
    if (!grafo.containsKey(nodo)) {
      System.out.println("El nodo " + nodo + " no existe en el grafo.");
      return new ArrayList<>();
    }
    return grafo.get(nodo).getRight();
  }

  public static void ponerNodosEnElGrafo(int cantNodos) {
    for (int i = 1; i <= cantNodos; i++)
      agregarNodo(i);
  }

  public static void unirTodosConTodos() {
    List<Integer> nodos = new ArrayList<>(grafo.keySet());

    // Recorremos todos los pares de nodos
    for (int i = 0; i < nodos.size(); i++) {
      for (int j = i + 1; j < nodos.size(); j++) {
        // Unimos los nodos i y j
        agregarArista(nodos.get(i), nodos.get(j));
      }
    }
  }

  public static void pintarNodo(int nodo, int color) {
    if (!grafo.containsKey(nodo)) {
      System.out.println("El nodo " + nodo + " no existe en el grafo.");
      return;
    }
    grafo.put(nodo, Pair.of(color, grafo.get(nodo).getRight()));
  }

  public static void imprimirGrafo() {
    for (int nodo : grafo.keySet()) {
      System.out.println(
          "Nodo " + nodo + ": Color " + grafo.get(nodo).getLeft() + ", Vecinos: " + grafo.get(nodo).getRight());
    }
  }

  public static void colorearGrafo() {
    for (int nodo : grafo.keySet()) {
      if (grafo.get(nodo).getLeft() == 0) { // Si el nodo no tiene color
        int color = 1; // Comenzamos con el color 1
        List<Integer> vecinos = obtenerVecinos(nodo);
        for (int vecino : vecinos) {
          if (grafo.get(vecino).getLeft() == color) {
            color++; // Cambiamos de color si el vecino ya tiene el mismo color
          }
        }
        pintarNodo(nodo, color);
      }
    }
  }

  public static void main(String[] args) {
    int cantNodos = 9;
    ponerNodosEnElGrafo(cantNodos);
    // unirTodosConTodos();
    agregarArista(1, 2);
    agregarArista(1, 3);
    colorearGrafo(); // Coloreamos el grafo
    imprimirGrafo(); // Imprimimos el grafo coloreado
  }
}