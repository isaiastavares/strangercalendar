package br.ufg.inf.mds.strangecalendar.entidade;

import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.joda.time.LocalDate;

@Converter(autoApply = true)
public class LocalDateAttributeConverter
						implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate localDate) {
    	return (localDate == null ? null : localDate.toDate());
    }

    @Override
    public LocalDate convertToEntityAttribute(Date date) {
    	return (date == null ? null : LocalDate.fromDateFields(date));
    }
}
