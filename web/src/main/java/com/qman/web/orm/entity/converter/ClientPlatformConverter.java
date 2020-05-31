package com.qman.web.orm.entity.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import com.qman.web.orm.glossary.ClientPlatform;

@Converter(autoApply = true)
public class ClientPlatformConverter implements AttributeConverter<ClientPlatform, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ClientPlatform accountType) {
        return accountType.getValue().intValue();
    }

    @Override
    public ClientPlatform convertToEntityAttribute(Integer dbData) {
        return ClientPlatform.parse(dbData.byteValue());
    }
}
