package me.dmillerw.jsonlogic.operation.base;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;

/**
 * @author dmillerw
 */
public abstract class TwoArgOperation extends VarArgOperation {

    @Override
    public JsonElement apply(JsonObject data, JsonElement... args) {
        return apply(data, safeGet(args, 0), safeGet(args, 1));
    }

    public JsonElement apply(JsonObject data, JsonElement arg1) {
        return apply(data, arg1, JsonNull.INSTANCE);
    }

    public abstract JsonElement apply(JsonObject data, JsonElement arg1, JsonElement arg2);
}
