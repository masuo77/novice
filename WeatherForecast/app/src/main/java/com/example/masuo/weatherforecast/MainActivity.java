package com.example.masuo.weatherforecast;

// android.app.FragmentManager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
//import java.util.logging.Handler;
//import java.util.logging.LogRecord;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String[] CITY_LIST =
            {
                    "270000", "130010", "040010","400040", "200010"
            };

    private List<String> pointList;

    private class Adapter extends FragmentPagerAdapter {

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ForecastFragment.newInstance(pointList.get(position));
        }

        @Override
        public int getCount() {
            return pointList.size();
        }
    }

    private Adapter adapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (pointList == null) {
            pointList = Arrays.asList(CITY_LIST);
        }

        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        adapter = new Adapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

    }


//    //    private Handler handler = new Handler();
//    //    private TextView result;
//    private TextView location;
//    private LinearLayout forecastLayout;
//    private ProgressBar progress;
//
//    public class ApiTask extends GetWeatherForecastTask { // GetWeatherForecastTaskを継承する
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            progress.setVisibility(View.VISIBLE);
//        }
//
//
//        @Override
////        protected void onPostExecute(String data) {
//        protected void onPostExecute(WeatherForecast data) {
//            // onPostExecuteで画面へ表示する。
//            // メインスレッドで表示される。
//            super.onPostExecute(data);
//
//            progress.setVisibility(View.GONE);
//
//            if (data != null) {
////                result.setText(data);
//
////                result.setText(data.location.area + " "
////                        + data.location.prefecture + " "
////                        + data.location.city);
//
//                location.setText(data.location.area + " "
//                        + data.location.prefecture + " "
//                        + data.location.city);
//
//                for (WeatherForecast.Forecast forecast : data.forecastList) {
////                    result.append("\n");
////                    result.append(forecast.dateLabel + " " + forecast.telop);
//
//                    View row = View.inflate(MainActivity.this, R.layout.forecast_row, null);
//
//                    TextView date = (TextView) row.findViewById(R.id.tv_date);
//                    date.setText(forecast.dateLabel);
//
//                    TextView telop = (TextView) row.findViewById(R.id.tv_telop);
//                    telop.setText(forecast.telop);
//
//                    TextView temperature = (TextView) row.findViewById(R.id.tv_temperature);
//                    temperature.setText(forecast.temperature.toString());
//
//                    ImageView image = (ImageView) row.findViewById(R.id.iv_weather);
//
//                    ImageLoaderTask task = new ImageLoaderTask();
//
//                    task.execute(new ImageLoaderTask.Request(image, forecast.image.url));
//
//                    forecastLayout.addView(row);
//
//                }
//
//            } else if (exception != null) {
//                Toast.makeText(getApplicationContext(), "IOException is occurred.",
//                        Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_forcast);
//
////        result = (TextView) findViewById(R.id.tv_result);
//
//        location = (TextView) findViewById(R.id.tv_location);
//        forecastLayout = (LinearLayout) findViewById(R.id.ll_forecasts);
//
//        progress = (ProgressBar) findViewById(R.id.progress);
//
//        // 都市IDを指定する。
//        new ApiTask().execute("200010");
//
////        Thread subThread=new Thread(){
////            @Override
////            public void run(){
////                try{
////                    final String data = WeatherApi.getWeather("400040");
////                    handler.post(new Runnable() {
////                        @Override
////                        public void run() {
////                            result.setText(data);
////                        }
////                    });
////                }catch(IOException e) {
////                    handler.post(new Runnable() {
////                        @Override
////                        public void run() {
////                            Toast.makeText(getApplicationContext(), "IOException is occurred.",
////                                    Toast.LENGTH_SHORT).show();
////
////                        }
////                    });
////                }
////            }
////
////        };
////        subThread.start();


}
