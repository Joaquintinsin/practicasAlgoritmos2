import java.util.Objects;

public class Tuple<S1, S2> {
  private S1 first;
  private S2 second;

  public Tuple(S1 first, S2 second) {
    this.first = first;
    this.second = second;
  }

  public S1 getFirst() {
    return first;
  }

  public S2 getSecond() {
    return second;
  }

  public void setFirst(S1 newFirst) {
    this.first = newFirst;
  }

  public void setSecond(S2 newSecond) {
    this.second = newSecond;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Tuple<?, ?> tuple = (Tuple<?, ?>) o;
    return Objects.equals(first, tuple.first) && Objects.equals(second, tuple.second);
  }

  @Override
  public int hashCode() {
    return Objects.hash(first, second);
  }

  @Override
  public String toString() {
    return "(" + first + ", " + second + ")";
  }
}
