package me.dmillerw.jsonlogic.operation.misc;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import me.dmillerw.jsonlogic.operation.base.OneArgOperation;

/**
 * @author dmillerw
 */
public class OperationLog extends OneArgOperation {

    @Override
    public JsonElement apply(JsonObject data, JsonElement arg1) {
        System.out.println(arg1.toString());
        return JsonNull.INSTANCE;
    }
}
