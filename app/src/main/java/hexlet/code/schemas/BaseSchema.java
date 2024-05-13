package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Базовый абстрактный класс для создания схем валидации данных различных типов.
 * Этот класс предназначен для наследования и реализации конкретных схем валидации.
 *
 * @param <T> тип данных, для которого создается схема валидации
 */
public abstract class BaseSchema<T> {
    protected final Map<String, Predicate<T>> validators = new HashMap<>();

    /**
     * Проверяет, соответствует ли значение всем правилам валидации, определенным в схеме.
     *
     * @param value значение, которое необходимо проверить
     * @return true, если значение соответствует всем правилам валидации, иначе false
     */
    public boolean isValid(T value) {
        return validators.entrySet().stream()
                .allMatch(entry -> entry.getValue().test(value));
    }
}
