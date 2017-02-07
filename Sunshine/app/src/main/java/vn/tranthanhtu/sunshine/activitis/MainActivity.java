package vn.tranthanhtu.sunshine.activitis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.SimpleDateFormat;
import java.util.Date;

import vn.tranthanhtu.sunshine.R;
import vn.tranthanhtu.sunshine.adapters.NextDayWeatherAdapter;
import vn.tranthanhtu.sunshine.eventbus.BaseEvent;
import vn.tranthanhtu.sunshine.eventbus.LoadDataCurrentDaySuccessEvent;
import vn.tranthanhtu.sunshine.eventbus.LoadDataNextDaySuccessEvent;
import vn.tranthanhtu.sunshine.managers.RealmHandle;
import vn.tranthanhtu.sunshine.models.APImodels.WeatherCity;
import vn.tranthanhtu.sunshine.models.APImodels.WeatherCityCurrent;
import vn.tranthanhtu.sunshine.models.APImodels.modelNextDay.List;
import vn.tranthanhtu.sunshine.models.NextDayModel;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.toString();
    private ImageView imvIconWeatherCurrent;
    private TextView tvDateCurrent;
    private TextView tvDescriptionCurrent;
    private TextView tvTemperatureMaxCurrent;
    private TextView tvTemperatureMinCurrent;

    private int tempMaxCurrent;
    private int tempMinCurrent;

    WeatherCity weatherCity;
    WeatherCityCurrent current;
    RecyclerView rvNextDay;
    private NextDayWeatherAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        Log.d(TAG, "onCreate: ");
//        weatherCity = RealmHandle.getInstances().getWeatherCity();
//        current = RealmHandle.getInstances().getWeatherCityCurrent();
//        Log.d(TAG, String.format("onCreate: %s", weatherCity.getList().get(0).getDt()));
        setReferences();
        getTimeCurrent();
        setAdapter();
//
//        loadDatatoAdapter();
//        setupUI();
//        getDataFromAPICurrentDay();
//        getDataFromAPINextDay();

    }

    private void setReferences() {
        rvNextDay = (RecyclerView) findViewById(R.id.rv_next_day);
        imvIconWeatherCurrent = (ImageView) findViewById(R.id.imv_icon_weather_current);
        tvDateCurrent = (TextView) findViewById(R.id.tv_date_current);
        tvDescriptionCurrent = (TextView) findViewById(R.id.tv_description_current);
        tvTemperatureMaxCurrent = (TextView) findViewById(R.id.tv_temperature_max_current);
        tvTemperatureMinCurrent = (TextView) findViewById(R.id.tv_temperature_min_current);

    }


    @Subscribe
    void onDataNextDay(BaseEvent baseEvent) {
        if (baseEvent instanceof LoadDataNextDaySuccessEvent) {
            weatherCity = RealmHandle.getInstances().getWeatherCity();
            Log.d(TAG, String.format("onCreate: %s", weatherCity.getList().get(0).getDt()));
            loadDatatoAdapter();
        }else {

        }
    }

    @Subscribe
    void onDataCurrent(BaseEvent baseEvent){
        if (baseEvent instanceof LoadDataCurrentDaySuccessEvent){
            current = RealmHandle.getInstances().getWeatherCityCurrent();
            setupUI();
        }else {

        }
    }

    private void setupUI() {
        Picasso
                .with(getApplicationContext())
                .load("http://openweathermap.org/img/w/"
                        + current.getWeather().get(0).getIcon()
                        + ".png")
                .into(imvIconWeatherCurrent);
        tempMaxCurrent = (int) Float.parseFloat(String.valueOf(current.getMain().getTemp_max())) - 273;
        tempMinCurrent = (int) Float.parseFloat(String.valueOf(current.getMain().getTemp_min())) - 273;
        tvTemperatureMinCurrent.setText(tempMinCurrent + "");
        tvTemperatureMaxCurrent.setText(tempMaxCurrent + "");
        tvDescriptionCurrent.setText(current.getWeather().get(0).getMain());
    }

    private void loadDatatoAdapter() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (List list : weatherCity.getList()) {
                    NextDayModel.list.add(new NextDayModel(
                            "http://openweathermap.org/img/w/"
                                    + list.getWeather().get(0).getIcon().toString()
                                    + ".png",
                            list.getWeather().get(0).getMain(),
                            list.getTemp().getMax(),
                            list.getTemp().getMin()));
                    adapter.notifyDataSetChanged();
                    Log.d(TAG, String.format("onResponse: %s", list.getWeather().get(0).getIcon()));
                }
            }
        });
        setAdapter();
    }

    private void getTimeCurrent() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d");
        String currentTime = dateFormat.format(new Date());
        tvDateCurrent.setText("Today, " + currentTime);
    }

    private void setAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvNextDay.setLayoutManager(layoutManager);
        rvNextDay.setHasFixedSize(true);
        adapter = new NextDayWeatherAdapter();
        rvNextDay.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }

}
