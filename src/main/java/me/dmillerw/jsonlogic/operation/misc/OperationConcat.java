package me.dmillerw.jsonlogic.operation.misc;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import me.dmillerw.jsonlogic.operation.base.VarArgOperation;

import java.util.Arrays;

/**
 * @author dmillerw
 */
public class OperationConcat extends VarArgOperation {

    @Override
    public JsonElement apply(JsonElement data, JsonElement... args) {
        StringBuilder builder = new StringBuilder();
        Arrays.stream(args).forEach((l) -> builder.append(l.getAsString()));
        return new JsonPrimitive(builder.toString());
    }
}
