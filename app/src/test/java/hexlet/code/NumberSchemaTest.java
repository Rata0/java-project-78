package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import hexlet.code.schemes.NumberSchema;
import org.junit.jupiter.api.Test;

public class NumberSchemaTest {
    @Test
    void testNoValidators() {
        NumberSchema schema = new NumberSchema();
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(0));
        assertTrue(schema.isValid(10));
        assertTrue(schema.isValid(-10));
    }

    @Test
    void testRequired() {
        NumberSchema schema = new NumberSchema().required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(0));
        assertTrue(schema.isValid(10));
        assertTrue(schema.isValid(-10));
    }

    @Test
    void testPositive() {
        NumberSchema schema = new NumberSchema().positive();
        assertTrue(schema.isValid(null));
        assertFalse(schema.isValid(0));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(-10));
    }

    @Test
    void testRange() {
        NumberSchema schema = new NumberSchema().range(5, 10);
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }

    @Test
    void testRequiredAndPositive() {
        NumberSchema schema = new NumberSchema().required().positive();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(0));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(-10));
    }

    @Test
    void testRequiredAndRange() {
        NumberSchema schema = new NumberSchema().required().range(5, 10);
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }

    @Test
    void testPositiveAndRange() {
        NumberSchema schema = new NumberSchema().positive().range(5, 10);
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
        NumberSchema schema = new NumberSchema().required().positive().range(5, 10);
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(0));
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
        assertFalse(schema.isValid(-10));
    }
}
