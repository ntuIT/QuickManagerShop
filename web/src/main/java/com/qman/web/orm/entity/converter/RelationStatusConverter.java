/**
 * 
 */
package com.qman.web.orm.entity.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import com.qman.web.orm.glossary.RelationStatus;

/**
 * @author quytm
 *
 */
@Converter(autoApply = true)
public class RelationStatusConverter implements AttributeConverter<RelationStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(RelationStatus relationStatus) {
        return relationStatus.getValue().intValue();
    }

    @Override
    public RelationStatus convertToEntityAttribute(Integer dbData) {
        return RelationStatus.parse(dbData.byteValue());
    }
}
