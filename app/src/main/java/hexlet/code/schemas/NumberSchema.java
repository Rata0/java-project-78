package hexlet.code.schemas;

import java.util.Objects;

public class NumberSchema extends BaseSchema<Integer> {
    public NumberSchema required() {
        validators.put("required", Objects::nonNull);
        return this;
    }

    public NumberSchema positive() {
        validators.put("positive", value -> value == null || value > 0);
        return this;
    }

    public NumberSchema range(Integer left, Integer right) {
        validators.put("range", value -> {
            if (value == null) {
                return true;
            }
            return left <= value && value <= right;
        });
        return this;
    }
}
