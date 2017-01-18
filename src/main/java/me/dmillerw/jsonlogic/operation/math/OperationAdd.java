package me.dmillerw.jsonlogic.operation.math;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import me.dmillerw.jsonlogic.operation.base.VarArgOperation;

/**
 * @author dmillerw
 */
public class OperationAdd extends VarArgOperation {

    @Override
    public JsonElement apply(JsonElement data, JsonElement... args) {
        double accumulator = 0;
        for (JsonElement element : args)
            accumulator += element.getAsDouble();

        return new JsonPrimitive(accumulator);
    }
}
