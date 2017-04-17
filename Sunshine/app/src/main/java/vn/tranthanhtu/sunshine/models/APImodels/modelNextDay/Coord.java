package vn.tranthanhtu.sunshine.models.APImodels.modelNextDay;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;


@SuppressWarnings("ALL")
public class Coord extends RealmObject {
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