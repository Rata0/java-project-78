package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {
    public StringSchema required() {
        validators.put("required", value -> value != null && !value.isEmpty());
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
}
