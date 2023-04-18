import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		String path = "C:\\Users\\luizf\\OneDrive\\Área de Trabalho\\main\\PM-TEO\\01-ExNivelamento\\ex05-nivelamento\\src\\file.txt";
		File file = new File(path);

		try {
			BufferedReader rd = new BufferedReader(new FileReader(file));
			String Stringdata;
			while ((Stringdata = rd.readLine()) != null) {
//				
				String[] dataStringVector = Stringdata.split(";");
				
				System.out.println("Vetor Origianl: " + Stringdata);
				
				Vector vet = new Vector(dataStringVector);
				
				int[] sum = HandleVector.sumVector(vet.getVet());
				
				Vector sumedVet = new Vector(sum);
				
				System.out.print("Vetor somado: " );
				sumedVet.printVector();
				System.out.println();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}