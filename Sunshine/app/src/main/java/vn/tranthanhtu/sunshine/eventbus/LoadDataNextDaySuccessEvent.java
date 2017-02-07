package vn.tranthanhtu.sunshine.eventbus;

import vn.tranthanhtu.sunshine.models.APImodels.WeatherCity;

/**
 * Created by Dell latitude E6520 on 2/7/2017.
 */

public class LoadDataNextDaySuccessEvent extends BaseEvent{
    private WeatherCity weatherCity;

    public LoadDataNextDaySuccessEvent(WeatherCity weatherCity){
        this.weatherCity = weatherCity;
    }

    public WeatherCity getWeatherCity(){
        return weatherCity;
    }
}
