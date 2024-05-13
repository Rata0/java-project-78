package hexlet.code;

import hexlet.code.schemes.BaseSchema;
import hexlet.code.schemes.StringSchema;
import hexlet.code.schemes.NumberSchema;
import hexlet.code.schemes.MapSchema;

public class Validator {
    public StringSchema string() {
        return new StringSchema();
    }

    public NumberSchema number() {
        return new NumberSchema();
    }

    public MapSchema map() {
        return new MapSchema();
    }

    public BaseSchema baseSchema() {
        return new BaseSchema();
    }
}
