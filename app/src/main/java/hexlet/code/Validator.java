package hexlet.code;

import hexlet.code.Schemes.StringSchema;
import hexlet.code.Schemes.NumberSchema;
import hexlet.code.Schemes.MapSchema;

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
}