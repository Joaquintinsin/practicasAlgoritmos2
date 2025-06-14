package util.math;

import java.util.HashSet;
import java.util.Set;

public class Main {
  public static void main(String[] args) {
    Set<Pair<Integer>> s = new HashSet<>();
    int nPoints = 30;
    for (int i = 0; i < nPoints; i++) {
      int first_pseudo_number = (int) (Math.random() * 100);
      int second_pseudo_number = (int) (Math.random() * 100);
      Pair<Integer> p = new Pair<Integer>(first_pseudo_number, second_pseudo_number);
      s.add(p);
    }
    System.out.println("Set creado: " + s.toString());

    EuclideanDistance<Integer> ed = new EuclideanDistance<>(s);
    Pair<Pair<Integer>> minimal_pair_of_points_closer = ed.getMinPoints();

    System.out.println(
        "Los dos puntos con menor distancia del set son: \n"
            + minimal_pair_of_points_closer.toString());
  }
}
