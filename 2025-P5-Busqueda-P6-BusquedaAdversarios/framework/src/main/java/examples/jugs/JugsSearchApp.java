package examples.jugs;

import engine.BestFirstEngine;
import engine.BreadthFirstEngine;
import engine.DepthFirstEngine;
import engine.IterativeDeepeningEngine;

import java.util.List;

public class JugsSearchApp {
  /**
   * Main for Jugs App.
   *
   * @param args contents Jug A and contents Jug B are expected.
   */

  public static void main(String[] args) {

    if (args.length != 2) {
      System.out.println("*** Usage: java JugsSearchApp <int> <int>");
      return;
    }
    int a = Integer.parseInt(args[0]);
    int b = Integer.parseInt(args[1]);

    JugsStateProblem sp1 = new JugsStateProblem(a, b);

    /* App using Breadth-first search */
    appUsingBFS(sp1);

    /* App using Depth-first search */
    appUsingDFS(sp1);

    /* App using Iterative-Deepening search */
    appUsingIterativeDeepening(sp1);

    /* App Using Informed State */
    JugsInformedStateProblem jugsInf = new JugsInformedStateProblem(a, b);
    appUsingInformedAndBestFirstSearch(jugsInf);

    return;
  }

  public static void appUsingBFS(JugsStateProblem sp1) {
    BreadthFirstEngine<JugsState, JugsStateProblem> engineBfs = new BreadthFirstEngine<JugsState, JugsStateProblem>(
        sp1);
    JugsState successBfS = engineBfs.performSearch();
    System.out.println();
    System.out.println("*** Result using Breadth-first search ***");
    System.out.println("Solution found? " + successBfS.toString());
    if (!(successBfS == null)) {
      System.out.print("Path to goal: ");
      List<JugsState> pathBfS = engineBfs.getPath();
      for (int i = 0; i < pathBfS.size(); i++) {
        System.out.println();
        JugsState current = (JugsState) pathBfS.get(i);
        System.out.print(current.toString());
      }
      System.out.println();
    }
    engineBfs.report();
    return;
  }

  public static void appUsingDFS(JugsStateProblem sp1) {
    DepthFirstEngine<JugsState, JugsStateProblem> engineDfs = new DepthFirstEngine<JugsState, JugsStateProblem>(
        sp1);
    JugsState successDfS = engineDfs.performSearch();
    System.out.println();
    System.out.println("*** Result using Depth-first search ***");
    System.out.println("Solution found? " + successDfS.toString());
    if (!(successDfS == null)) {
      System.out.print("Path to goal: ");
      List<JugsState> pathDfS = engineDfs.getPath();
      for (int i = 0; i < pathDfS.size(); i++) {
        System.out.println();
        JugsState current = (JugsState) pathDfS.get(i);
        System.out.print(current.toString());
      }
      System.out.println();
    }
    engineDfs.report();
    return;
  }

  public static void appUsingIterativeDeepening(JugsStateProblem sp1) {
    IterativeDeepeningEngine<JugsState, JugsStateProblem> engineIterative = new IterativeDeepeningEngine<JugsState, JugsStateProblem>(
        sp1);
    JugsState successIterative = engineIterative.performSearch();
    System.out.println();
    System.out.println("*** Result using Iterative-Deepening search ***");
    System.out.println("Solution found? " + successIterative.toString());
    if (!(successIterative == null)) {
      System.out.print("Path to goal: ");
      List<JugsState> pathIterative = engineIterative.getPath();
      for (int i = 0; i < pathIterative.size(); i++) {
        System.out.println();
        JugsState current = (JugsState) pathIterative.get(i);
        System.out.print(current.toString());
      }
      System.out.println();
    }
    engineIterative.report();
    return;
  }

  public static void appUsingInformedAndBestFirstSearch(JugsInformedStateProblem jugsInf) {
    BestFirstEngine<JugsInformedState, JugsInformedStateProblem> engineBest = new BestFirstEngine<JugsInformedState, JugsInformedStateProblem>(
        jugsInf);
    JugsInformedState successBest = engineBest.performSearch();
    System.out.println();
    System.out.println("*** Result using Best-first search ***");
    System.out.println("Solution found? " + successBest.toString());
    if (!(successBest == null)) {
      System.out.print("Path to goal: ");
      List<JugsInformedState> pathBest = engineBest.getPath();
      for (int i = 0; i < pathBest.size(); i++) {
        System.out.println();
        JugsInformedState current = (JugsInformedState) pathBest.get(i);
        System.out.print(current.toString());
      }
      System.out.println();
    }
    engineBest.report();
    return;
  }
}
