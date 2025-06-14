package examples.jugs;

import java.util.LinkedList;
import java.util.List;

import conventionalsearch.StateProblem;

public class JugsInformedStateProblem implements StateProblem<JugsInformedState> {
  private JugsInformedState initial;

  /**
   * Constructor of class JugsInformedStateProblem. It initialises the contents of
   * the two jugs with the provided parameters.
   *
   * @param contentA is the value to set the contents of jug A.
   * @param contentB is the value to set the contents of jug B.
   *                 @pre. 0<=contentA<=4 and 0<=contentB<=3.
   *                 @post. It sets the initial state for the two jugs problem to
   *                 be (contentA,contentB).
   */
  public JugsInformedStateProblem(int contentA, int contentB) {

    initial = new JugsInformedState(contentA, contentB);

  }

  @Override
  public JugsInformedState initialState() {
    return initial;
  }

  @Override
  public List<JugsInformedState> getSuccessors(JugsInformedState s) {
    List<JugsInformedState> result = new LinkedList<JugsInformedState>();
    // we empty the contents of jug A
    if (s.getContentsA() > 0) {
      result.add(new JugsInformedState(0, s.getContentsB(), s));
    }
    // we empty the contents of jug B
    if (s.getContentsB() > 0) {
      result.add(new JugsInformedState(s.getContentsA(), 0, s));
    }
    // we fill jug A
    if (s.getContentsA() < 4) {
      result.add(new JugsInformedState(4, s.getContentsB(), s));
    }
    // we fill jug B
    if (s.getContentsB() < 3) {
      result.add(new JugsInformedState(s.getContentsA(), 3, s));
    }
    // we pour contents of A in B
    if (s.getContentsA() > 0 && s.getContentsB() < 3) {
      int initA = s.getContentsA();
      int initB = s.getContentsB();
      while (initA > 0 && initB < 3) {
        initA--;
        initB++;
      }
      result.add(new JugsInformedState(initA, initB, s));
    }
    // we pour contents of B in A
    if (s.getContentsB() > 0 && s.getContentsA() < 4) {
      int initA = s.getContentsA();
      int initB = s.getContentsB();
      while (initB > 0 && initA < 4) {
        initA++;
        initB--;
      }
      result.add(new JugsInformedState(initA, initB, s));
    }
    return result;
  }

}
