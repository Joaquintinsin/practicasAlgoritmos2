package util.math;

import java.util.Set;

public class EuclideanDistance<T> {
	private Set<Pair<T>> set = null;
	private Pair<Pair<T>> p = null;

	public EuclideanDistance() {
		super();
	}

	public EuclideanDistance(Set<Pair<T>> s) {
		if (s == null)
			throw new NullPointerException("Constructor with a set must be non-null");
		if (s.size() < 2)
			throw new UnsupportedOperationException("Set must have at least two points");
		set = s;
		calculateMinDistance();
	}

	public Pair<Pair<T>> getMinPoints() {
		return p;
	}

	public Set<Pair<T>> getCurrentSet() {
		return set;
	}

	public void setCurrentSet(Set<Pair<T>> newSet) {
		if (newSet == null)
			throw new NullPointerException("The new set must be non-null");
		if (newSet.size() < 2)
			throw new UnsupportedOperationException("Set must have at least two points");
		set = newSet;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void calculateMinDistance() {
		Object[] s_to_a = set.toArray();

		p = new Pair((Pair<T>) s_to_a[0], (Pair<T>) s_to_a[1]);
		for (int i = 0; i < set.size(); i++) {
			Pair<T> A = (Pair<T>) s_to_a[i];
			for (int j = i + 1; j < set.size(); j++) {
				Pair<T> B = (Pair<T>) s_to_a[j];
				if (distEucl(A, B) < distEucl(p.fst(), p.snd())) {
					p.setFirst(A);
					p.setSecond(B);
				}
			}
		}
	}

	private double distEucl(Pair<T> p1, Pair<T> p2) {
		return Math.sqrt(Math.pow((int) p1.fst() - (int) p2.fst(), 2) + Math.pow((int) p1.snd() - (int) p2.snd(), 2));
	}
}
