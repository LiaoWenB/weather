package com.example.aclass;

import com.example.aclass.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

public  class  Utility{

    public  static Weather handleWeatherResponse(String response){
    try{
        JSONObject jsonObject= new JSONObject(response);
        JSONArray jsonArray = jsonObject.getJSONArray("Heweather");
        String weatherContent = jsonArray.getJSONObject(0).toString();
        return new Gson().fromJson(weatherContent,Weather.class);

    } catch (Exception e)
    {
        e.printStackTrace();
    }
    return  null;
}

}
