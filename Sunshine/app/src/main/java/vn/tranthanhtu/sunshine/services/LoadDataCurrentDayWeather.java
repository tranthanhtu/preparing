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
import vn.tranthanhtu.sunshine.eventbus.LoadDataCurrentDaySuccessEvent;
import vn.tranthanhtu.sunshine.managers.RealmHandle;
import vn.tranthanhtu.sunshine.models.APImodels.WeatherCityCurrent;

import static android.content.ContentValues.TAG;

/**
 * Created by Dell latitude E6520 on 2/7/2017.
 */

public class LoadDataCurrentDayWeather extends IntentService {
    SharedPreferences preferences;
    String q;

    public LoadDataCurrentDayWeather() {
        super("LoadDataCurrentDayWeather");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIOpenWeatherCurrentDay service = retrofit.create(APIOpenWeatherCurrentDay.class);

        String appid = "66f5fe4f80f450d73ad7f7cd100f95b6";
        if (preferences.getString("location", "location").equals("location")){
            q = "hanoi";
        }else {
            q = preferences.getString("location", "location");
        }
//
//        q = "hanoi";

        service.getWeatherCityCurrent(q, appid).enqueue(new Callback<WeatherCityCurrent>() {
            @Override
            public void onResponse(Response<WeatherCityCurrent> response) {
                WeatherCityCurrent current = response.body();
                RealmHandle.getInstances().addWeatherCityCurrent(current);
                EventBus.getDefault().post(new LoadDataCurrentDaySuccessEvent(current));
                Log.d(TAG, "onResponse: Currrent ");
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
