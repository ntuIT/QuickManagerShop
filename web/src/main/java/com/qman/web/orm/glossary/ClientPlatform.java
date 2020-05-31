package com.qman.web.orm.glossary;

import java.util.Map;
import com.qman.web.orm.PersistentEnum;
import com.qman.web.orm.PersistentEnums;

public enum ClientPlatform implements PersistentEnum<Byte> {
    ANDROID((byte) 0, "ANDROID"), IOS((byte) 1, "IOS"), WEB((byte) 2, "WEB");

    private static final Map<Byte, ClientPlatform> INDEX = PersistentEnums.index(ClientPlatform.class);

    private final byte value;

    private final String displayName;

    private ClientPlatform(byte value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }

    @Override
    public Byte getValue() { return this.value; }

    @Override
    public String getDisplayName() { return this.displayName; }

    @Override
    public Map<Byte, ClientPlatform> getAll() { return INDEX; }

    public static ClientPlatform parse(Byte value) {
        return value == null ? null : INDEX.get(value);
    }
}
