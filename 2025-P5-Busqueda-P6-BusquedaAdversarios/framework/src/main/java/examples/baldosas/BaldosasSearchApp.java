package examples.baldosas;

import engine.BestFirstEngine;
import engine.BreadthFirstEngine;
import engine.DepthFirstEngine;
import engine.IterativeDeepeningEngine;
// import examples.baldosas.BaldosasState;
// import examples.baldosas.BaldosasStateProblem;
// import examples.baldosas.BaldosasInformedState;
// import examples.baldosas.BaldosasInformedStateProblem;

import java.util.List;

public class BaldosasSearchApp {
  /**
   * Main for Baldosas App.
   *
   * @param args Position X Y of the initial empty socket
   */

  public static void main(String[] args) {
    if (args.length != 2) {
      System.out.println("*** Usage: java BaldosasSearchApp <int> <int>");
      return;
    }
    int a = Integer.parseInt(args[0]);
    int b = Integer.parseInt(args[1]);

    BaldosasStateProblem sp1 = new BaldosasStateProblem(a, b);

    /* App using Breadth-first search */
    // appUsingBFS(sp1);

    /* App using Depth-first search */
    // appUsingDFS(sp1);

    /* App using Iterative-Deepening search */
    // appUsingIterativeDeepening(sp1);

    /* App using Informed search and Best-First Search */
    BaldosasInformedStateProblem baldosasInformed = new BaldosasInformedStateProblem(a, b);
    appUsingInformedAndBestFirstSearch(baldosasInformed);
    return;
  }

  public static void appUsingBFS(BaldosasStateProblem sp1) {
    BreadthFirstEngine<BaldosasState, BaldosasStateProblem> engineBfs = new BreadthFirstEngine<BaldosasState, BaldosasStateProblem>(
        sp1);
    BaldosasState successBfS = engineBfs.performSearch();
    System.out.println();
    System.out.println("*** Result using Breadth-first search ***");
    System.out.println("Solution found? " + successBfS.toString());
    if (!(successBfS == null)) {
      System.out.print("Path to goal: ");
      List<BaldosasState> pathBfS = engineBfs.getPath();
      for (int i = 0; i < pathBfS.size(); i++) {
        BaldosasState current = (BaldosasState) pathBfS.get(i);
        System.out.print(current.toString());
      }
      System.out.println();
    }
    engineBfs.report();
    return;
  }

  public static void appUsingDFS(BaldosasStateProblem sp1) {
    DepthFirstEngine<BaldosasState, BaldosasStateProblem> engineDfs = new DepthFirstEngine<BaldosasState, BaldosasStateProblem>(
        sp1);
    BaldosasState successDfS = engineDfs.performSearch();
    System.out.println();
    System.out.println("*** Result using Depth-first search ***");
    System.out.println("Solution found? " + successDfS.toString());
    if (!(successDfS == null)) {
      System.out.print("Path to goal: ");
      List<BaldosasState> pathDfS = engineDfs.getPath();
      for (int i = 0; i < pathDfS.size(); i++) {
        BaldosasState current = (BaldosasState) pathDfS.get(i);
        System.out.print(current.toString());
      }
      System.out.println();
    }
    engineDfs.report();
    return;
  }

  public static void appUsingIterativeDeepening(BaldosasStateProblem sp1) {
    IterativeDeepeningEngine<BaldosasState, BaldosasStateProblem> engineIterative = new IterativeDeepeningEngine<BaldosasState, BaldosasStateProblem>(
        sp1);
    BaldosasState successIterative = engineIterative.performSearch();
    System.out.println();
    System.out.println("*** Result using Iterative-Deepening search ***");
    System.out.println("Solution found? " + successIterative.toString());
    if (!(successIterative == null)) {
      System.out.print("Path to goal: ");
      List<BaldosasState> pathIterative = engineIterative.getPath();
      for (int i = 0; i < pathIterative.size(); i++) {
        BaldosasState current = (BaldosasState) pathIterative.get(i);
        System.out.print(current.toString());
      }
      System.out.println();
    }
    engineIterative.report();
    return;
  }

  public static void appUsingInformedAndBestFirstSearch(BaldosasInformedStateProblem baldosasInformed) {
    BestFirstEngine<BaldosasInformedState, BaldosasInformedStateProblem> engineBest = new BestFirstEngine<BaldosasInformedState, BaldosasInformedStateProblem>(
        baldosasInformed);
    BaldosasInformedState successBest = engineBest.performSearch();
    System.out.println();
    System.out.println("*** Result using Best-first search ***");
    System.out.println("Solution found? " + successBest.toString());
    if (!(successBest == null)) {
      System.out.print("Path to goal: ");
      List<BaldosasInformedState> pathBest = engineBest.getPath();
      for (int i = 0; i < pathBest.size(); i++) {
        BaldosasInformedState current = (BaldosasInformedState) pathBest.get(i);
        System.out.print(current.toString());
      }
      System.out.println();
    }
    engineBest.report();
    return;
  }
}
