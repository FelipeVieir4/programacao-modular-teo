import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		System.out.println("Informe uma data no formato DD/MM/AAAA para ser validada");
		String userInput = sc.nextLine();
		//System.out.println("input do usuário = " + userInput);
		try {
			Validator.date(userInput);
			System.out.println(Validator.getDayOfWeeK(userInput));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		sc.close();
	}

}
