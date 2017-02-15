package vn.tranthanhtu.sunshine.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import vn.tranthanhtu.sunshine.eventbus.LoadDataNextDaySuccessEvent;
import vn.tranthanhtu.sunshine.managers.RealmHandle;
import vn.tranthanhtu.sunshine.models.APImodels.WeatherCity;

import static android.content.ContentValues.TAG;

/**
 * Created by Dell latitude E6520 on 2/7/2017.
 */

public class LoadDataNextDayWeather extends IntentService {
    SharedPreferences preferences;
    String q;
    String units;

    public LoadDataNextDayWeather() {
        super("LoadDataNextDayWeather");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIOpenWeather service = retrofit.create(APIOpenWeather.class);

        if (preferences.getString("location", "location").equals("location")
                && preferences.getString("units", "units").equals("units")){
            q = "hanoi";
            units = "metric";

        }else {
            q = preferences.getString("location", "location");
            units = preferences.getString("units", "units");
        }
        Log.d(TAG, "onHandleIntent: " + q);
        Log.d(TAG, "onHandleIntent: " + units);
        String cnt = "15";
        String appid = "66f5fe4f80f450d73ad7f7cd100f95b6";

        service.getWeatherCity(q, units, cnt, appid).enqueue(new Callback<WeatherCity>() {
            @Override
            public void onResponse(Response<WeatherCity> response) {
                WeatherCity weatherCity = response.body();
                RealmHandle.getInstances().addWeatherCity(weatherCity);
                EventBus.getDefault().post(new LoadDataNextDaySuccessEvent(weatherCity));
                Log.d(TAG, "onResponse: nextday");
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
