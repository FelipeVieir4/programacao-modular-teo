import java.util.Scanner;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		// Ex01();
		// Ex02();
		Ex02();
	}

	// **************************Exercício 01 *************//
	public static void Ex01() {
		System.out.println("*****Exercício 01*****");
		int vet[] = createVector();
		printVector(vet, 6);
		vet = revertVector(vet);
		System.out.print("Vetor invertido: ");
		printVector(vet, 6);
		System.out.println();
		System.out.println("*****Fim exercício 01*****");
	}

	public static void printVector(int vet[], int tam) {
		int count = 0;
		System.out.print("Vetor: ");
		while (count < 6) {
			System.out.print(" " + vet[count]);
			count++;
		}
	}

	public static int[] createVector() {
		Scanner sc = new Scanner(System.in);
		int vet[] = new int[6];
		int count = 0;
		while (count < 6) {
			System.out.println("Digite um número para preencher a posição " + count + " do vetor");
			vet[count] = sc.nextInt();
			count++;
		}
		sc.close();

		return vet;
	}

	public static int[] revertVector(int vet[]) {
		System.out.println();
		int len = vet.length;
		int lastPosition = vet.length - 1;
		int vet2[] = new int[len];
		int count = 0;

		for (int i = 0; i < 6; i++) {
			vet2[count] = vet[lastPosition];
			count++;
			lastPosition--;
		}
		return vet2;
	}

	// **************************Exercício 06 *************//

	public static void Ex02() {

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

	// ******Classes********//
	class Vector {

		private int len;
		private int vet[];

		Vector() {
			setLen(6);
			int v[] = { 1, 3, 5, 7, 9, 11 };
			setVet(v);
		}

		Vector(int tam) {
			setLen(tam);
			int v[] = new int[tam];
			Random gen = new Random();

			for (int i = 0; i < len; i++) {
				v[i] = gen.nextInt(31);
			}

			setVet(v);
		}

		Vector(int[] vector) {
			setVet(vector);
			setLen(vector.length);
		}

		public void printVector() {
			int len = getLen();
			System.out.print("Vetor: ");
			for (int i = 0; i < len; i++) {
				System.out.print(" " + vet[i]);
			}
		}

		public int getLen() {
			return len;
		}

		public void setLen(int len) {
			this.len = len;
		}

		public int[] getVet() {
			return vet;
		}

		public void setVet(int vet[]) {
			this.vet = vet;
		}

	}

	class HandleVector {

		public static int[] sumVector(int[] v) {

			int[] vSumed;
			int count = 0;
			if (v.length % 2 == 0) {

				vSumed = new int[v.length / 2];
				int tam = v.length / 2;

				for (int i = 0; i < tam; i++) {
					vSumed[i] = v[count] + v[count + 1];
					count += 2;
				}
			} else {

				vSumed = new int[(v.length / 2) + 1];
				int tam = (v.length / 2) + 1;

				for (int i = 0; i < tam; i++) {
					if (i == tam - 1) {
						vSumed[i] = v[count] + v[count];
						count += 2;
					} else {
						vSumed[i] = v[count] + v[count + 1];
						count += 2;
					}
				}
			}

			return vSumed;

		}
	}
}
