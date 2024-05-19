package conjuntos;

import java.util.Set;
import java.util.HashSet;

public class Conjunto<E> {
  //~ public static void main(String[] args) {
    //~ Conjunto<Integer> conjunto = new Conjunto<>();
    //~ HashSet<Integer> set = new HashSet<>();
    //~ set.add(1);
    //~ set.add(2);
    //~ set.add(3);
    //~ Set<Set<Integer>> subsets = conjunto.subsets(set);
    //~ for (Set<Integer> subset : subsets) {
      //~ System.out.println(subset);
    //~ }
  //~ }

  public Set<Set<E>> subsets(Set<E> set) {
    Set<Set<E>> result = new HashSet<>();
    if (set.isEmpty()) {
      result.add(new HashSet<>());
      return result;
    }
    E element = set.iterator().next();
    set.remove(element);
    Set<Set<E>> subsets = subsets(set);
    for (Set<E> subset : subsets) {
      Set<E> newSubset = new HashSet<>(subset);
      newSubset.add(element);
      result.add(newSubset);
    }
    result.addAll(subsets);
    return result;
  }
}
