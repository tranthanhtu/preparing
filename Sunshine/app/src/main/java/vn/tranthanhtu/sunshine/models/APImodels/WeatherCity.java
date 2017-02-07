package vn.tranthanhtu.sunshine.models.APImodels;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import vn.tranthanhtu.sunshine.models.APImodels.modelNextDay.City;
import vn.tranthanhtu.sunshine.models.APImodels.modelNextDay.List;

/**
 * Created by Dell latitude E6520 on 2/6/2017.
 */

public class WeatherCity extends RealmObject{

    @SerializedName("city")
    public City city;
    @SerializedName("cod")
    public String cod;
    @SerializedName("message")
    public String message;
    @SerializedName("cnt")
    public String cnt;
    @SerializedName("list")
    public RealmList<List> list;

    public City getCity() {
        return city;
    }

    public String getCod() {
        return cod;
    }

    public String getMessage() {
        return message;
    }

    public String getCnt() {
        return cnt;
    }

    public RealmList<List> getList() {
        return list;
    }
    
}
