package vn.tranthanhtu.sunshine.models.APImodels.modelWeatherCurrent;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;


@SuppressWarnings("ALL")
public class Sys extends RealmObject {
    @SerializedName("message")
    private String message;
    @SerializedName("country")
    private String country;
    @SerializedName("sunrise")
    private String sunrise;
    @SerializedName("sunset")
    private String sunset;

    public String getMessage() {
        return message;
    }

    public String getCountry() {
        return country;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }
}
