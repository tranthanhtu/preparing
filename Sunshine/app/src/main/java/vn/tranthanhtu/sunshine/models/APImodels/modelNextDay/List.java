package vn.tranthanhtu.sunshine.models.APImodels.modelNextDay;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;


@SuppressWarnings("ALL")
public class List extends RealmObject {
    @SerializedName("dt")
    private long dt;
    @SerializedName("temp")
    private Temp temp;
    @SerializedName("pressure")
    private String pressure;
    @SerializedName("humidity")
    private String humidity;
    @SerializedName("weather")
    private RealmList<Weather> weather;
    @SerializedName("speed")
    private float speed;
    @SerializedName("deg")
    private float deg;
    @SerializedName("clouds")
    private String clouds;

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