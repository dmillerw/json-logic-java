package me.dmillerw.jsonlogic;

import com.google.gson.*;
import me.dmillerw.jsonlogic.operation.base.VarArgOperation;
import me.dmillerw.jsonlogic.operation.logic.OperationContains;
import me.dmillerw.jsonlogic.operation.logic.OperationNotTruthy;
import me.dmillerw.jsonlogic.operation.logic.OperationTruthy;
import me.dmillerw.jsonlogic.operation.math.*;
import me.dmillerw.jsonlogic.operation.misc.OperationConcat;
import me.dmillerw.jsonlogic.operation.misc.OperationLog;
import me.dmillerw.jsonlogic.operation.misc.OperationMerge;
import me.dmillerw.jsonlogic.operation.misc.OperationVar;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author dmillerw
 */
public class JsonLogic {

    private static Map<String, VarArgOperation> operations = new HashMap<>();

    private static void addOperation(String key, VarArgOperation operation) {
        operations.put(key, operation);
    }

    static {
        addOperation("==", new OperationEquals());
        addOperation("!=", new OperationNotEquals());
        addOperation(">", new OperationGreaterThan());
        addOperation(">=", new OperationGreaterThanOrEqual());
        addOperation("<", new OperationLessThan());
        addOperation("<=", new OperationLessThanOrEqual());
        addOperation("!!", new OperationTruthy());
        addOperation("!", new OperationNotTruthy());
        addOperation("%", new OperationModulus());
        addOperation("in", new OperationContains());
        addOperation("merge", new OperationMerge());
        addOperation("cat", new OperationConcat());
        addOperation("log", new OperationLog());
        addOperation("+", new OperationAdd());
        addOperation("-", new OperationSubtract());
        addOperation("*", new OperationMultiply());
        addOperation("/", new OperationDivide());
        addOperation("min", new OperationMin());
        addOperation("max", new OperationMax());
        addOperation("var", new OperationVar());
    }

    private static JsonElement callOperation(String operation, JsonObject data, JsonElement logic) {
        JsonElement[] array;
        if (logic.isJsonArray()) {
            array = new JsonElement[logic.getAsJsonArray().size()];
            for (int i = 0; i < array.length; i++)
                array[i] = logic.getAsJsonArray().get(i);
        } else {
            array = new JsonElement[1];
            array[0] = logic;
        }

        return operations.get(operation).apply(data, array);
    }

    private static JsonElement map(JsonArray array, Function<JsonElement, JsonElement> consumer) {
        JsonArray list = new JsonArray();
        for (int i = 0; i < array.size(); i++) {
            list.add(consumer.apply(array.get(i)));
        }
        return list;
    }

    public static boolean isLogic(JsonElement element) {
        return element != null && element.isJsonObject();
    }

    public static boolean isTruthy(JsonElement element) {
        if (element == null || element.isJsonNull())
            return false;

        if (element.isJsonPrimitive()) {
            JsonPrimitive primitive = element.getAsJsonPrimitive();
            if (primitive.isBoolean()) {
                return primitive.getAsBoolean();
            } else if (primitive.isNumber()) {
                return primitive.getAsNumber().intValue() != 0;
            } else if (primitive.isString()) {
                return !primitive.getAsString().isEmpty();
            }
        } else if (element.isJsonArray()) {
            return element.getAsJsonArray().size() > 0;
        } else if (element.isJsonObject()) {
            return element.getAsJsonObject().size() > 0;
        }

        return false;
    }

    public static JsonElement apply(JsonElement logic) {
        return apply(logic, null);
    }

    public static JsonElement apply(JsonElement logic, JsonObject data) {
        if (logic.isJsonArray()) {
            JsonObject finalData = data;
            map(logic.getAsJsonArray(), (l) -> JsonLogic.apply(l, finalData));
        }

        if (!isLogic(logic)) {
            return logic;
        }

        if (data == null) data = new JsonObject();

        // Good lord...
        String operation = logic.getAsJsonObject().entrySet().iterator().next().getKey();
        JsonElement values = logic.getAsJsonObject().get(operation);

        JsonArray valueArray;
        if (values.isJsonArray()) {
            valueArray = values.getAsJsonArray();
        } else {
            valueArray = new JsonArray();
            valueArray.add(values);
        }

        int i;
        JsonElement current = JsonNull.INSTANCE;

        if (operation.equalsIgnoreCase("if") || operation.equalsIgnoreCase("?:")) {
            for (i = 0; i < valueArray.size() - 1; i += 2) {
                if (JsonLogic.isTruthy(JsonLogic.apply(valueArray.get(i), data))) {
                    return JsonLogic.apply(valueArray.get(i + 1), data);
                }
            }

            if (valueArray.size() == i + 1)
                return JsonLogic.apply(valueArray.get(i), data);

            return null;
        } else if (operation.equalsIgnoreCase("and")) {
            for (i = 0; i < valueArray.size(); i += 1) {
                current = JsonLogic.apply(valueArray.get(i), data);
                if (!JsonLogic.isTruthy(current)) {
                    return current;
                }
            }
            return current;
        } else if (operation.equalsIgnoreCase("or")) {
            for (i = 0; i < valueArray.size(); i += 1) {
                current = JsonLogic.apply(valueArray.get(i), data);
                if (JsonLogic.isTruthy(current)) {
                    return current;
                }
            }
            return current;
        }

        JsonObject finalData1 = data;
        valueArray = map(valueArray, (l) -> JsonLogic.apply(l, finalData1)).getAsJsonArray();

        if (valueArray.size() <= 0)
            throw new RuntimeException("Value array cannot be zero! TODO, log stuff, somehow, useful debug shit");

        if (operations.containsKey(operation)) {
            return callOperation(operation, data, valueArray);
        } else {
            throw new RuntimeException("Unknown operation: " + operation);
        }
    }
}
