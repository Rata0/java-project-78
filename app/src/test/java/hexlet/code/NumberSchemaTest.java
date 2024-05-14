package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;

public class NumberSchemaTest {
    @Test
    void testNoValidators() {
        var v = new Validator();
        NumberSchema schema = v.number();
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(0));
        assertTrue(schema.isValid(10));
        assertTrue(schema.isValid(-10));
    }

    @Test
    void testRequired() {
        var v = new Validator();
        NumberSchema schema = v.number().required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(0));
        assertTrue(schema.isValid(10));
        assertTrue(schema.isValid(-10));
    }

    @Test
    void testPositive() {
        var v = new Validator();
        NumberSchema schema = v.number().positive();
        assertTrue(schema.isValid(null));
        assertFalse(schema.isValid(0));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(-10));
    }

    @Test
    void testRange() {
        var v = new Validator();
        NumberSchema schema = v.number().range(5, 10);
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }

    @Test
    void testRequiredAndPositive() {
        var v = new Validator();
        NumberSchema schema = v.number().required().positive();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(0));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(-10));
    }

    @Test
    void testRequiredAndRange() {
        var v = new Validator();
        NumberSchema schema = v.number().required().range(5, 10);
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }

    @Test
    void testPositiveAndRange() {
        var v = new Validator();
        NumberSchema schema = v.number().positive().range(5, 10);
        assertTrue(schema.isValid(null));
        assertFalse(schema.isValid(0));
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
        assertFalse(schema.isValid(-10));
    }

    @Test
    void testAllValidators() {
        var v = new Validator();
        NumberSchema schema = v.number().required().positive().range(5, 10);
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(0));
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
        assertFalse(schema.isValid(-10));
    }
}
