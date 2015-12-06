package com.aubhree.element.element;

import org.json.JSONObject;
import org.json.JSONException;


public class WeatherParser{

    public static Weather getWeather(String data) throws JSONException {

        Weather curWeather = new Weather();

        JSONObject jsonObj = new JSONObject(data);

        //create temperature object
        JSONObject tmpObj = getObject("main", jsonObj);
        curWeather.setTemp(getFloat("temp", tmpObj));
        curWeather.setHumidity(getInt("humidity", tmpObj));

        return curWeather;

    }

    private static JSONObject getObject(String tagName, JSONObject jsonObj)  throws JSONException {
        JSONObject subObj = jsonObj.getJSONObject(tagName);
        return subObj;
    }

    private static float getFloat(String tagName, JSONObject jsonObj) throws JSONException {
        return (float) jsonObj.getDouble(tagName);
    }

    private static int getInt(String tagName, JSONObject jsonObj) throws JSONException {
        return jsonObj.getInt(tagName);
    }
}