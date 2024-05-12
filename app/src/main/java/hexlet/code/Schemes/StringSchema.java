package hexlet.code.Schemes;

import java.util.function.Predicate;
import java.util.HashMap;
import java.util.Map;


public class StringSchema {
    private Map<String, Predicate<String>> rules = new HashMap<>();

    public StringSchema required() {
        rules.put("required", value -> value != null && !value.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        rules.put("minLength", value -> value.length() >= length);
        return this;
    }

    public StringSchema contains(String substring) {
        rules.put("contains", value -> value.contains(substring));
        return this;
    }

    public boolean isValid(String value) {
        return rules.values().stream().allMatch(rule -> rule.test(value));
    }
}
