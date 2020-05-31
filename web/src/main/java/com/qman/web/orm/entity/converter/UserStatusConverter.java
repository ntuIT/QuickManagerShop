/**
 * 
 */
package com.qman.web.orm.entity.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.qman.web.orm.glossary.UserStatus;

/**
 * @author DEV-LongDT
 *
 */
@Converter(autoApply = true)
public class UserStatusConverter implements AttributeConverter<UserStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(UserStatus status) {
        return status.getValue().intValue();
    }

    @Override
    public UserStatus convertToEntityAttribute(Integer dbData) {
        return UserStatus.parse(dbData.byteValue());
    }
}
