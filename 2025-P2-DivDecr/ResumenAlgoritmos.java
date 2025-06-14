/**
 * Puede que haya cosas que no anden por ser pseudocodigo.
 *
 *  No adjudico mi autoria al resumen de los algoritmos,
 *  son sacados del teorico hechos por los chicos en 2024,
 *  por lo tanto conceptualmente y pseudoalgoritmicamente andan bien.
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class ResumenAlgoritmos {
  // DFS engine
  public S performSearchDFS() {
    Stack<S> stack = new Stack<S>();
    Stack<S> marked = new Stack<S>();
    stack.push(sp.initialState());
    boolean found = false;
    S goal = null;
    while (!stack.isEmpty() && !found) {
      S current = stack.pop();
      if (!marked.contains(current)) {
        marked.push(current);
        if (current.isSuccess()) {
          found = true;
          goal = current;
        } else {
          List<S> succs = sp.getSuccessors(current);
          stack.addAll(succs);
        }
      }
    }
    if (!(goal == null)) {
      S s = goal;
      while (!(s == null)) {
        path.add(0, s);
        s = (S) s.getParent();
      }
    }
    return goal;

  }

  // BFS engine
  public S performSearchBFS() {
    Queue<S> queue = new LinkedList<S>();
    queue.add(sp.initialState());
    boolean found = false;
    S goal = null;
    while (!queue.isEmpty() && !found) {
      S current = queue.poll();
      if (current.isSuccess()) {
        found = true;
        goal = current;
      } else {
        List<S> succs = sp.getSuccessors(current);
        queue.addAll(succs);
      }
    }
    if (!(goal == null)) {
      S s = goal;
      while (!(s == null)) {
        path.add(0, s);
        s = (S) s.getParent();
      }
    }
    return goal;

  }

  // Iterative Deepening engine
  public S performSearchITERATIVE() {
    boolean found = false;
    S estado = null;
    int n = 1;

    while (!found) {
      DepthFirstLevelEngine<S, P> engineDfs = new DepthFirstLevelEngine<S, P>(sp, n);
      estado = (S) engineDfs.performSearchDFSLEVEL();
      if (estado != null) {
        found = true;
        path = engineDfs.getPath();
      }
      n++;
    }
    return estado;
  }

  // Hill Climbing engine
  public S performSearchHillClimbing() {
    S mejor = sp.initialState();
    boolean encontrado = false;
    S goal = null;
    while (!encontrado) {
      if (mejor.isSuccess()) {
        encontrado = true;
        goal = mejor;
      } else {
        List<S> succs = sp.getSuccessors(mejor);
        mejor = calcularElMejorSucesor(succs, mejor);
      }
    }
    if (!(goal == null)) {
      S s = goal;
      while (!(s == null)) {
        path.add(0, s);
        s = (S) s.getParent();
      }
    }
    return goal;

  }

  private static final Comparator customComparator = null;

  // Best First Search engine
  public S performSearchBestFirstSearch() {
    // el customComparator es de la forma que se
    // van a ordenar en la cola de prioridad
    PriorityQueue<S> priorityQueue = new PriorityQueue<S>(customComparator);
    S inicial = sp.initialState();
    priorityQueue.add(inicial);
    boolean found = false;
    S goal = null;
    ArrayList<S> marked = new ArrayList<S>();
    while (!priorityQueue.isEmpty() && !found) {
      S frente = priorityQueue.poll();
      if (!marked.contains(frente)) {
        marked.add(frente);
        if (frente.isSuccess()) {
          found = true;
          goal = frente;
        } else {
          List<S> succs = sp.getSuccessors(frente);
          for (S successor : succs) {
            priorityQueue.add(successor);
          }
        }
      }
    }

    S s = goal;
    while (!(goal == null)) {
      path.add(0, goal);
      goal = (S) goal.getParent();
    }
    return s;

  }

  // MinMax comun
  public int minMax2(S state, int maxDepth) {
    if (maxDepth == 0 || state.end()) { // si state es hoja o llegue hasta el nivel que queria
      return state.value();
    }

    int x = Integer.MAX_VALUE;
    int y = Integer.MAX_VALUE;

    List<S> succs = sp.getSuccessors(state);
    for (S succ : succs) {
      if (succ.isMax()) {
        x = max(x, minMax2(succ, maxDepth - 1));
      } else {
        y = min(y, minMax2(succ, maxDepth - 1));
      }
    }

    if (state.isMax()) {
      return x;
    } else {
      return y;
    }
  }

  // MinMax Alfa Beta
  public int minMaxAlphaBeta(int alpha, int beta, S state, int maxDepth) {

    if (state.end() || maxDepth == 0) {
      return state.valoration();
    }

    List<S> succs = state.getSucessors();
    int i = 0;
    while (i < succs.lenght && alpha < beta) {
      S succ = succs.get(i);
      if (succ.isMax()) {
        alpha = Math.max(alpha, minMaxAlphaBeta(alpha, beta, succ, maxDepth - 1));
      } else {
        beta = Math.min(beta, minMaxAlphaBeta(alpha, beta, succ, maxDepth - 1));
      }

    }

    if (state.isMax()) {
      return alpha;
    } else {
      return beta;
    }
  }

  private static final int INF = Integer.MAX_VALUE;

  // Prims
  public void primMST(int[][] graph) {
    int V = graph.length;

    int[] parent = new int[V]; // Arreglo para almacenar el MST
    int[] key = new int[V]; // Valores mínimos para elegir la arista
    boolean[] mstSet = new boolean[V]; // Representa el conjunto de vértices incluidos en el MST

    Arrays.fill(key, INF);
    key[0] = 0; // Comenzamos desde el vértice 0
    parent[0] = -1; // El primer nodo no tiene padre

    for (int count = 0; count < V - 1; count++) {
      int u = minKey(key, mstSet, V);
      mstSet[u] = true;

      for (int v = 0; v < V; v++) {
        if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
          parent[v] = u;
          key[v] = graph[u][v];
        }
      }
    }

    printMST(parent, graph, V);
  }

  // Encuentra el vértice con el valor mínimo en key[] que
  // aún no está incluido en el MST
  private int minKey(int[] key, boolean[] mstSet, int V) {
    int min = INF, minIndex = -1;

    for (int v = 0; v < V; v++) {
      if (!mstSet[v] && key[v] < min) {
        min = key[v];
        minIndex = v;
      }
    }

    return minIndex;
  }

  // Imprime el MST almacenado en parent[]
  private void printMST(int[] parent, int[][] graph, int V) {
    System.out.println("Arista \tPeso");
    for (int i = 1; i < V; i++) {
      System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
    }
  }

  // Kruskal
  public Set<Edge> kruskal(Graph g) {
    Set<Edge> es = new Set<Edge>(); // crea un conjunto de arcos vacíos

    // creamos una cola de prioridad de arcos ordenados por sus costos
    PriorityQueue h = new PriorityQueue(g.getEdges());

    // creamos un disjoint set con todos los vértices
    DisjointSet s = new DisjointSet(g.getVertices());

    while (!h.isEmpty()) {
      Edge e = h.deMin(); // se saca el arco con menor costo
      Vertex u = e.getSource(); // se obtiene su origen
      Vertex v = e.getTarget(); // se obtiene su destino
      if (s.find(u) != s.find(v)) { // si están en clases de equivalencia distintas
        es.add(e); // se agrega el arco
        s.union(u, v); // se hace la unión de las clases
      }
    }
    return es;
  }
}