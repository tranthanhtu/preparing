package vn.tranthanhtu.sunshine.models.APImodels.modelWeatherCurrent;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;


@SuppressWarnings("ALL")
public class Main extends RealmObject {
    @SerializedName("temp")
    private String temp;
    @SerializedName("pressure")
    private String pressure;
    @SerializedName("humidity")
    private String humidity;
    @SerializedName("temp_min")
    private float temp_min;
    @SerializedName("temp_max")
    private float temp_max;
    @SerializedName("sea_level")
    private String sea_level;
    @SerializedName("grnd_level")
    private String grnd_level;

    public String getTemp() {
        return temp;
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public float getTemp_min() {
        return temp_min;
    }

    public float getTemp_max() {
        return temp_max;
    }

    public String getSea_level() {
        return sea_level;
    }

    public String getGrnd_level() {
        return grnd_level;
    }
}