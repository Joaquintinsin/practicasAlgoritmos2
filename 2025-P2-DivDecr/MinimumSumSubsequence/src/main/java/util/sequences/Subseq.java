package util.sequences;

public class Subseq<T> {

	private T sum;
	private int lowerIndex;
	private int upperIndex;

	public Subseq() {
		this.sum = null;
		this.lowerIndex = -1;
		this.upperIndex = -1;
	}

	public Subseq(T sum, int lower, int upper) {
		this.sum = sum;
		this.lowerIndex = lower;
		this.upperIndex = upper;
	}

	public T getSum() {
		return sum;
	}

	public void setSum(T sum) {
		this.sum = sum;
	}

	public int getLowerIndex() {
		return lowerIndex;
	}

	public void setLowerIndex(int lower) {
		this.lowerIndex = lower;
	}

	public int getUpperIndex() {
		return upperIndex;
	}

	public void setUpperIndex(int upper) {
		this.upperIndex = upper;
	}

	@Override
	public String toString() {
		return ("(" + sum.toString() + "," + lowerIndex + "," + upperIndex + "," + ")");
	}

	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		if (!(other instanceof Subseq)) {
			return false;
		}
		Subseq<T> actualOther = (Subseq<T>) other;
		boolean equals = actualOther.getSum().equals(this.sum);
		equals &= actualOther.getLowerIndex() == this.lowerIndex;
		equals &= actualOther.getUpperIndex() == this.upperIndex;
		return equals;
	}
}
