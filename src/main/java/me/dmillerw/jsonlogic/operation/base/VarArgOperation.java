package me.dmillerw.jsonlogic.operation.base;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;

/**
 * @author dmillerw
 */
public abstract class VarArgOperation {

    public final JsonElement safeGet(JsonElement[] array, int index) {
        if (index >= array.length)
            return JsonNull.INSTANCE;
        else
            return array[index];
    }

    public abstract JsonElement apply(JsonElement data, JsonElement ... args);
}
