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


@SuppressWarnings("ALL")
public class WeatherCityCurrent extends RealmObject{

    @SerializedName("coord")
    private CoordC coord;
    @SerializedName("weather")
    private RealmList<WeatherC> weather;
    @SerializedName("base")
    private String base;
    @SerializedName("main")
    private Main main;
    @SerializedName("wind")
    private Wind wind;
    @SerializedName("clouds")
    private Clouds clouds;
    @SerializedName("dt")
    private String dt;
    @SerializedName("sys")
    private Sys sys;
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("cod")
    private String cod;

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
