package vn.tranthanhtu.sunshine.viewholders;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import vn.tranthanhtu.sunshine.R;
import vn.tranthanhtu.sunshine.managers.RealmHandle;
import vn.tranthanhtu.sunshine.models.APImodels.WeatherCity;
import vn.tranthanhtu.sunshine.models.NextDayModel;
import vn.tranthanhtu.sunshine.utils.SunshineWeatherUtils;

/**
 * Created by Dell latitude E6520 on 2/6/2017.
 */

public class NextDayViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private ImageView imvIconWeather;
    private TextView tvDescription;
    private TextView tvTemperatureMax;
    private TextView tvTemperatureMin;
    private TextView tvDate;
    private Context context;
    private int tempMax;
    private int tempMin;
    private WeatherCity weatherCity;
    private Cursor cursor;
    public NextDayViewHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        imvIconWeather = (ImageView) itemView.findViewById(R.id.imv_icon_weather);
        tvDescription = (TextView) itemView.findViewById(R.id.tv_description);
        tvTemperatureMax = (TextView) itemView.findViewById(R.id.tv_temperature_max);
        tvTemperatureMin = (TextView) itemView.findViewById(R.id.tv_temperature_min);
        tvDate = (TextView) itemView.findViewById(R.id.tv_date);
    }

    public void bind(NextDayModel nextDayModel){
        imvIconWeather.setImageResource(SunshineWeatherUtils
                .getSmallArtResourceIdForWeatherCondition(nextDayModel.getId()));
        tvDescription.setText(nextDayModel.getDescription());

        weatherCity = RealmHandle.getInstances().getWeatherCity();
        tempMax = (int)Float.parseFloat(nextDayModel.getTemperatureMax());
        tvTemperatureMax.setText(tempMax + "\u00b0");

        tempMin = (int)Float.parseFloat(nextDayModel.getTemperatureMin());


        tvTemperatureMin.setText(tempMin + "\u00b0");


        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE");
        String currentTime = dateFormat.format(new Date());

        SimpleDateFormat dateFormat1 = new SimpleDateFormat("d");
        String currentTime1 = dateFormat1.format(new Date());
        int current1 = Integer.valueOf(currentTime1);

        String dateString = new SimpleDateFormat("EEEE").format(new Date(nextDayModel.getDateMilis() * 1000));
        String dateString1 = new SimpleDateFormat("d").format(new Date(nextDayModel.getDateMilis() * 1000));
        int date1 = Integer.valueOf(dateString1);
        if (currentTime1.equals(dateString1)){
            tvDate.setText("Today");
        }
        else if (date1 - current1 == 1){
            tvDate.setText("Tommorow");
        }else {
            tvDate.setText(dateString);
        }




//        tvDate.setText(SunshineDateUtils.getDate(nextDayModel.getDateMilis()));
//        tvDate.setText(SunshineDateUtils.dayStringFormat(1486786493));
//        tvDate.setText(SunshineDateUtils.getDayName(context, nextDayModel.getDateMilis()));
//        tvDate.setText(SunshineDateUtils.getFriendlyDateString(context, nextDayModel.getDateMilis() * 1000, false));
    }

    @Override
    public void onClick(View v) {
        v.setOnClickListener(this);
    }
}
