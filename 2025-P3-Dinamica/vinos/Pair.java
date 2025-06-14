public class Pair<F, S> {
	private F first = null;
	private S second = null;

	public Pair() {
		super();
	}

	public Pair(F f, S s) {
		first = f;
		second = s;
	}

	public void setFirst(F f) {
		first = f;
	}

	public void setSecond(S s) {
		second = s;
	}

	public F getFirst() {
		return first;
	}

	public S getSecond() {
		return second;
	}

	public F fst() {
		return getFirst();
	}

	public S snd() {
		return getSecond();
	}

	@Override
	public String toString() {
		return new String("(" + first + "," + second + ")");
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Pair) {
			Pair<F, S> p = (Pair<F, S>) obj;
			return first.equals(p.getFirst()) && second.equals(p.getSecond());
		}
		return false;
	}
}
