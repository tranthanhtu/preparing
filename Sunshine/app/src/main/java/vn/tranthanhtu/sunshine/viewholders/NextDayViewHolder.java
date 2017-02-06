package vn.tranthanhtu.sunshine.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import vn.tranthanhtu.sunshine.R;
import vn.tranthanhtu.sunshine.models.NextDayModel;

/**
 * Created by Dell latitude E6520 on 2/6/2017.
 */

public class NextDayViewHolder extends RecyclerView.ViewHolder {

    private ImageView imvIconWeather;
    private TextView tvDescription;
    private TextView tvTemperatureMax;
    private TextView tvTemperatureMin;
    private Context context;
    private int tempMax;
    private int tempMin;
    public NextDayViewHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();
        imvIconWeather = (ImageView) itemView.findViewById(R.id.imv_icon_weather);
        tvDescription = (TextView) itemView.findViewById(R.id.tv_description);
        tvTemperatureMax = (TextView) itemView.findViewById(R.id.tv_temperature_max);
        tvTemperatureMin = (TextView) itemView.findViewById(R.id.tv_temperature_min);
    }

    public void bind(NextDayModel nextDayModel){
        Picasso.with(context).load(nextDayModel.getUrlIcon()).into(imvIconWeather);
        tvDescription.setText(nextDayModel.getDescription());

        tempMax = (int)Float.parseFloat(nextDayModel.getTemperatureMax());
        tvTemperatureMax.setText(tempMax + "");

        tempMin = (int)Float.parseFloat(nextDayModel.getTemperatureMin());
        tvTemperatureMin.setText(tempMin + "");

    }
}
