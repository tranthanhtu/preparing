package vn.tranthanhtu.sunshine.activitis;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import vn.tranthanhtu.sunshine.R;
import vn.tranthanhtu.sunshine.managers.RealmHandle;
import vn.tranthanhtu.sunshine.models.APImodels.WeatherCity;
import vn.tranthanhtu.sunshine.models.APImodels.modelNextDay.List;
import vn.tranthanhtu.sunshine.utils.SunshineWeatherUtils;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = DetailActivity.class.toString();
    private String position;
    private WeatherCity weatherCity;

    private ImageView imvIconDetail;
    private TextView tvTemperatureMaxDetail;
    private TextView tvTemperatureMinDetail;
    private TextView tvDescriptionDetail;
    private TextView tvHumidityDetail;
    private TextView tvPressureDetail;
    private TextView tvWindDetail;
    private TextView tvTimeDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Log.d(TAG, "onCreate: ");
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Details");
        getReferences();
        loadDataFromMain();
        weatherCity = RealmHandle.getInstances().getWeatherCity();
        setupUI();
    }

    @SuppressLint("SetTextI18n")
    private void setupUI() {
        List detail = weatherCity.getList().get(Integer.parseInt(position));

        Log.d(TAG, "setupUI: ");

        imvIconDetail.setImageResource(SunshineWeatherUtils
                .getLargeArtResourceIdForWeatherCondition(detail.getWeather().get(0).getId()));
        tvTemperatureMaxDetail.setText((int)Float.parseFloat(detail.getTemp().getMax()) + "\u00b0");
        tvTemperatureMinDetail.setText((int)Float.parseFloat(detail.getTemp().getMin()) + "\u00b0");
        tvDescriptionDetail.setText(detail.getWeather().get(0).getMain());
        tvHumidityDetail.setText(detail.getHumidity() + " %");
        tvPressureDetail.setText(detail.getPressure() + " hPa");

        String windString = SunshineWeatherUtils.getFormattedWind(
                this,
                detail.getSpeed(),
                detail.getDeg());
        tvWindDetail.setText(windString);

        @SuppressLint("SimpleDateFormat") String dateString1 = new SimpleDateFormat("EEEE, MMMM d")
                .format(new Date(detail.getDt() * 1000));
        tvTimeDate.setText(dateString1);

    }

    private void getReferences() {
        imvIconDetail = (ImageView) findViewById(R.id.imv_icon_detail);
        tvTemperatureMaxDetail = (TextView) findViewById(R.id.tv_temperature_max_detail);
        tvTemperatureMinDetail = (TextView) findViewById(R.id.tv_temperature_min_detail);
        tvDescriptionDetail = (TextView) findViewById(R.id.tv_description_detail);
        tvHumidityDetail = (TextView) findViewById(R.id.tv_humidity_detail);
        tvPressureDetail = (TextView) findViewById(R.id.tv_pressure_detail);
        tvWindDetail = (TextView) findViewById(R.id.tv_wind_detail);
        tvTimeDate = (TextView) findViewById(R.id.tv_time_date);
    }

    private void loadDataFromMain() {
        Intent intent = getIntent();
        position = String.valueOf(intent.getSerializableExtra("position"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings_detail){
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_share){
            Intent shareIntent = createShareForecastIntent();
            startActivity(shareIntent);
            return true;
        }
        if (id == android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Intent createShareForecastIntent() {
        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText("")
                .getIntent();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
        }
        return shareIntent;
    }
}
