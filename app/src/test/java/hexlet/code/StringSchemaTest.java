package hexlet.code;

import hexlet.code.Schemes.StringSchema;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class StringSchemaTest {

    @Test
    public void testRequired() {
        Validator v = new Validator();
        var schema = v.string().required();

        assertTrue(schema.isValid("hexlet"));
        assertTrue(schema.isValid("what does the fox say"));
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
    }

    @Test
    public void testMinLength() {
        Validator v = new Validator();
        var schema = v.string().minLength(5);

        assertTrue(schema.isValid("World!"));
        assertTrue(schema.isValid("hello"));
        assertFalse(schema.isValid("test"));
        assertFalse(schema.isValid(""));
    }

    @Test
    public void testContains() {
        Validator v = new Validator();
        StringSchema schema = v.string().contains("wh");

        assertTrue(schema.isValid("what does the fox say"));
        assertFalse(schema.isValid("does the fox say"));
        schema.contains("does");
        assertFalse(schema.isValid("what the fox say"));
        assertTrue(schema.isValid("what does the fox say"));
    }

    @Test
    public void testConfiguration() {
        var v = new Validator();
        var schema = v.string().required().minLength(5).contains("hex");

        assertTrue(schema.isValid("hexlet"));
        assertTrue(schema.isValid("codehex"));
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid("test"));
        assertFalse(schema.isValid("hello"));
    }
}
