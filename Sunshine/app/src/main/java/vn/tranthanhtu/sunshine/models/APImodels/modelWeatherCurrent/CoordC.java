package vn.tranthanhtu.sunshine.models.APImodels.modelWeatherCurrent;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Dell latitude E6520 on 2/7/2017.
 */

public class CoordC extends RealmObject{
    @SerializedName("lon")
    public String lon;
    @SerializedName("lat")
    public String lat;

    public String getLon() {
        return lon;
    }

    public String getLat() {
        return lat;
    }
}
