package vn.tranthanhtu.sunshine.models.APImodels;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import vn.tranthanhtu.sunshine.models.APImodels.modelWeatherCurrent.Clouds;
import vn.tranthanhtu.sunshine.models.APImodels.modelWeatherCurrent.CoordC;
import vn.tranthanhtu.sunshine.models.APImodels.modelWeatherCurrent.Main;
import vn.tranthanhtu.sunshine.models.APImodels.modelWeatherCurrent.Sys;
import vn.tranthanhtu.sunshine.models.APImodels.modelWeatherCurrent.WeatherC;
import vn.tranthanhtu.sunshine.models.APImodels.modelWeatherCurrent.Wind;

/**
 * Created by Dell latitude E6520 on 2/6/2017.
 */

public class WeatherCityCurrent extends RealmObject{

    @SerializedName("coord")
    public CoordC coord;
    @SerializedName("weather")
    public RealmList<WeatherC> weather;
    @SerializedName("base")
    public String base;
    @SerializedName("main")
    public Main main;
    @SerializedName("wind")
    public Wind wind;
    @SerializedName("clouds")
    public Clouds clouds;
    @SerializedName("dt")
    public String dt;
    @SerializedName("sys")
    public Sys sys;
    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName("cod")
    public String cod;

    public CoordC getCoord() {
        return coord;
    }

    public RealmList<WeatherC> getWeather() {
        return weather;
    }

    public String getBase() {
        return base;
    }

    public Main getMain() {
        return main;
    }

    public Wind getWind() {
        return wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public String getDt() {
        return dt;
    }

    public Sys getSys() {
        return sys;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCod() {
        return cod;
    }

}
