package me.dmillerw.jsonlogic.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * @author dmillerw
 */
public class ObjectBuilder {

    public static ObjectBuilder with(String key, boolean value) {
        return new ObjectBuilder().and(key, value);
    }

    public static ObjectBuilder with(String key, Number value) {
        return new ObjectBuilder().and(key, value);
    }

    public static ObjectBuilder with(String key, String value) {
        return new ObjectBuilder().and(key, value);
    }

    public static ObjectBuilder with(String key, JsonElement value) {
        return new ObjectBuilder().and(key, value);
    }

    private JsonObject object;

    private ObjectBuilder() {
        this.object = new JsonObject();
    }

    public ObjectBuilder and(String key, boolean value) {
        this.object.addProperty(key, value);
        return this;
    }

    public ObjectBuilder and(String key, Number value) {
        this.object.addProperty(key, value);
        return this;
    }

    public ObjectBuilder and(String key, String value) {
        this.object.addProperty(key, value);
        return this;
    }

    public ObjectBuilder and(String key, JsonElement value) {
        this.object.add(key, value);
        return this;
    }

    public JsonObject get() {
        return this.object;
    }
}
