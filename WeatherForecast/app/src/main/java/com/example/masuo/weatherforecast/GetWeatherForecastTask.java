package com.example.masuo.weatherforecast;

import android.os.AsyncTask;

import org.json.JSONException;

import java.io.IOException;

/**
 * Created by Masuo on 2016/03/05.
 */
//public class GetWeatherForecastTask extends AsyncTask<String, Void, String> { /* AsyncTaskクラスを継承する */
public class GetWeatherForecastTask extends AsyncTask<String, Void, WeatherForecast> { /* AsyncTaskクラスを継承する */

    Exception exception;

    @Override
//    protected String doInBackground(String... params) {
    protected WeatherForecast doInBackground(String... params) {
        try {
            /* doInBackgroundの中でWeatherApi.getWeatherを実行して結果を返す*/
            return WeatherApi.getWeather(params[0]);
        } catch (IOException | JSONException e) {
            exception = e;
        }
        return null;
    }
}
