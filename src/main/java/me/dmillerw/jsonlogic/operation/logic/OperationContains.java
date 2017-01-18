package me.dmillerw.jsonlogic.operation.logic;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import me.dmillerw.jsonlogic.operation.base.TwoArgOperation;

/**
 * @author dmillerw
 */
public class OperationContains extends TwoArgOperation {

    @Override
    public JsonElement apply(JsonElement data, JsonElement arg1, JsonElement arg2) {
        if (arg2.isJsonArray()) {
            return new JsonPrimitive(arg2.getAsJsonArray().contains(arg1));
        } else if (arg2.isJsonPrimitive()) {
            return new JsonPrimitive(arg2.getAsString().contains(arg1.getAsString()));
        } else {
            return new JsonPrimitive(false);
        }
    }
}
