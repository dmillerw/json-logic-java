package me.dmillerw.jsonlogic.operation.misc;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import me.dmillerw.jsonlogic.operation.base.TwoArgOperation;

/**
 * @author dmillerw
 */
public class OperationVar extends TwoArgOperation {

    @Override
    public JsonElement apply(JsonObject data, JsonElement arg1, JsonElement arg2) {
        String key = arg1.getAsString();

        String[] properties = key.split("\\.");

        for (int i=0; i<properties.length; i++) {
            JsonElement e = data.get(properties[i]);
            if (e == null) {
                return arg2;
            } else if (e.isJsonObject()) {
                data = e.getAsJsonObject();
            } else {
                return e;
            }
        }

        return data;
    }
}
