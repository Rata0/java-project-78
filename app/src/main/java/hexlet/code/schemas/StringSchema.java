package hexlet.code.schemas;

/**
 * Схема для валидации строковых значений.
 * Предоставляет методы для задания правил валидации строк,
 * такие как обязательность, минимальная длина и содержание подстроки.
 * Класс может быть расширен для добавления дополнительных правил валидации.
 */
public class StringSchema extends BaseSchema<String> {

    /**
     * Добавляет правило обязательности строки.
     * Строка считается невалидной, если она равна null или пустая.
     *
     * @return текущий объект StringSchema для возможности чейнинга вызовов
     */
    public StringSchema required() {
        validators.put("required", value -> value != null && !value.isEmpty());
        return this;
    }

    /**
     * Добавляет правило минимальной длины строки.
     * Строка считается невалидной, если она равна null или ее длина меньше указанной.
     *
     * @param length минимальная допустимая длина строки
     * @return текущий объект StringSchema для возможности чейнинга вызовов
     */
    public StringSchema minLength(int length) {
        validators.put("minLength", value -> value == null || value.length() >= length);
        return this;
    }

    /**
     * Добавляет правило наличия подстроки в строке.
     * Строка считается невалидной, если она равна null или не содержит указанную подстроку.
     *
     * @param substring подстрока, которая должна присутствовать в строке
     * @return текущий объект StringSchema для возможности чейнинга вызовов
     */
    public StringSchema contains(String substring) {
        validators.put("contains", value -> value == null || value.contains(substring));
        return this;
    }
}
