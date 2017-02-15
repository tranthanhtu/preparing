package vn.tranthanhtu.sunshine.activitis;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import vn.tranthanhtu.sunshine.R;
import vn.tranthanhtu.sunshine.adapters.NextDayWeatherAdapter;
import vn.tranthanhtu.sunshine.adapters.RecyclerItemClickListener;
import vn.tranthanhtu.sunshine.managers.RealmHandle;
import vn.tranthanhtu.sunshine.models.APImodels.WeatherCity;
import vn.tranthanhtu.sunshine.models.APImodels.WeatherCityCurrent;
import vn.tranthanhtu.sunshine.models.APImodels.modelNextDay.List;
import vn.tranthanhtu.sunshine.models.NextDayModel;
import vn.tranthanhtu.sunshine.utils.SunshineWeatherUtils;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.toString();
    private ImageView imvIconWeatherCurrent;
    private TextView tvDateCurrent;
    private TextView tvDescriptionCurrent;
    private TextView tvTemperatureMaxCurrent;
    private TextView tvTemperatureMinCurrent;

    private int tempMaxCurrent;
    private int tempMinCurrent;

    SharedPreferences preferences;


    WeatherCity weatherCity;
    WeatherCityCurrent current;
    RecyclerView rvNextDay;
    private NextDayWeatherAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setElevation(0f);
        weatherCity = RealmHandle.getInstances().getWeatherCity();
        current = RealmHandle.getInstances().getWeatherCityCurrent();
        Log.d(TAG, "onCreate: ");
        setReferences();
        setupUI();
        setAdapter();
        loadDatatoAdapter();
        getTimeCurrent();
        addListeners();
    }

    private void addListeners() {
        rvNextDay.addOnItemTouchListener(new RecyclerItemClickListener(this,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                        intent.putExtra("position", position);
                        startActivity(intent);
                        Log.d(TAG, String.format("onItemClick: %s", position));
                    }
                }));
    }

    private void setReferences() {
        rvNextDay = (RecyclerView) findViewById(R.id.rv_next_day);
        imvIconWeatherCurrent = (ImageView) findViewById(R.id.imv_icon_weather_current);
        tvDateCurrent = (TextView) findViewById(R.id.tv_date_current);
        tvDescriptionCurrent = (TextView) findViewById(R.id.tv_description_current);
        tvTemperatureMaxCurrent = (TextView) findViewById(R.id.tv_temperature_max_current);
        tvTemperatureMinCurrent = (TextView) findViewById(R.id.tv_temperature_min_current);

    }


    private void setupUI() {
        Log.d(TAG, "setupUI: " + current.getWeather().get(0).getId());
        imvIconWeatherCurrent.setImageResource(SunshineWeatherUtils
                .getLargeArtResourceIdForWeatherCondition(current.getWeather().get(0).getId()));
        tempMinCurrent = (int) Float.parseFloat(weatherCity.getList().get(0).getTemp().getMin());
        tempMaxCurrent = (int) Float.parseFloat(weatherCity.getList().get(0).getTemp().getMax());

        tvTemperatureMinCurrent.setText(tempMinCurrent + "\u00b0");
        tvTemperatureMaxCurrent.setText(tempMaxCurrent + "\u00b0");

        tvDescriptionCurrent.setText(current.getWeather().get(0).getMain());
    }

    private void loadDatatoAdapter() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (List list : weatherCity.getList()) {
                    NextDayModel.list.add(new NextDayModel(
                            list.getWeather().get(0).getId(),
                            list.getWeather().get(0).getMain(),
                            list.getTemp().getMax(),
                            list.getTemp().getMin(),
                            list.getDt()
                    ));
                    adapter.notifyDataSetChanged();
                }
            }
        });
        setAdapter();
    }

    private void getTimeCurrent() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_map) {
            preferences = PreferenceManager.getDefaultSharedPreferences(this);
            String location = preferences.getString("location", "location");
            String units = preferences.getString("units", "units");
            Boolean enable_notifications = preferences.getBoolean("enable_notifications", false);

            Toast.makeText(this, location + " " + units + " " + enable_notifications, Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
