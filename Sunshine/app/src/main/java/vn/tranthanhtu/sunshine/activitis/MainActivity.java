package vn.tranthanhtu.sunshine.activitis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import vn.tranthanhtu.sunshine.R;
import vn.tranthanhtu.sunshine.adapters.NextDayWeatherAdapter;
import vn.tranthanhtu.sunshine.models.APImodels.WeatherCity;
import vn.tranthanhtu.sunshine.models.APImodels.WeatherCityCurrent;
import vn.tranthanhtu.sunshine.models.NextDayModel;
import vn.tranthanhtu.sunshine.services.APIOpenWeather;
import vn.tranthanhtu.sunshine.services.APIOpenWeatherCurrentDay;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.toString();
    private ImageView imvIconWeatherCurrent;
    private TextView tvDateCurrent;
    private TextView tvDescriptionCurrent;
    private TextView tvTemperatureMaxCurrent;
    private TextView tvTemperatureMinCurrent;

    private int tempMaxCurrent;
    private int tempMinCurrent;

    RecyclerView rvNextDay;
    private NextDayWeatherAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");
        setReferences();
        setAdapter();
        getDataFromAPINextDay();
        getDataFromAPICurrentDay();
    }

    private void getDataFromAPICurrentDay() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIOpenWeatherCurrentDay service = retrofit.create(APIOpenWeatherCurrentDay.class);

        String appid = "66f5fe4f80f450d73ad7f7cd100f95b6";
        String q = "hanoi";

        service.getWeatherCityCurrent(q, appid).enqueue(new Callback<WeatherCityCurrent>() {
            @Override
            public void onResponse(Response<WeatherCityCurrent> response) {
                Log.d(TAG, "onResponse: ");
                WeatherCityCurrent current = response.body();
                Picasso
                        .with(getApplicationContext())
                        .load("http://openweathermap.org/img/w/"
                                + current.getWeather().get(0).getIcon()
                                + ".png")
                        .into(imvIconWeatherCurrent);
                tempMaxCurrent = (int)Float.parseFloat(String.valueOf(current.getMain().getTemp_max())) - 273;
                tempMinCurrent = (int)Float.parseFloat(String.valueOf(current.getMain().getTemp_min())) - 273;
                tvTemperatureMinCurrent.setText(tempMinCurrent + "");
                tvTemperatureMaxCurrent.setText(tempMaxCurrent + "");
                tvDescriptionCurrent.setText(current.getWeather().get(0).getMain());
                Log.d(TAG, String.format("onResponse: %s", tempMaxCurrent));
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });

    }

    private void getDataFromAPINextDay() {
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
                Log.d(TAG, "Next Day onResponse: ");
                final WeatherCity weatherCity = response.body();
                Log.d(TAG, String.format("onResponse: %s", weatherCity.getList().get(0).getHumidity()));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (WeatherCity.List list : weatherCity.getList()){
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


            }

            @Override
            public void onFailure(Throwable t) {
                Log.d(TAG, "Next Day onFailure: ");
            }
        });
    }

    private void setReferences() {
        rvNextDay = (RecyclerView) findViewById(R.id.rv_next_day);
        imvIconWeatherCurrent = (ImageView) findViewById(R.id.imv_icon_weather_current);
        tvDateCurrent = (TextView) findViewById(R.id.tv_date_current);
        tvDescriptionCurrent = (TextView) findViewById(R.id.tv_description_current);
        tvTemperatureMaxCurrent = (TextView) findViewById(R.id.tv_temperature_max_current);
        tvTemperatureMinCurrent = (TextView) findViewById(R.id.tv_temperature_min_current);

    }

    private void setAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvNextDay.setLayoutManager(layoutManager);
        rvNextDay.setHasFixedSize(true);
        adapter = new NextDayWeatherAdapter();
        rvNextDay.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
        NextDayModel.list.clear();
        adapter.notifyDataSetChanged();
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
