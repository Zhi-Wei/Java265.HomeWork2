package polynomial;

import java.util.StringJoiner;

public class MyPolynomial {

	private double[] coeffs;

	public MyPolynomial(double... coeffs) throws IllegalArgumentException {
		if (coeffs == null) {
			throw new IllegalArgumentException("coeffs");
		}
		this.coeffs = coeffs;
	}

	public int getDegree() {
		int degree = 0;
		for (int i = 0; i < this.coeffs.length; i++) {
			if (this.coeffs[i] != 0) {
				degree = i;
			}
		}
		return degree;
	}

	public String toString() {
		int coeffsLength = this.coeffs.length;
		StringJoiner joiner = new StringJoiner(" + ");
		for (int i = coeffsLength - 1; i >= 0; i--) {
			if (i == 0) {
				joiner.add(String.format("%.1f", this.coeffs[i]));
				continue;
			} else if (i == 1) {
				joiner.add(String.format("%.1f x", this.coeffs[i]));
				continue;
			}
			joiner.add(String.format("%.1f x^%d", this.coeffs[i], i));
		}
		return joiner.toString();
	}

	public double evaluate(double x) {
		double result = 0;
		for (int i = 0; i < this.coeffs.length; i++) {
			result += (this.coeffs[i] * Math.pow(x, i));
		}
		return result;
	}

	public MyPolynomial add(MyPolynomial rigth) {
		int maxLength = (int) Math.max(this.coeffs.length, rigth.coeffs.length);
		double[] emptyCoeffs = new double[maxLength];
		MyPolynomial result = new MyPolynomial(emptyCoeffs);

		for (int i = 0; i <= this.getDegree(); i++) {
			result.coeffs[i] += this.coeffs[i];
		}
		for (int i = 0; i <= rigth.getDegree(); i++) {
			result.coeffs[i] += rigth.coeffs[i];
		}
		return result;
	}

	public MyPolynomial multiply(MyPolynomial rigth) {
		int thisDegree = this.getDegree();
		int rigthDegree = rigth.getDegree();
		int maxLength = thisDegree + rigthDegree + 1;
		double[] emptyCoeffs = new double[maxLength];
		MyPolynomial result = new MyPolynomial(emptyCoeffs);

		for (int i = 0; i <= thisDegree; i++) {
			for (int j = 0; j <= rigthDegree; j++) {
				result.coeffs[i + j] += (this.coeffs[i] * rigth.coeffs[j]);
			}
		}
		return result;
	}
}
