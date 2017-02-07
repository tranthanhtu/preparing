package vn.tranthanhtu.sunshine.models.APImodels.modelNextDay;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by Dell latitude E6520 on 2/7/2017.
 */

public class Temp extends RealmObject {
    @SerializedName("day")
    public String day;
    @SerializedName("min")
    public String min;
    @SerializedName("max")
    public String max;
    @SerializedName("night")
    public String night;
    @SerializedName("eve")
    public String eve;
    @SerializedName("morn")
    public String morn;

    public String getDay() {
        return day;
    }

    public String getMin() {
        return min;
    }

    public String getMax() {
        return max;
    }

    public String getNight() {
        return night;
    }

    public String getEve() {
        return eve;
    }

    public String getMorn() {
        return morn;
    }
}