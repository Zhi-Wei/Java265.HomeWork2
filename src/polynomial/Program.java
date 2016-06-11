package polynomial;

public class Program {

	public static void main(String[] args) {
		MyPolynomial polynomial_1 = new MyPolynomial(3d, 2d, 1d);
		System.out.println("ToString: " + polynomial_1.toString());
		System.out.println("Degree: " + polynomial_1.getDegree());
		System.out.println("Evaluate: " + polynomial_1.evaluate(3));
		
		System.out.println();
		
		MyPolynomial polynomial_2 = new MyPolynomial(4, 3, 2, 1);
		System.out.println("ToString: " + polynomial_2.toString());
		System.out.println("Degree: " + polynomial_2.getDegree());
		System.out.println("Evaluate: " + polynomial_2.evaluate(3));
		
		System.out.println();
		
		MyPolynomial polynomial_3 = polynomial_1.add(polynomial_2);
		System.out.println("ToString: " + polynomial_3.toString());
		System.out.println("Degree: " + polynomial_3.getDegree());
		System.out.println("Evaluate: " + polynomial_3.evaluate(3));
		
		System.out.println();
		
		MyPolynomial polynomial_4 = polynomial_1.multiply(polynomial_2);
		System.out.println("ToString: " + polynomial_4.toString());
		System.out.println("Degree: " + polynomial_4.getDegree());
		System.out.println("Evaluate: " + polynomial_4.evaluate(3));
	}

}
