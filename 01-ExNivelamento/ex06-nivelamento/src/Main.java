import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Main {

	public static void main(String[] args) {
		
		String path = "C:\\Users\\luizf\\OneDrive\\Área de Trabalho\\main\\PM-TEO\\01-ExNivelamento\\ex06-nivelamento\\file.txt";
		File file = new File(path);
		String Stringdata;
		int dataLengh;
		
		try {
			BufferedReader rd = new BufferedReader(new FileReader(file));
			dataLengh = Integer.parseInt(rd.readLine());
			for(int i = 0; i<dataLengh; i++) {
				Stringdata = rd.readLine();
				try {
					if (Validator.date(Stringdata) & Stringdata != null) {
						String dayOfWeek = Validator.getDayOfWeeK(Stringdata);
						System.out.println("A data " + Stringdata + " é uma data válida e corresponde ao " + dayOfWeek);
					}
				} catch (Exception e) {
					System.out.print("A data " + Stringdata + " contém um " );
					System.out.println(e.getMessage());
				}
			}
			rd.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
	
		
		
		