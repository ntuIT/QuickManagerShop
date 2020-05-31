/**
 * 
 */
package com.qman.web.orm.glossary;

import java.util.Map;

import com.qman.web.orm.PersistentEnum;
import com.qman.web.orm.PersistentEnums;

/**
 * @author quytm
 *
 */
public enum Gender implements PersistentEnum<Byte> {
    MALE((byte) 0, "MALE", "Nam"), FEMALE((byte) 1, "FEMALE", "Nữ"), GAY((byte) 2, "GAY", "Gay"), LESBIAN((byte) 3, "LESBIAN", "Lesbian"),
    OTHER((byte) 4, "OTHER", "Khác");

    private static final Map<Byte, Gender> INDEX = PersistentEnums.index(Gender.class);

    private final byte value;

    private final String displayName;

    private final String displayViName;

    private Gender(byte value, String displayName, String displayViName) {
        this.value = value;
        this.displayName = displayName;
        this.displayViName = displayViName;
    }

    @Override
    public Byte getValue() { return this.value; }

    @Override
    public String getDisplayName() { return this.displayName; }

    public String getDisplayViName() { return this.displayViName; }

    @Override
    public Map<Byte, Gender> getAll() { return INDEX; }

    public static Gender parse(Byte value) {
        return value == null ? null : INDEX.get(value);
    }
}
