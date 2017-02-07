package vn.tranthanhtu.sunshine.eventbus;

import vn.tranthanhtu.sunshine.models.APImodels.WeatherCityCurrent;

/**
 * Created by Dell latitude E6520 on 2/7/2017.
 */

public class LoadDataCurrentDaySuccessEvent extends BaseEvent{
    private WeatherCityCurrent weatherCityCurrent;

    public LoadDataCurrentDaySuccessEvent(WeatherCityCurrent weatherCityCurrent){
        this.weatherCityCurrent = weatherCityCurrent;
    }

    public WeatherCityCurrent getWeatherCityCurrent(){
        return weatherCityCurrent;
    }
}
