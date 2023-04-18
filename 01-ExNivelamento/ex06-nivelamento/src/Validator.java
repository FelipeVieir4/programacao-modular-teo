import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Validator {

	public static boolean date(String str) throws Exception {

		try {
			String[] date = str.split("/");
			if (date.length != 3 || date[0] == null || date[0] == "" || date[1] == null || date[1] == ""
					|| date[2] == null || date[2] == "") {
				throw new Exception("DATA INVÁLIDA! A data não está separada por uma barra ou está incompleta");
			} else {
				// int day = Integer.parseInt(date[0]);
				int month = Integer.parseInt(date[1]);
				int year = Integer.parseInt(date[2]);

				if (isYearValid(year) & isMonthValid(month)) {
					isDayValid(str);
				}
			}
			return true;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public static boolean isYearValid(int year) throws Exception {
		try {
			if (year > 0 & year < 9999) {
				return true;
			} else {
				throw new Exception("Ano inválido");
			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public static boolean isMonthValid(int month) throws Exception {
		try {
			if (month > 0 & month < 13) {
				return true;
			} else {
				throw new Exception("Mês inválido");
			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public static boolean isDayValid(String str) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		format.setLenient(false);
		try {
			format.parse(str);
			return true;
		} catch (Exception e) {
			throw new Exception("Dia inválido");
		}
	}

	public static String getDayOfWeeK(String str) throws Exception {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		format.setLenient(false);

		int dayOfWeekInt;
		String dayOfWeekString;

		try {
			cal.setTime(format.parse(str));
			dayOfWeekInt = cal.get(Calendar.DAY_OF_WEEK);

			switch (dayOfWeekInt) {
			case 1:
				dayOfWeekString = "Domingo";
				break;
			case 2:
				dayOfWeekString = "Segunda-feira";
				break;
			case 3:
				dayOfWeekString = "Terça-feira";
				break;
			case 4:
				dayOfWeekString = "Quarta-feira";
				break;
			case 5:
				dayOfWeekString = "Quinta-feira";
				break;
			case 6:
				dayOfWeekString = "Sexta-feira";
				break;
			case 7:
				dayOfWeekString = "Sábado";
				break;
			default:
				dayOfWeekString = "Inválido";
				break;
			}

			return dayOfWeekString;
		} catch (ParseException e) {
			throw new Exception(e.getMessage());
		}
	}
}
