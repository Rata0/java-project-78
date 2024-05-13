package hexlet.code.schemes;

import java.util.Map;
import java.util.HashMap;

public class MapSchema<V> extends BaseSchema<Map<String, V>> {
    private Map<String, BaseSchema<V>> schemaShapeMap = new HashMap<>();

    public MapSchema<V> required() {
        validators.put("required", value -> value != null);
        return this;
    }

    public MapSchema<V> sizeof(int size) {
        validators.put("sizeof", value -> value == null || size == value.size());
        return this;
    }

    public MapSchema<V> shape(Map<String, BaseSchema<V>> validators) {
        schemaShapeMap = validators;
        return this;
    }

    @Override
    public boolean isValid(Map<String, V> value) {
        boolean result = super.isValid(value);
        if (!result || value == null) {
            return result;
        }

        for (Map.Entry<String, BaseSchema<V>> entry : schemaShapeMap.entrySet()) {
            String key = entry.getKey();
            BaseSchema<V> schema = entry.getValue();
            if (!schema.isValid(value.get(key))) {
                return false;
            }
        }

        return true;
    }
}
