import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

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
}
