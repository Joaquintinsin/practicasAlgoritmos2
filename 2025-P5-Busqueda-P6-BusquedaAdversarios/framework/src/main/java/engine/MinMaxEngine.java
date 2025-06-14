package engine;

import java.util.List;

import adversarysearch.EngineAdversary;
import adversarysearch.StateAdversary;
import adversarysearch.StateProblemAdversary;

public class MinMaxEngine<P extends StateProblemAdversary<S>, S extends StateAdversary>
    implements EngineAdversary<P, S> {
  private int maxDepth;
  private P stateProblem;

  public MinMaxEngine(int maxDepth, P p) {
    this.maxDepth = maxDepth;
    this.stateProblem = p;
  }

  public int getMaxDepth() {
    return maxDepth;
  }

  public void setMaxDepth(int maxDepth) {
    this.maxDepth = maxDepth;
  }

  public P getProblem() {
    return this.stateProblem;
  }

  public void setProblem(P p) {
    this.stateProblem = p;
  }

  public int computeValue(S state) {
    return minMaxAlfaBeta(state, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
  }

  private int minMaxAlfaBeta(S state, int currentDepth, int alfa, int beta) {
    if (state.end() || currentDepth >= maxDepth) {
      return state.value();
    } else {
      List<S> succ = stateProblem.getSuccessors(state);
      for (S s : succ) {
        if (state.isMax()) {
          alfa = Math.max(alfa, minMaxAlfaBeta(s, currentDepth + 1, alfa, beta));
        } else {
          beta = Math.min(beta, minMaxAlfaBeta(s, currentDepth + 1, alfa, beta));
        }
        if (beta <= alfa) {
          break;
        }
      }

      return (state.isMax()) ? alfa : beta;
    }
  }

  private int minMax(S state, int currentDepth) {
    if (state.end() || currentDepth >= maxDepth) {
      return state.value();
    } else {
      int maxValue = stateProblem.minValue();
      int minValue = stateProblem.maxValue();
      List<S> succ = stateProblem.getSuccessors(state);
      for (S s : succ) {
        if (state.isMax()) {
          maxValue = Math.max(maxValue, minMax(s, currentDepth + 1));
        } else {
          minValue = Math.min(minValue, minMax(s, currentDepth + 1));
        }
      }

      return (state.isMax()) ? maxValue : minValue;
    }
  }

  public S computeSuccessor(S state) {
    S bestSuccessor = null;
    int bestValue = (state.isMax()) ? Integer.MIN_VALUE : Integer.MAX_VALUE;

    // System.out.println("Generando sucesores para el estado:");
    // System.out.println(state);

    // List<S> successors = stateProblem.getSuccessors(state);
    // System.out.println("Cantidad de sucesores generados: " + successors.size());
    // for (S s : successors) {
    //   System.out.println("Sucesor: \n" + s);
    // }

    for (S s : stateProblem.getSuccessors(state)) {
      int computedValue = computeValue(s);
      if (state.isMax()) {
        if (computedValue > bestValue || bestSuccessor == null) {
          bestValue = computedValue;
          bestSuccessor = s;
        }
      } else {
        if (computedValue < bestValue || bestSuccessor == null) {
          bestValue = computedValue;
          bestSuccessor = s;
        }
      }
    }
    return bestSuccessor;
  }

  public void report() {
    System.out.println("Current State Problem: ");
    System.out.println(stateProblem);
  }

}
