package me.dmillerw.jsonlogic.operation.misc;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import me.dmillerw.jsonlogic.operation.base.TwoArgOperation;

/**
 * @author dmillerw
 */
public class OperationVar extends TwoArgOperation {

    @Override
    public JsonElement apply(JsonElement data, JsonElement arg1, JsonElement arg2) {
        if (data.isJsonNull())
            return JsonNull.INSTANCE;

        if (data.isJsonArray()) {
            if (arg1.isJsonPrimitive() && arg1.getAsJsonPrimitive().isNumber()) {
                return data.getAsJsonArray().get(arg1.getAsJsonPrimitive().getAsInt());
            }
        } else {
            JsonObject object = data.getAsJsonObject();

            String key = arg1.getAsString();
            String[] properties = key.split("\\.");

            for (int i=0; i<properties.length; i++) {
                JsonElement e = object.get(properties[i]);
                if (e == null) {
                    return arg2;
                } else if (e.isJsonObject()) {
                    object = e.getAsJsonObject();
                } else {
                    return e;
                }
            }
        }

        return JsonNull.INSTANCE;
    }
}
