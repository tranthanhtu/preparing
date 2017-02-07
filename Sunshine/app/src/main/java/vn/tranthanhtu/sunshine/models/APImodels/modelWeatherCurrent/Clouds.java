package vn.tranthanhtu.sunshine.models.APImodels.modelWeatherCurrent;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Dell latitude E6520 on 2/7/2017.
 */

public class Clouds extends RealmObject {
    @SerializedName("all")
    public String all;

    public String getAll() {
        return all;
    }
}