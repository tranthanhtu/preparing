package vn.tranthanhtu.sunshine.models.APImodels;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import vn.tranthanhtu.sunshine.models.APImodels.modelNextDay.City;
import vn.tranthanhtu.sunshine.models.APImodels.modelNextDay.List;


@SuppressWarnings("ALL")
public class WeatherCity extends RealmObject{

    @SerializedName("city")
    private City city;
    @SerializedName("cod")
    private String cod;
    @SerializedName("message")
    private String message;
    @SerializedName("cnt")
    private String cnt;
    @SerializedName("list")
    private RealmList<List> list;

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
