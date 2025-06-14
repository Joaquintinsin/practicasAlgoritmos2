package examples.eightqueens;

import engine.BestFirstEngine;
import engine.BreadthFirstEngine;
import engine.DepthFirstEngine;
import engine.IterativeDeepeningEngine;

import java.util.List;

public class QueensSearchApp {
  public static void main(String[] args) {
    int NSize = 8;
    QueensStateProblem sp1 = new QueensStateProblem(NSize);

    /* App using Breadth-first search */
    appUsingBFS(sp1);

    /* App using Depth-first search */
    appUsingDFS(sp1);

    /* App using Iterative-Deepening search */
    appUsingIterativeDeepening(sp1);

    /* App Using Informed State */
    QueensInformedStateProblem queensInf = new QueensInformedStateProblem(NSize);
    appUsingInformedAndBestFirstSearch(queensInf);

    return;
  }

  public static void appUsingBFS(QueensStateProblem sp1) {
    BreadthFirstEngine<QueensState, QueensStateProblem> engineBfs = new BreadthFirstEngine<QueensState, QueensStateProblem>(
        sp1);
    QueensState successBfS = engineBfs.performSearch();
    System.out.println();
    System.out.println("*** Result using Breadth-first search ***");
    System.out.println("Solution found? \n" + String.valueOf(successBfS));
    if (!(successBfS == null)) {
      System.out.print("Path to goal: ");
      List<QueensState> pathBfS = engineBfs.getPath();
      for (int i = 0; i < pathBfS.size(); i++) {
        System.out.println();
        QueensState current = (QueensState) pathBfS.get(i);
        System.out.print(current.toString());
      }
      System.out.println();
    }
    engineBfs.report();
    return;
  }

  public static void appUsingDFS(QueensStateProblem sp1) {
    DepthFirstEngine<QueensState, QueensStateProblem> engineDfs = new DepthFirstEngine<QueensState, QueensStateProblem>(
        sp1);
    QueensState successDfS = engineDfs.performSearch();
    System.out.println();
    System.out.println("*** Result using Depth-first search ***");
    System.out.println("Solution found? \n" + String.valueOf(successDfS));
    if (!(successDfS == null)) {
      System.out.print("Path to goal: ");
      List<QueensState> pathDfS = engineDfs.getPath();
      for (int i = 0; i < pathDfS.size(); i++) {
        System.out.println();
        QueensState current = (QueensState) pathDfS.get(i);
        System.out.print(current.toString());
      }
      System.out.println();
    }
    engineDfs.report();
    return;
  }

  public static void appUsingIterativeDeepening(QueensStateProblem sp1) {
    IterativeDeepeningEngine<QueensState, QueensStateProblem> engineIterative = new IterativeDeepeningEngine<QueensState, QueensStateProblem>(
        sp1);
    QueensState successIterative = engineIterative.performSearch();
    System.out.println();
    System.out.println("*** Result using Iterative-Deepening search ***");
    System.out.println("Solution found? \n" + successIterative.toString());
    if (!(successIterative == null)) {
      System.out.print("Path to goal: ");
      List<QueensState> pathIterative = engineIterative.getPath();
      for (int i = 0; i < pathIterative.size(); i++) {
        System.out.println();
        QueensState current = (QueensState) pathIterative.get(i);
        System.out.print(current.toString());
      }
      System.out.println();
    }
    engineIterative.report();
    return;
  }

  public static void appUsingInformedAndBestFirstSearch(QueensInformedStateProblem queensInf) {
    BestFirstEngine<QueensInformedState, QueensInformedStateProblem> engineBest = new BestFirstEngine<QueensInformedState, QueensInformedStateProblem>(
        queensInf);
    QueensInformedState successBest = engineBest.performSearch();
    System.out.println();
    System.out.println("*** Result using Best-first search ***");
    System.out.println("Solution found? \n" + successBest.toString());
    if (!(successBest == null)) {
      System.out.print("Path to goal: ");
      List<QueensInformedState> pathBest = engineBest.getPath();
      for (int i = 0; i < pathBest.size(); i++) {
        System.out.println();
        QueensInformedState current = (QueensInformedState) pathBest.get(i);
        System.out.print(current.toString());
      }
      System.out.println();
    }
    engineBest.report();
    return;
  }
}
