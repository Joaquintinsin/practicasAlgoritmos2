import java.util.HashSet;
import java.util.Set;

public class SubConjuntoSumaN {
  public static Set<Set<Integer>> powerSet(Set<Integer> s) {
    Set<Set<Integer>> ps = new HashSet<>();
    ps.add(new HashSet<>());

    for (Integer elementOriginalSet : s) {
      Set<Set<Integer>> subSets = new HashSet<>();
      for (Set<Integer> subSet : ps) {
        Set<Integer> newSubSet = new HashSet<>(subSet);
        newSubSet.add(elementOriginalSet);
        subSets.add(newSubSet);
      }
      ps.addAll(subSets);
    }

    return ps;
  }

  public static int setSum(Set<Integer> s) {
    int res = 0;
    for (Integer z : s)
      res += z;
    return res;
  }

  public static void main(String[] args) {
    Set<Integer> originalSet = new HashSet<>();
    int n = 30;

    originalSet.add(0);
    originalSet.add(1);
    originalSet.add(2);
    originalSet.add(15);
    originalSet.add(40);

    Set<Set<Integer>> ps = powerSet(originalSet);
    for (Set<Integer> sz : ps)
      if (setSum(sz) == n) {
        System.out.println("True");
        return;
      }
    System.out.println("False");
  }
}
