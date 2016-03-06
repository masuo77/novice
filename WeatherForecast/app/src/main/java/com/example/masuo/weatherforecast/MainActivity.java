package com.example.masuo.weatherforecast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
//import java.util.logging.Handler;
//import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {

    //    private Handler handler = new Handler();
    //    private TextView result;
    private TextView location;
    private LinearLayout forecastLayout;

    public class ApiTask extends GetWeatherForecastTask { // GetWeatherForecastTaskを継承する
        @Override
//        protected void onPostExecute(String data) {
        protected void onPostExecute(WeatherForecast data) {
            // onPostExecuteで画面へ表示する。
            // メインスレッドで表示される。
            super.onPostExecute(data);
            if (data != null) {
//                result.setText(data);

//                result.setText(data.location.area + " "
//                        + data.location.prefecture + " "
//                        + data.location.city);

                location.setText(data.location.area + " "
                        + data.location.prefecture + " "
                        + data.location.city);

                for (WeatherForecast.Forecast forecast : data.forecastList) {
//                    result.append("\n");
//                    result.append(forecast.dateLabel + " " + forecast.telop);

                    View row = View.inflate(MainActivity.this, R.layout.forecast_row, null);

                    TextView date = (TextView) row.findViewById(R.id.tv_date);
                    date.setText(forecast.dateLabel);

                    TextView telop = (TextView) row.findViewById(R.id.tv_telop);
                    telop.setText(forecast.telop);

                    TextView temperature = (TextView) row.findViewById(R.id.tv_temperature);
                    temperature.setText(forecast.temperature.toString());

                    ImageView image = (ImageView) row.findViewById(R.id.iv_weather);

                    ImageLoaderTask task = new ImageLoaderTask();

                    task.execute(new ImageLoaderTask.Request(image, forecast.image.url));

                    forecastLayout.addView(row);

                }

            } else if (exception != null) {
                Toast.makeText(getApplicationContext(), "IOException is occurred.",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        result = (TextView) findViewById(R.id.tv_result);

        location = (TextView) findViewById(R.id.tv_location);
        forecastLayout = (LinearLayout) findViewById(R.id.ll_forecasts);


        // 都市IDを指定する。
        new ApiTask().execute("200010");

//        Thread subThread=new Thread(){
//            @Override
//            public void run(){
//                try{
//                    final String data = WeatherApi.getWeather("400040");
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            result.setText(data);
//                        }
//                    });
//                }catch(IOException e) {
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(getApplicationContext(), "IOException is occurred.",
//                                    Toast.LENGTH_SHORT).show();
//
//                        }
//                    });
//                }
//            }
//
//        };
//        subThread.start();

    }
}
