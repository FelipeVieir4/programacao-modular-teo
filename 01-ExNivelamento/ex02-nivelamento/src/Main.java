import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.println("Deseja informar o tamanho do vetor?");
		System.out.println("1 - sim");
		System.out.println("2 - não");
		int userInput = sc.nextInt();

		Vector v;

		if (userInput == 1) {
			System.out.println("Informe o tamanho do vetor:");
			userInput = sc.nextInt();
			v = new Vector(userInput);
		} else {
			v = new Vector();
		}
		
		v.printVector();
		System.out.println();
		int[] vSumed;
		vSumed = HandleVector.sumVector(v.getVet());
		Vector v2 = new Vector(vSumed);
		System.out.print("Vetor somado: ");
		v2.printVector();

		sc.close();
	}

}
