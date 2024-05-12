package hexlet.code;

import hexlet.code.Schemes.StringSchema;

public class Validator {
    public StringSchema string() {
        return new StringSchema();
    }

    public static void main(String[] args) {
        var v = new Validator();
        var schema = v.string();
        schema.required();
        System.out.println(schema.contains("wh").isValid("what does the fox say"));
        System.out.println(schema.contains("what").isValid("what does the fox say"));
        System.out.println(schema.contains("whatthe").isValid("what does the fox say"));
        System.out.println(schema.isValid("what does the fox say"));
        var schema1 = v.string();
        System.out.println(schema1.minLength(10).minLength(4).isValid("Hexlet")); // true
    }
}
