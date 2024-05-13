package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

public class StringSchemaTest {

    @Test
    void testNoValidators() {
        var v = new Validator();
        var schema = v.string();
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid("hello"));
    }

    @Test
    void testRequired() {
        var v = new Validator();
        var schema = v.string().required();
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid("hello"));
    }

    @Test
    void testMinLength() {
        var v = new Validator();
        var schema = v.string().minLength(5);
        assertTrue(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("hello"));
        assertFalse(schema.isValid("hi"));
    }


    @Test
    void testContains() {
        var v = new Validator();
        var schema = v.string().contains("hello");
        assertTrue(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("hello world"));
        assertFalse(schema.isValid("hi"));
    }

    @Test
    void testRequiredAndMinLength() {
        StringSchema schema = new StringSchema().required().minLength(5);
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid("hello"));
        assertFalse(schema.isValid("hi"));
    }

    @Test
    void testRequiredAndContains() {
        StringSchema schema = new StringSchema().required().contains("hello");
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid("hello world"));
        assertFalse(schema.isValid("hi"));
    }

    @Test
    void testMinLengthAndContains() {
        StringSchema schema = new StringSchema().minLength(5).contains("hello");
        assertTrue(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("hello world"));
        assertFalse(schema.isValid("hi"));
    }

    @Test
    void testAllValidators() {
        StringSchema schema = new StringSchema().required().minLength(5).contains("hello");
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid("hello world"));
        assertFalse(schema.isValid("hi"));
    }

    @Test
    void testOverridingMinLength() {
        StringSchema schema = new StringSchema().minLength(10).minLength(4);
        assertTrue(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("hello"));
        assertFalse(schema.isValid("hi"));
    }
}
