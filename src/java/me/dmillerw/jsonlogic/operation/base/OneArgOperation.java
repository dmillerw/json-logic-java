package me.dmillerw.jsonlogic.operation.base;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * @author dmillerw
 */
public abstract class OneArgOperation extends VarArgOperation {

    @Override
    public JsonElement apply(JsonObject data, JsonElement... args) {
        return apply(data, safeGet(args, 0));
    }

    public abstract JsonElement apply(JsonObject data, JsonElement arg1);
}
