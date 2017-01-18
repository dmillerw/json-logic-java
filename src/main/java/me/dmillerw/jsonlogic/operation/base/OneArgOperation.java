package me.dmillerw.jsonlogic.operation.base;

import com.google.gson.JsonElement;

/**
 * @author dmillerw
 */
public abstract class OneArgOperation extends VarArgOperation {

    @Override
    public JsonElement apply(JsonElement data, JsonElement... args) {
        return apply(data, safeGet(args, 0));
    }

    public abstract JsonElement apply(JsonElement data, JsonElement arg1);
}
