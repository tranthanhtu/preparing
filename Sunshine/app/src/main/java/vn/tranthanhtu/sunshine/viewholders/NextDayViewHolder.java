package vn.tranthanhtu.sunshine.viewholders;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import vn.tranthanhtu.sunshine.R;
import vn.tranthanhtu.sunshine.models.NextDayModel;
import vn.tranthanhtu.sunshine.utils.SunshineWeatherUtils;


public class NextDayViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private final ImageView imvIconWeather;
    private final TextView tvDescription;
    private final TextView tvTemperatureMax;
    private final TextView tvTemperatureMin;
    private final TextView tvDate;

    public NextDayViewHolder(View itemView) {
        super(itemView);

        imvIconWeather = (ImageView) itemView.findViewById(R.id.imv_icon_weather);
        tvDescription = (TextView) itemView.findViewById(R.id.tv_description);
        tvTemperatureMax = (TextView) itemView.findViewById(R.id.tv_temperature_max);
        tvTemperatureMin = (TextView) itemView.findViewById(R.id.tv_temperature_min);
        tvDate = (TextView) itemView.findViewById(R.id.tv_date);
    }

    @SuppressLint("SetTextI18n")
    public void bind(NextDayModel nextDayModel){
        imvIconWeather.setImageResource(SunshineWeatherUtils
                .getSmallArtResourceIdForWeatherCondition(nextDayModel.getId()));
        tvDescription.setText(nextDayModel.getDescription());


        int tempMax = (int) Float.parseFloat(nextDayModel.getTemperatureMax());
        tvTemperatureMax.setText(tempMax + "\u00b0");

        int tempMin = (int) Float.parseFloat(nextDayModel.getTemperatureMin());


        tvTemperatureMin.setText(tempMin + "\u00b0");



        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat1 = new SimpleDateFormat("d");
        String currentTime1 = dateFormat1.format(new Date());
        int current1 = Integer.valueOf(currentTime1);

        @SuppressLint("SimpleDateFormat") String dateString = new SimpleDateFormat("EEEE")
                .format(new Date(nextDayModel.getDateMilis() * 1000));
        @SuppressLint("SimpleDateFormat") String dateString1 = new SimpleDateFormat("d")
                .format(new Date(nextDayModel.getDateMilis() * 1000));
        int date1 = Integer.valueOf(dateString1);
        if (currentTime1.equals(dateString1)){
            tvDate.setText(R.string.today);
        }
        else if (date1 - current1 == 1){
            tvDate.setText(R.string.tomorrow);
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
