package informedsearch;

import java.util.Comparator;

public class StateComparators {
  public static <S extends StateInformed> Comparator<S> byHeuristic() {
    return Comparator.comparingInt(S::value);
  }
}
