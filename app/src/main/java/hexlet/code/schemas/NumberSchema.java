package hexlet.code.schemas;

import java.util.Objects;

/**
 * Схема для валидации числовых значений.
 * Предоставляет методы для задания правил валидации чисел,
 * таких как обязательность, положительность и диапазон значений.
 * Класс может быть расширен для добавления дополнительных правил валидации.
 */
public class NumberSchema extends BaseSchema<Integer> {

    /**
     * Добавляет правило обязательности числа.
     * Число считается невалидным, если оно равно null.
     *
     * @return текущий объект NumberSchema для возможности чейнинга вызовов
     */
    public NumberSchema required() {
        validators.put("required", Objects::nonNull);
        return this;
    }

    /**
     * Добавляет правило положительности числа.
     * Число считается невалидным, если оно равно null или не является положительным.
     *
     * @return текущий объект NumberSchema для возможности чейнинга вызовов
     */
    public NumberSchema positive() {
        validators.put("positive", value -> value == null || value > 0);
        return this;
    }

    /**
     * Добавляет правило диапазона значений числа.
     * Число считается невалидным, если оно равно null или выходит за пределы указанного диапазона.
     *
     * @param left нижняя граница диапазона (включительно)
     * @param right верхняя граница диапазона (включительно)
     * @return текущий объект NumberSchema для возможности чейнинга вызовов
     */
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
