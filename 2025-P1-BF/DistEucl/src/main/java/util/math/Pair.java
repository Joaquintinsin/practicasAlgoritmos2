package util.math;

public class Pair<T> {
	private T first = null;
	private T second = null;

	public Pair() {
		super();
	}

	public Pair(T f, T s) {
		first = f;
		second = s;
	}

	@SuppressWarnings("unchecked")
	public T[] getPair() {
		return (T[]) new Object[] { first, second };
	}

	public void setFirst(T f) {
		first = f;
	}

	public void setSecond(T s) {
		second = s;
	}

	public T getFirst() {
		return first;
	}

	public T getSecond() {
		return second;
	}

	public T fst() {
		return getFirst();
	}

	public T snd() {
		return getSecond();
	}

	@Override
	public String toString() {
		return new String("(" + first + "," + second + ")");
	}
}