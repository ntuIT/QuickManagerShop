package com.qman.web.orm;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 
 * @author DEV-LongDT
 */
public interface PersistentEnum<T> {

    @JsonValue
    T getValue();

    String getDisplayName();

    Map<T, ? extends PersistentEnum<T>> getAll();
}
