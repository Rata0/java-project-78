package hexlet.code.schemes;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected final Map<String, Predicate<T>> validators = new HashMap<>();

    public boolean isValid(T value) {
        return validators.entrySet().stream()
                .allMatch(entry -> entry.getValue().test(value));
    }
}
