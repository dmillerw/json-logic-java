package me.dmillerw.jsonlogic.operation.logic;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import me.dmillerw.jsonlogic.JsonLogic;
import me.dmillerw.jsonlogic.operation.base.OneArgOperation;

/**
 * @author dmillerw
 */
public class OperationTruthy extends OneArgOperation {

    @Override
    public JsonElement apply(JsonElement data, JsonElement arg1) {
        return new JsonPrimitive(JsonLogic.isTruthy(arg1));
    }
}
