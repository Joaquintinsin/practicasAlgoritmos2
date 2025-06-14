package main;

import conjuntos.Conjunto;
import listas.Lista;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    System.out.println("Inicio main de Listas");
    List<Integer> list = new ArrayList<>();
    list.add(1);
    list.add(2);
    list.add(3);
    System.out.println("Lista creada: " + list.toString());
    System.out.println("Inicio permutations(list): ");
    List<List<Integer>> perms = Lista.permutations(list);
    for (List<Integer> perm : perms) {
      System.out.println(perm);
    }
    System.out.println("Fin permutations(list): ");
    System.out.println("");
    System.out.println("Inicio sublists(list): ");
    List<List<Integer>> sublists = Lista.sublists(list);
    for (List<Integer> sublist : sublists) {
      System.out.println(sublist);
    }
    System.out.println("Fin sublists(list): ");
    System.out.println("Fin main de Listas");
    
    System.out.println("");
    
    System.out.println("Inicio main de Conjuntos");
    Conjunto<Integer> conjunto = new Conjunto<>();
    HashSet<Integer> set = new HashSet<>();
    set.add(1);
    set.add(2);
    set.add(3);
    System.out.println("Conjunto creado: " + set.toString());
    System.out.println("Inicio subsets(set): ");
    Set<Set<Integer>> subsets = conjunto.subsets(set);
    for (Set<Integer> subset : subsets) {
      System.out.println(subset);
    }
    System.out.println("Fin subsets(set): ");
    System.out.println("Fin main de Conjuntos");
  }
}
