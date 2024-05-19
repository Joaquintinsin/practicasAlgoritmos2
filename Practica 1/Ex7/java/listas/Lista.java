package listas;

import java.util.ArrayList;
import java.util.List;

public class Lista<T> {
  //~ public static void main(String[] args) {
    //~ List<Integer> list = new ArrayList<>();
    //~ list.add(1);
    //~ list.add(2);
    //~ list.add(3);
    //~ System.out.println("Lista creada: " + list.toString());

    //~ System.out.println("Inicio permutations(list): ");
    //~ List<List<Integer>> perms = permutations(list);
    //~ for (List<Integer> perm : perms) {
      //~ System.out.println(perm);
    //~ }
    //~ System.out.println("Fin permutations(list): ");
    //~ System.out.println("");
    //~ System.out.println("Inicio sublists(list): ");
    //~ List<List<Integer>> sublists = sublists(list);
    //~ for (List<Integer> sublist : sublists) {
      //~ System.out.println(sublist);
    //~ }
    //~ System.out.println("Fin sublists(list): ");
  //~ }

  public static <T> List<List<T>> permutations(List<T> list) {
    List<List<T>> result = new ArrayList<>();
    permutationsHelper(list, 0, result);
    return result;
  }

  private static <T> void permutationsHelper(List<T> list, int index, List<List<T>> result) {
    if (index >= list.size()) {
      result.add(new ArrayList<>(list));
      return;
    }
    for (int i = index; i < list.size(); i++) {
      swap(list, index, i);
      permutationsHelper(list, index + 1, result);
      swap(list, index, i); // Para restaurar la lista al estado original
    }
  }

  private static <T> void swap(List<T> list, int i, int j) {
    T temp = list.get(i);
    list.set(i, list.get(j));
    list.set(j, temp);
  }

  public static <T> List<List<T>> sublists(List<T> list) {
    List<List<T>> result = new ArrayList<>();
    for (int i = 0; i <= list.size(); i++) {
      for (int j = i + 1; j <= list.size(); j++) {
        result.add(list.subList(i, j));
      }
    }
    return result;
  }
}
