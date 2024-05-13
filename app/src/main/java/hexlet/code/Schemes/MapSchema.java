package hexlet.code.Schemes;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>> {
    public MapSchema required() {
        validators.put("required", value -> value != null);
        return this;
    }

    public MapSchema sizeof(int size) {
        validators.put("sizeof", value -> value == null || size == value.size());
        return this;
    }
}
