import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Grafo<T> {
  private Set<T> listaDeNodos;
  private Set<Tuple<T, T, Integer>> grafoMap;

  public Set<T> prim() {
    Set<T> VT = new HashSet<>();
    VT.add(initialNode);
    Set<T> ET = new HashSet<>();
    for (int i = 0; i < VT.size(); i++) {
      int minimumWeightEdge = 0;
      for (Tuple<T, T, Integer> nodopeso : grafoMap) {
        T V = nodoPeso.first();
        T u = nodoPeso.second();
        Integer p = nodoPeso.third();
        Set<T> resta = restaConjuntos(listaDeNodos, VT);
        if (VT.contains(v) && resta.contains(u)) {
          minimumWeightEdge = Math.min(p, minimumWeightEdge);
        }
        VT.add(u);
        ET.add(minimumWeightEdge);
      }
    }
    return ET;
  }
}


/*
 * Here is pseudocode of this algorithm.
ALGORITHM Prim(G)
  //Prim’s algorithm for constructing a minimum spanning tree
  //Input: A weighted connected graph G = 〈V, E〉
  //Output: ET , the set of edges composing a minimum spanning tree of G
  VT <- {v0} //the set of tree vertices can be initialized with any vertex
  ET ← ∅
  for i ← 1 to |V | − 1 do
    find a minimum-weight edge e∗ = (v∗, u∗) among all the edges (v, u)
    such that v is in VT and u is in V − VT
    VT ← VT ∪ {u∗}
    ET ← ET ∪ {e∗}
  return ET
 */