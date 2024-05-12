package hexlet.code.Schemes;

import java.util.function.Predicate;
import java.util.HashMap;
import java.util.Map;

public class NumberSchema {
    private Map<String, Predicate<Integer>> validators = new HashMap<>();

    public NumberSchema required() {
        validators.put("required", value -> value != null && (value instanceof Integer));
        return this;
    }

    public NumberSchema positive() {
        validators.put("positive", value -> value == null || value > 0);
        return this;
    }

    public NumberSchema range(Integer left, Integer right) {
        validators.put("range", value -> value == null || (left <= value && value <= right));
        return this;
    }


    public boolean isValid(Integer value) {
        return validators.values().stream().allMatch(rule -> rule.test(value));
    }
}
