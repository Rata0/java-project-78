package hexlet.code;

import hexlet.code.Schemes.StringSchema;
import hexlet.code.Schemes.NumberSchema;

public class Validator {
    public StringSchema string() {
        return new StringSchema();
    }

    public NumberSchema number() {
        return new NumberSchema();
    }
}
