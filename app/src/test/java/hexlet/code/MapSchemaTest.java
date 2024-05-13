package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import hexlet.code.Schemes.BaseSchema;
import hexlet.code.Schemes.MapSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class MapSchemaTest {
    @Test
    public void testRequired() {
        MapSchema<String> schema = new MapSchema<String>().required();

        assertTrue(schema.isValid(new HashMap<>()));

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data));

        assertFalse(schema.isValid(null));
    }

    @Test
    public void testSizeOf() {
        MapSchema<String> schema = new MapSchema<String>().sizeof(2);

        assertTrue(schema.isValid(null));

        Map<String, String> data1 = new HashMap<>();
        data1.put("key1", "value1");
        assertFalse(schema.isValid(data1));

        Map<String, String> data2 = new HashMap<>();
        data2.put("key1", "value1");
        data2.put("key2", "value2");
        assertTrue(schema.isValid(data2));

        Map<String, String> data3 = new HashMap<>();
        data3.put("key1", "value1");
        data3.put("key2", "value2");
        data3.put("key3", "value3");
        assertFalse(schema.isValid(data3));
    }

    @Test
    public void testShape() {
        Validator v = new Validator();
        MapSchema<String> schema = new MapSchema<>();

        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));

        schema.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(schema.isValid(human1));

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertFalse(schema.isValid(human2));

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertFalse(schema.isValid(human3));
    }


}
