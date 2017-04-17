package vn.tranthanhtu.sunshine.activitis;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vn.tranthanhtu.sunshine.R;
import vn.tranthanhtu.sunshine.managers.RealmHandle;
import vn.tranthanhtu.sunshine.models.APImodels.WeatherCity;
import vn.tranthanhtu.sunshine.models.APImodels.WeatherCityCurrent;
import vn.tranthanhtu.sunshine.networks.NetworkManager;
import vn.tranthanhtu.sunshine.services.APIOpenWeather;
import vn.tranthanhtu.sunshine.services.APIOpenWeatherCurrentDay;
import vn.tranthanhtu.sunshine.services.NotificationService;

public class WaitActivity extends AppCompatActivity {
    private String q;

    private static final String TAG = WaitActivity.class.toString();
    private SharedPreferences preferences;
    private static final String BASE_URL = "http://api.openweathermap.org";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: Start");
        setContentView(R.layout.activity_wait);


        if (NetworkManager.getInstance().isConnectedToInternet()) {
            Log.d(TAG, "onCreate: isConnectedToInternet = true");
//            EventBus.getDefault().register(this);
            asyncTaskCurrentDay.execute(BASE_URL, null, null);

        } else {
            WeatherCity weatherCity = RealmHandle.getInstances().getWeatherCity();
            WeatherCityCurrent weatherCityCurrent = RealmHandle.getInstances().getWeatherCityCurrent();

            Log.d(TAG, "onCreate: isConnectedToInternet = false");
            if (weatherCity != null && weatherCityCurrent != null) {
                Log.d(TAG, "onCreate: isConnectedToInternet = false, data != null");
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(this, "No Internet Access!", Toast.LENGTH_SHORT).show();
            } else {
                Log.d(TAG, "onCreate: isConnectedToInternet = false, data = null");
                Toast.makeText(this, "No data!!!", Toast.LENGTH_SHORT).show();
            }

        }
        Log.d(TAG, "onCreate: End");
    }



    private void getDataCurrentDay(String url) {
        Log.d(TAG, "onHandleIntent: Start");
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIOpenWeatherCurrentDay service = retrofit.create(APIOpenWeatherCurrentDay.class);

        String appid = "65e7b2016fd75ffdc5602d1e714f82c5";
        if (preferences.getString("location", "location").equals("location")) {
            q = "hanoi";
        } else {
            q = preferences.getString("location", "location");
        }
        Log.d(TAG, String.format("getDataCurrentDay: %s", q));
        Log.d(TAG, "onHandleIntent: End");
//        service.getWeatherCityCurrent(q, appid).enqueue(new Callback<WeatherCityCurrent>() {
//            @Override
//            public void onResponse(Response<WeatherCityCurrent> response) {
//                Log.d(TAG, "onResponse: Start");
//                WeatherCityCurrent current = response.body();
//                RealmHandle.getInstances().addWeatherCityCurrent(current);
//                Log.d(TAG, "onResponse: Currrent ");
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                Log.d(TAG, "onFailure: Start");
//            }
//        });

        service.getWeatherCityCurrent(q, appid).enqueue(new Callback<WeatherCityCurrent>() {
            @Override
            public void onResponse(Call<WeatherCityCurrent> call, Response<WeatherCityCurrent> response) {
                Log.d(TAG, "onResponse: Start");
                WeatherCityCurrent current = response.body();
                RealmHandle.getInstances().addWeatherCityCurrent(current);
                Log.d(TAG, "onResponse: Currrent ");
            }

            @Override
            public void onFailure(Call<WeatherCityCurrent> call, Throwable t) {
                Log.d(TAG, "onFailure: Start");
            }
        });

    }

    private void getDataNextDay(String url) {
        Log.d(TAG, "onHandleIntent: Start");
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIOpenWeather service = retrofit.create(APIOpenWeather.class);

        String units;
        if (preferences.getString("location", "location").equals("location")
                && preferences.getString("units", "units").equals("units")) {
            q = "hanoi";
            units = "metric";
        } else {
            q = preferences.getString("location", "location");
            units = preferences.getString("units", "units");
        }
        Log.d(TAG, "onHandleIntent: " + q);
        Log.d(TAG, "onHandleIntent: " + units);
        String cnt = "15";
        String appid = "65e7b2016fd75ffdc5602d1e714f82c5";
        Log.d(TAG, "onHandleIntent: End");
//        service.getWeatherCity(q, units, cnt, appid).enqueue(new Callback<WeatherCity>() {
//            @Override
//            public void onResponse(Response<WeatherCity> response) {
//                Log.d(TAG, "onResponse: Start");
//                WeatherCity weatherCity = response.body();
//                RealmHandle.getInstances().addWeatherCity(weatherCity);
//                Log.d(TAG, "onResponse: nextday");
//                Log.d(TAG, "onResponse: End");
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                Log.d(TAG, "onFailure: Start");
//            }
//        });
        service.getWeatherCity(q, units, cnt, appid).enqueue(new Callback<WeatherCity>() {
            @Override
            public void onResponse(Call<WeatherCity> call, Response<WeatherCity> response) {
                Log.d(TAG, "onResponse: Start");
                WeatherCity weatherCity = response.body();
                RealmHandle.getInstances().addWeatherCity(weatherCity);
                Log.d(TAG, "onResponse: nextday");
                Log.d(TAG, "onResponse: End");
            }

            @Override
            public void onFailure(Call<WeatherCity> call, Throwable t) {
                Log.d(TAG, "onFailure: Start");
            }
        });

    }

    private final AsyncTask<String, Void, Void> asyncTaskLoadNextDay =
            new AsyncTask<String, Void, Void>() {
                @Override
                protected Void doInBackground(String... params) {
                    getDataNextDay(params[0]);
                    return null;
                }
            };


    private final AsyncTask<String, String, String> asyncTaskCurrentDay =
            new AsyncTask<String, String, String>() {


                @Override
                protected String doInBackground(String... params) {
                    getDataCurrentDay(params[0]);
                    Log.d(TAG, "doInBackground: finish getCurrent");
                    return null;
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    asyncTaskLoadNextDay.execute(BASE_URL, null, null);

                    Intent intent = new Intent(WaitActivity.this, MainActivity.class);
                    startActivity(intent);
                    if (preferences.getBoolean("enable_notifications", false)) {
                        startService(new Intent(getApplicationContext(), NotificationService.class));
                    } else {
                        Log.d(TAG, "Notification not able!!!");
                        NotificationManager notificationManager = (NotificationManager) getApplicationContext()
                                .getSystemService(Context.NOTIFICATION_SERVICE);
                        notificationManager.cancelAll();
                    }
                }
            };

}
