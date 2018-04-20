package fr.epsi.ipeda.dal.converters;

import java.math.BigInteger;
import java.time.Duration;

import javax.persistence.AttributeConverter;

public class DurationAttributeConverter implements AttributeConverter<Duration, BigInteger> {

	@Override
	public BigInteger convertToDatabaseColumn(Duration attribute) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Duration convertToEntityAttribute(BigInteger dbData) {
		// TODO Auto-generated method stub
		return null;
	}

}
