package vn.tranthanhtu.sunshine.models.APImodels.modelWeatherCurrent;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Dell latitude E6520 on 2/7/2017.
 */

public class Sys extends RealmObject {
    @SerializedName("message")
    public String message;
    @SerializedName("country")
    public String country;
    @SerializedName("sunrise")
    public String sunrise;
    @SerializedName("sunset")
    public String sunset;

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
