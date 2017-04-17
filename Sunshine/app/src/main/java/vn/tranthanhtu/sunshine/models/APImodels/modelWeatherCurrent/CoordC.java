package vn.tranthanhtu.sunshine.models.APImodels.modelWeatherCurrent;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;


@SuppressWarnings("ALL")
public class CoordC extends RealmObject{
    @SerializedName("lon")
    private String lon;
    @SerializedName("lat")
    private String lat;

    public String getLon() {
        return lon;
    }

    public String getLat() {
        return lat;
    }
}
