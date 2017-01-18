package me.dmillerw.jsonlogic.operation.math;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import me.dmillerw.jsonlogic.operation.base.VarArgOperation;

/**
 * @author dmillerw
 */
public class OperationMin extends VarArgOperation {

    @Override
    public JsonElement apply(JsonElement data, JsonElement... args) {
        double value = args[0].getAsDouble();
        for (int i = 1; i< args.length; i++)
            if (args[i].getAsDouble() < value) value = args[i].getAsDouble();

        return new JsonPrimitive(value);
    }
}
