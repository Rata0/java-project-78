package hexlet.code.schemes;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema<T> {
    protected Map<String, Predicate<T>> validators = new HashMap<>();

    public boolean isValid(T value) {
        return validators.values().stream().allMatch(rule -> rule.test(value));
    }
}
