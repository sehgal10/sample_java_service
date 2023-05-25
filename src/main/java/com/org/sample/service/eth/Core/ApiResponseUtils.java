package com.org.sample.service.eth.Core;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ApiResponseUtils {

    /**
     * Builds successful Json response
     * @param data JsonObject data that should be sent
     * @return Successful Json response
     */
    public static JsonObject success(JsonObject data) {
        JsonObject response = new JsonObject();
        response.addProperty("status", true);
        response.add("data", data);
        response.addProperty("error", "");
        return response;
    }

    /**
     * Builds successful Json response
     * @param data JsonObject data that should be sent
     * @return Successful Json response
     */
    public static JsonObject success(JsonArray data) {
        JsonObject response = new JsonObject();
        response.addProperty("status", true);
        response.add("data", data);
        response.addProperty("error", "");
        return response;
    }

    /**
     * Builds error Json response
     * @param msg Error message
     * @return Error Json response
     */
    public static JsonObject error(String msg) {
        JsonObject response = new JsonObject();
        response.addProperty("status", false);
        response.add("data", new JsonObject());
        response.addProperty("error", msg);
        return response;
    }
}
