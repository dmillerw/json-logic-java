package me.dmillerw.jsonlogic.operation.logic;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import me.dmillerw.jsonlogic.operation.base.TwoArgOperation;

/**
 * @author dmillerw
 */
public class OperationContains extends TwoArgOperation {

    @Override
    public JsonElement apply(JsonObject data, JsonElement arg1, JsonElement arg2) {
        if (arg1.isJsonArray()) {
            return new JsonPrimitive(arg1.getAsJsonArray().contains(arg2));
        } else if (arg1.isJsonPrimitive() && arg1.getAsJsonPrimitive().isString()) {
            return new JsonPrimitive(arg1.getAsString().contains(arg2.getAsString()));
        } else {
            return new JsonPrimitive(false);
        }
    }
}
