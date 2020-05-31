package com.qman.web.masterdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:message.properties", encoding = "UTF-8")
public class ErrorMessageHolder {

    @Autowired
    private Environment env;

    public String getMessByKey(String key) {
        return this.env.getProperty(key);
    }
}