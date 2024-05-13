package hexlet.code.Schemes;

public class NumberSchema extends BaseSchema<Integer> {
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
}
