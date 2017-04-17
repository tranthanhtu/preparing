package vn.tranthanhtu.sunshine.models.APImodels.modelNextDay;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;


@SuppressWarnings("ALL")
public class Temp extends RealmObject {
    @SerializedName("day")
    private String day;
    @SerializedName("min")
    private String min;
    @SerializedName("max")
    private String max;
    @SerializedName("night")
    private String night;
    @SerializedName("eve")
    private String eve;
    @SerializedName("morn")
    private String morn;

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