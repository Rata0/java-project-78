package hexlet.code.schemas;

import java.util.Map;
import java.util.HashMap;
import java.util.Objects;

public class MapSchema<V> extends BaseSchema<Map<String, V>> {
    private Map<String, BaseSchema<? super V>> schemaShapeMap = new HashMap<>();

    public MapSchema<V> required() {
        validators.put("required", Objects::nonNull);
        return this;
    }

    public MapSchema<V> sizeof(int size) {
        validators.put("sizeof", value -> value == null || size == value.size());
        return this;
    }

    public void shape(Map<String, BaseSchema<? super V>> validators) {
        schemaShapeMap = validators;
    }

    @Override
    public boolean isValid(Map<String, V> value) {
        boolean result = super.isValid(value);
        if (!result || value == null) {
            return result;
        }

        for (Map.Entry<String, BaseSchema<? super V>> entry : schemaShapeMap.entrySet()) {
            String key = entry.getKey();
            BaseSchema<? super V> schema = entry.getValue();
            V val = value.get(key);
            if (val == null || !schema.isValid(val)) {
                return false;
            }
        }

        return true;
    }
}
