package vn.tranthanhtu.sunshine.activitis;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import vn.tranthanhtu.sunshine.R;
import vn.tranthanhtu.sunshine.eventbus.BaseEvent;
import vn.tranthanhtu.sunshine.eventbus.CloseAppEvent;
import vn.tranthanhtu.sunshine.eventbus.LoadDataCurrentDaySuccessEvent;
import vn.tranthanhtu.sunshine.eventbus.LoadDataNextDaySuccessEvent;
import vn.tranthanhtu.sunshine.managers.RealmHandle;
import vn.tranthanhtu.sunshine.models.APImodels.WeatherCity;
import vn.tranthanhtu.sunshine.models.APImodels.WeatherCityCurrent;
import vn.tranthanhtu.sunshine.networks.NetworkManager;

public class WaitActivity extends AppCompatActivity {

    public static final String TAG = WaitActivity.class.toString();
    SharedPreferences preferences;
    WeatherCity weatherCity;
    WeatherCityCurrent weatherCityCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        weatherCity = RealmHandle.getInstances().getWeatherCity();
        weatherCityCurrent = RealmHandle.getInstances().getWeatherCityCurrent();

        if (NetworkManager.getInstance().isConnectedToInternet()){
            EventBus.getDefault().register(this);

        }else {
            if (weatherCity != null && weatherCityCurrent !=null){
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(this, "No Internet Access!", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "No data!!!", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Subscribe
    void onDataCurrent(BaseEvent baseEvent){
        if (baseEvent instanceof LoadDataCurrentDaySuccessEvent){
            Intent intent = new Intent(this, LoadDataNextDaySuccessEvent.class);
            startService(intent);

        }else {

        }
    }

    @Subscribe
    void onDataNextDay(BaseEvent baseEvent) {
        if (baseEvent instanceof LoadDataNextDaySuccessEvent) {
            Intent intent = new Intent(WaitActivity.this, MainActivity.class);
            startActivity(intent);
//            Log.d(TAG, "onDataNextDay: " + preferences.getBoolean("enable_notifications", false));;
//            if (preferences.getBoolean("enable_notifications", false) == true){
//                startService(new Intent(this, NotificationService.class));
//            }
//            else {
//                Log.d(TAG, "Notification not able!!!");
//            }
        }else {

        }
    }

    @Subscribe
    void closeApp(BaseEvent baseEvent){
        if (baseEvent instanceof CloseAppEvent){
            Log.d(TAG, "closeApp: ");
            onBackPressed();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
