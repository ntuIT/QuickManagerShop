package com.qman.web.orm.glossary;

import java.util.Map;

import com.qman.web.orm.PersistentEnum;
import com.qman.web.orm.PersistentEnums;

public enum UserStatus implements PersistentEnum<Byte> {
    ACTIVATED((byte) 0, "ACTIVATED"), DISABLED((byte) 1, "UNACTIVED"), CHANGE_PASS((byte) 2, "CHANGE_PASS");

    private static final Map<Byte, UserStatus> INDEX = PersistentEnums.index(UserStatus.class);

    //
    private final byte value;

    private final String displayName;

    private UserStatus(byte value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }

    @Override
    public Byte getValue() { return this.value; }

    @Override
    public String getDisplayName() { return this.displayName; }

    @Override
    public Map<Byte, UserStatus> getAll() { return INDEX; }

    public static UserStatus parse(Byte value) {
        return value == null ? null : INDEX.get(value);
    }
}
