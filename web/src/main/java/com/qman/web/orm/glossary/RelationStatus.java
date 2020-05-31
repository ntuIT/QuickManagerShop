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
public enum RelationStatus implements PersistentEnum<Byte> {
    SINGLE((byte) 0, "SINGLE"), IN_RELATIONSHIP((byte) 1, "IN RELATIONSHIP"), MARRIED((byte) 2, "MARRIED"), OTHER((byte) 3, "OTHER");

    private static final Map<Byte, RelationStatus> INDEX = PersistentEnums.index(RelationStatus.class);

    private final byte value;

    private final String displayName;

    private RelationStatus(byte value, String displayName) {
        this.value = value;
        this.displayName = displayName;
    }

    @Override
    public Byte getValue() { return this.value; }

    @Override
    public String getDisplayName() { return this.displayName; }

    @Override
    public Map<Byte, RelationStatus> getAll() { return INDEX; }

    public static RelationStatus parse(Byte value) {
        return value == null ? null : INDEX.get(value);
    }
}
