package org.ccts.balancingact.model;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate date) {
        ZonedDateTime zdt = date.atStartOfDay(ZoneId.of("UTC"));
        return Date.from(zdt.toInstant());
    }

    @Override
    public LocalDate convertToEntityAttribute(Date value) {
        Instant instant = value.toInstant();
        return instant.atZone(ZoneId.of("UTC")).toLocalDate();
    }
}