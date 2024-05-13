package hexlet.code;

import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.MapSchema;

/**
 * Класс Validator предоставляет методы для создания экземпляров схем валидации различных типов данных.
 * Класс не предназначен для наследования.
 */
public final class Validator {

    /**
     * Создает экземпляр схемы валидации строк.
     *
     * @return новый экземпляр StringSchema
     */
    public StringSchema string() {
        return new StringSchema();
    }

    /**
     * Создает экземпляр схемы валидации чисел.
     *
     * @return новый экземпляр NumberSchema
     */
    public NumberSchema number() {
        return new NumberSchema();
    }

    /**
     * Создает экземпляр схемы валидации словарей (map).
     *
     * @return новый экземпляр MapSchema
     */
    public MapSchema map() {
        return new MapSchema();
    }
}
