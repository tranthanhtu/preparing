package vn.tranthanhtu.sunshine.models.APImodels.modelWeatherCurrent;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;


@SuppressWarnings("ALL")
public class Wind extends RealmObject {
    @SerializedName("speed")
    private String speed;
    @SerializedName("deg")
    private String deg;

    public String getSpeed() {
        return speed;
    }

    public String getDeg() {
        return deg;
    }
}