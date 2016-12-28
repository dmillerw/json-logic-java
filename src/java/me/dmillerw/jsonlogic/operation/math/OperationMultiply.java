package me.dmillerw.jsonlogic.operation.math;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import me.dmillerw.jsonlogic.operation.base.VarArgOperation;

/**
 * @author dmillerw
 */
public class OperationMultiply extends VarArgOperation {

    @Override
    public JsonElement apply(JsonObject data, JsonElement... args) {
        double accumulator = args[0].getAsDouble();
        for (int i = 1; i< args.length; i++)
            accumulator *= args[i].getAsDouble();

        return new JsonPrimitive(accumulator);
    }
}
