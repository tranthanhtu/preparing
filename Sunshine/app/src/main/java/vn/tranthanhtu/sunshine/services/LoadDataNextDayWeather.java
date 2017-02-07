package vn.tranthanhtu.sunshine.services;

import android.app.IntentService;
import android.content.Intent;
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

    public LoadDataNextDayWeather() {
        super("LoadDataNextDayWeather");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIOpenWeather service = retrofit.create(APIOpenWeather.class);
        String q = "hanoi";
        String units = "metric";
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
