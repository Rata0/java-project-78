package hexlet.code.Schemes;

import java.util.function.Predicate;
import java.util.HashMap;
import java.util.Map;

public class StringSchema {
    private Map<String, Predicate<String>> validators = new HashMap<>();

    public StringSchema required() {
        validators.put("required", value -> value != null && !value.isEmpty() && value instanceof String);
        return this;
    }

    public StringSchema minLength(int length) {
        validators.put("minLength", value -> value == null || value.length() >= length);
        return this;
    }

    public StringSchema contains(String substring) {
        validators.put("contains", value -> value == null || value.contains(substring));
        return this;
    }

    public boolean isValid(String value) {
        return validators.values().stream().allMatch(rule -> rule.test(value));
    }
}
