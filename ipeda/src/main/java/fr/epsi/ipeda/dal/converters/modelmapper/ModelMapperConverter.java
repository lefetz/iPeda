package fr.epsi.ipeda.dal.converters.modelmapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;

public class ModelMapperConverter {

	public static Converter<LocalDate, String> localDateToString = new AbstractConverter<LocalDate, String>() {
		@Override
		protected String convert(LocalDate source) {
			return source == null ? null : source.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		}
	};

}
