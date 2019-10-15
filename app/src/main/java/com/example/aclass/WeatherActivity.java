package com.example.aclass;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.aclass.gson.Forecast;
import com.example.aclass.gson.Weather;
import com.example.aclass.util.Http;

import java.io.IOException;

public class WeatherActivity  extends AppCompatActivity {

    private ScrollView weatherLayout;
    private TextView titleCity;
    private TextView titleUpadateTime;
    private TextView degreeText;
    private TextView weatherInfoText;
    private LinearLayout forecastLayout;
    private TextView aqiText;
    private TextView pm25Text;
    private TextView comfortText;
    private TextView carWashText;
    private TextView sportText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        weatherLayout=(ScrollView)findViewById(R.id.weather_layout);
        titleCity=(TextView)findViewById(R.id.title_city);
        titleUpadateTime=(TextView)findViewById(R.id.title_upadate_time);
        degreeText=(TextView)findViewById(R.id.degree_text);
        weatherInfoText=(TextView)findViewById(R.id.info_text);
        forecastLayout=(LinearLayout)findViewById(R.id.forecast_layout);
        aqiText=(TextView)findViewById(R.id.aqi_tetx);
        pm25Text=(TextView)findViewById(R.id.pm2_5_text);
        comfortText=(TextView)findViewById(R.id.comfort_text);
        carWashText=(TextView)findViewById(R.id.wash_text);
        sportText=(TextView)findViewById(R.id.sport_text);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString =prefs.getString("weather",null);
        if (weatherString!=null){
            Weather weather = Utility.handleWeatherResponse(weatherString);
            showWeatherInfo(weather);
        }else {
            String weatherId =getIntent().getStringExtra("weather_id");
            weatherLayout.setVisibility(View.INVISIBLE);
//            requestWeather(weatherId);
        }


    }

//    private void requestWeather(final String weatherId) {
//        String weatherUrl= "http//guolin.tech/api/weather?cityid="+weatherId+"&key=dbb8029a4d6d4cd3a1187b8a31b53de4";
//        Http.sendOkHttpRequest(weatherUrl,new Window.Callback()){
//            @Override
//            public  void onResponse(Call call,Response response) throws IOException {
//                final String responseText =response.body().string();
//
//            }
//        }
//    }

    private class LinerLayout {
    }


    private void    showWeatherInfo(Weather weather){
        String cityName= weather.basic.cityName;
//        String updateTime= weather.basic.upadate.updateTime.split()[1];
        String degree =weather.now.temperature+"℃";
        String weatherInfo= weather.now.more.info;
        titleCity.setText(cityName);
//
        degreeText.setText(degree);
        weatherInfoText.setText(weatherInfo);
        forecastLayout.removeAllViews();
        for (Forecast forecast : weather.forecastsList){
            View view= LayoutInflater.from(this).inflate(R.layout.forecast_item,forecastLayout,false);
            TextView dateText=(TextView) view.findViewById(R.id.data_text);
            TextView infoText=(TextView)view.findViewById(R.id.info_text);
            TextView max=(TextView)view.findViewById(R.id.max_text);
            TextView min=(TextView)view.findViewById(R.id.min_text);
            dateText.setText(forecast.date);
            infoText.setText(forecast.more.info);
            max.setText(forecast.temperature.max);
            min.setText(forecast.temperature.min);
            forecastLayout.addView(view);
        }
        if (weather.aqi !=null){
            aqiText.setText(weather.aqi.city.aqi);
            pm25Text.setText(weather.aqi.city.pm25);
        }
        String comfortable="舒适度"+weather.suggestion.comfort.info;
        String sport ="运动建议"+weather.suggestion.sport.info;

        comfortText.setText(comfortable);
        sportText.setText(sport);
        weatherLayout.setVisibility(View.VISIBLE);

    }
}
