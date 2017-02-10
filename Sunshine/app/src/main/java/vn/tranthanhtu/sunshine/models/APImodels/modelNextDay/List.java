package vn.tranthanhtu.sunshine.models.APImodels.modelNextDay;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Dell latitude E6520 on 2/7/2017.
 */

public class List extends RealmObject {
    @SerializedName("dt")
    public long dt;
    @SerializedName("temp")
    public Temp temp;
    @SerializedName("pressure")
    public String pressure;
    @SerializedName("humidity")
    public String humidity;
    @SerializedName("weather")
    public RealmList<Weather> weather;
    @SerializedName("speed")
    public float speed;
    @SerializedName("deg")
    public float deg;
    @SerializedName("clouds")
    public String clouds;

    public long getDt() {
        return dt;
    }

    public Temp getTemp() {
        return temp;
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public RealmList<Weather> getWeather() {
        return weather;
    }

    public float getSpeed() {
        return speed;
    }

    public float getDeg() {
        return deg;
    }

    public String getClouds() {
        return clouds;
    }
}