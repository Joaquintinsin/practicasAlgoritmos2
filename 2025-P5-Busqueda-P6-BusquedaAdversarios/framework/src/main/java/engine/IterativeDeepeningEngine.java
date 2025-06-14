package engine;

import conventionalsearch.Engine;
import conventionalsearch.State;
import conventionalsearch.StateProblem;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

/**
 * Title: IterativeDeepeningEngine
 * Description: Class IterativeDeepeningEngine implements a Breadth-first search
 * strategy which can be used with any instance of StateProblem.
 *
 * @author Nazareno Aguirre
 */
public class IterativeDeepeningEngine<S extends State, P extends StateProblem<S>> implements Engine<S, P> {

  /**
   * Internal representation of the StateProblem.
   */
  private P sp;

  /**
   * path stores the path to the goal.
   */
  private List<S> path;

  /**
   * Constructor for class IterativeDeepeningEngine.
   * @pre. true.
   * @post. Lists path is initialized as empty.
   */
  public IterativeDeepeningEngine() {
    path = new LinkedList<S>();
  }

  /**
   * Constructor for class IterativeDeepeningEngine.
   *
   * @param sp is the search problem associated with the engine
   *           being created.
   *           @pre. p!=null.
   *           @post. A reference to p is stored in field problem.
   */
  public IterativeDeepeningEngine(P sp) {
    this.sp = sp;
    path = new LinkedList<S>();
  }

  /**
   * Starts the search for successful states for problem, following a
   * iterative deepening strategy.
   *
   * @return true iff a successful state is found.
   *         @pre. problem!=null.
   *         @post. the search is performed, the path in list path, and true is
   *         returned iff a
   *         successful state is found.
   */
  @SuppressWarnings("unchecked")
  public S performSearch() {
    boolean found = false;
    S goal = null;
    for (int profundidadActual = 0; !found && profundidadActual <= Integer.MAX_VALUE; profundidadActual++) {
      // goal = dfsLimitado(sp.initialState(), profundidadActual);
      goal = dfsLimitadoConVisitados(sp.initialState(), profundidadActual, new HashSet<>());

      // inicio de construir el camino hasta el exitoso
      if (goal != null) {
        S s = goal;
        found = true;
        while (s != null) {
          path.add(0, s);
          s = (S) s.getParent();
        }
      }
      // fin de construir el camino hasta el exitoso
    }
    return goal;
  }

  // sin el conjunto visitados
  private S dfsLimitado(S current, int limite) {
    // lo encuentra, lo devuelve
    if (current.isSuccess()) {
      return current;
    }
    // termina el limite, devuelve null
    if (limite <= 0) {
      return null;
    }
    // tirar dfs con limite-1
    // si encuentra, lo devuelve, sino tira dfs con el proximo sucesor
    for (S succ : sp.getSuccessors(current)) {
      S result = dfsLimitado(succ, limite - 1);
      if (result != null) {
        return result;
      }
    }
    return null;
  }

  private S dfsLimitadoConVisitados(S current, int limite, Set<S> visitados) {
    if (visitados.contains(current)) {
      return null;
    }
    visitados.add(current);

    // lo encuentra, lo devuelve
    if (current.isSuccess()) {
      return current;
    }
    // termina el limite, devuelve null
    if (limite <= 0) {
      return null;
    }
    // tirar dfs con limite-1
    // si encuentra, lo devuelve, sino tira dfs con el proximo sucesor
    for (S succ : sp.getSuccessors(current)) {
      S result = dfsLimitadoConVisitados(succ, limite - 1, visitados);
      if (result != null) {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the path to a previously calculated successful state for problem.
   *
   * @return the list of nodes corresponding to the path from the root to
   *         the successful node.
   *         @pre. performSearch() has been executed and finished successfully.
   *         @post. the path to the found goal node is returned.
   */
  public List<S> getPath() {
    return path;
  } // end of getPath()

  /**
   * Reports information regarding a previously executed search.
   * @pre. performSearch() has been executed and finished.
   * @post. A report regarding the search is printed to standard output.
   * This report consists of .
   */
  public void report() {
    System.out.println("Length of path to state when search finished: " + path.size());
  } // end of report()
}
