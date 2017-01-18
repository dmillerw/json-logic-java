package me.dmillerw.jsonlogic.tests;

import com.google.gson.*;
import me.dmillerw.jsonlogic.JsonLogic;
import me.dmillerw.jsonlogic.exception.MissingOperationException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author dmillerw
 */
public class CommunityTests {

    //TODO: Figure out JUnit
    public static void main(String[] args) throws Exception {
        final URL tests = new URL("http://jsonlogic.com/tests.json");
        URLConnection connection = tests.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        StringBuilder builder = new StringBuilder();

        String line;
        while ((line = in.readLine()) != null) {
            builder.append(line);
        }
        in.close();

        final Gson gson = new GsonBuilder().create();
        JsonArray testArray = gson.fromJson(builder.toString(), JsonArray.class);

        for (JsonElement element : testArray) {
            if (element.isJsonPrimitive() && element.getAsJsonPrimitive().isString()) {
                continue;
            }

            JsonArray array = element.getAsJsonArray();

            JsonElement test = array.get(0);
            JsonElement data = array.get(1);
            JsonElement expected = array.get(2);

            try {
                JsonLogic.apply(test, data).equals(expected);
            } catch (Exception ex) {
                if (ex instanceof MissingOperationException)
                    continue;

                ex.printStackTrace();
                System.out.println(test);
                System.out.println(data);
                break;
            }
        }
    }
}
