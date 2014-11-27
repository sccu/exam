/**
 * Created by sccu on 14. 11. 27..
 */
package pe.name.sccu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JsonNullTest {

    @Test
    public void testJsonNull() {
        Gson gson = new GsonBuilder().create();
        JsonObject jsonObject = gson.fromJson("{\"key\":null}", JsonObject.class);
        assertTrue(jsonObject.get("key").isJsonNull());
    }
}
