package me.dmillerw.jsonlogic.operation.math;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import me.dmillerw.jsonlogic.operation.base.TwoArgOperation;

/**
 * @author dmillerw
 */
public class OperationDivide extends TwoArgOperation {

    @Override
    public JsonElement apply(JsonElement data, JsonElement arg1, JsonElement arg2) {
        if (arg2.isJsonNull()) {
            return arg1;
        } else {
            return new JsonPrimitive(arg1.getAsDouble() / arg2.getAsDouble());
        }
    }
}
