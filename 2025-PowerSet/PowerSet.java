import java.util.HashSet;
import java.util.Set;

public class PowerSet {
  public static void main(String[] args) {
    Set<Integer> originalSet = new HashSet<>();
    int N_ELEM_SET = 4;

    for (int i = 1; i <= N_ELEM_SET; i++)
      originalSet.add(i);

    Set<Set<Integer>> powerSet = new HashSet<>();
    powerSet.add(new HashSet<>());

    for (Integer elementOriginalSet : originalSet) {
      Set<Set<Integer>> subSets = new HashSet<>();
      for (Set<Integer> subSet : powerSet) {
        Set<Integer> newSubSet = new HashSet<>(subSet);
        newSubSet.add(elementOriginalSet);
        subSets.add(newSubSet);
      }
      powerSet.addAll(subSets);
    }

    System.out.println("originalSet: " + originalSet.toString());
    System.out.println("powerSet: " + powerSet.toString());
  }
}
