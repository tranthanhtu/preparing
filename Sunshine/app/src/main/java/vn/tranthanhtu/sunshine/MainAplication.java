package vn.tranthanhtu.sunshine;

import android.app.Application;
import android.content.Intent;

import vn.tranthanhtu.sunshine.managers.RealmHandle;
import vn.tranthanhtu.sunshine.networks.NetworkManager;
import vn.tranthanhtu.sunshine.services.LoadDataCurrentDayWeather;
import vn.tranthanhtu.sunshine.services.LoadDataNextDayWeather;
import vn.tranthanhtu.sunshine.services.LoadLocationCityGPS;

/**
 * Created by Dell latitude E6520 on 2/7/2017.
 */

public class MainAplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        NetworkManager.init(this);
        RealmHandle.init(this);
        Intent intent = new Intent(this, LoadDataCurrentDayWeather.class);
        startService(intent);
        Intent intent1 = new Intent(this, LoadDataNextDayWeather.class);
        startService(intent1);
        Intent intent2 = new Intent(this, LoadLocationCityGPS.class);
        startService(intent2);
    }
}
