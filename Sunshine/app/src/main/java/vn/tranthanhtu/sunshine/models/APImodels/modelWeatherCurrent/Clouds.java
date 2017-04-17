package vn.tranthanhtu.sunshine.models.APImodels.modelWeatherCurrent;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;


@SuppressWarnings("ALL")
public class Clouds extends RealmObject {
    @SerializedName("all")
    private String all;

    public String getAll() {
        return all;
    }
}