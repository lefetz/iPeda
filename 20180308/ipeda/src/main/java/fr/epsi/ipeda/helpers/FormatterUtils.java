package fr.epsi.ipeda.helpers;

import java.time.format.DateTimeFormatter;

public class FormatterUtils {

	public static DateTimeFormatter getDateTimeFormatterFR() {
		return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	}

}
