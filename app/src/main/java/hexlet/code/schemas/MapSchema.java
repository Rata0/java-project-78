package hexlet.code.schemas;

import java.util.Map;
import java.util.HashMap;
import java.util.Objects;

/**
 * Схема для валидации словарей (Map).
 * Предоставляет методы для задания правил валидации для ключей и значений словаря.
 * Класс может быть расширен для добавления дополнительных правил валидации.
 *
 * @param <V> тип значений в словаре
 */
public class MapSchema<V> extends BaseSchema<Map<String, V>> {
    private Map<String, BaseSchema<? super V>> schemaShapeMap = new HashMap<>();

    /**
     * Добавляет правило обязательности словаря.
     * Словарь считается невалидным, если он равен null.
     *
     * @return текущий объект MapSchema для возможности чейнинга вызовов
     */
    public MapSchema<V> required() {
        validators.put("required", Objects::nonNull);
        return this;
    }

    /**
     * Добавляет правило проверки размера словаря.
     * Словарь считается невалидным, если он равен null или его размер не соответствует указанному значению.
     *
     * @param size ожидаемый размер словаря
     * @return текущий объект MapSchema для возможности чейнинга вызовов
     */
    public MapSchema<V> sizeof(int size) {
        validators.put("sizeof", value -> value == null || size == value.size());
        return this;
    }

    /**
     * Задает схемы валидации для значений в словаре.
     * Для каждого ключа в словаре будет применяться соответствующая схема валидации.
     *
     * @param validators словарь, где ключи - строковые представления ключей в исходном словаре,
     *                  а значения - схемы валидации для соответствующих значений
     */
    public void shape(Map<String, BaseSchema<? super V>> validators) {
        schemaShapeMap = validators;
    }

    /**
     * Проверяет, соответствует ли словарь всем правилам валидации, определенным в схеме.
     *
     * @param value словарь, который необходимо проверить
     * @return true, если словарь соответствует всем правилам валидации, иначе false
     */
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
