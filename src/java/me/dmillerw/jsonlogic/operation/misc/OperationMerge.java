package me.dmillerw.jsonlogic.operation.misc;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.dmillerw.jsonlogic.operation.base.VarArgOperation;

/**
 * @author dmillerw
 */
public class OperationMerge extends VarArgOperation {

    @Override
    public JsonElement apply(JsonObject data, JsonElement... args) {
        JsonArray array = new JsonArray();
        for (JsonElement e : args) {
            if (e.isJsonArray()) {
                e.getAsJsonArray().forEach(array::add);
            } else {
                array.add(e);
            }
        }

        return array;
    }
}
