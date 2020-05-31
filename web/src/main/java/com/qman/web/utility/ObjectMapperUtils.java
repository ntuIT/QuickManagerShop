package com.qman.web.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

public class ObjectMapperUtils extends ObjectMapper {

    private static final long serialVersionUID = -5970317407549233427L;

    public static ObjectMapperUtils initJdk8Module() {
        ObjectMapperUtils omu = new ObjectMapperUtils();
        omu.registerModule(new Jdk8Module());
        return omu;
    }
}
