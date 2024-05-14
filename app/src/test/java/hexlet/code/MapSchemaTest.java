package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class MapSchemaTest {
    @Test
    public void testRequired() {
        var v = new Validator();

        MapSchema<String> schema = v.map().required();

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

}
