package com.qman.web.orm.entity.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import com.qman.web.orm.glossary.Gender;

/**
 * @author quytm
 *
 */
@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Gender gender) {
        return gender.getValue().intValue();
    }

    @Override
    public Gender convertToEntityAttribute(Integer dbData) {
        return Gender.parse(dbData.byteValue());
    }
}