package vn.tranthanhtu.sunshine.models.APImodels.modelNextDay;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;


@SuppressWarnings("ALL")
public class City extends RealmObject {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("coord")
    private Coord coord;
    @SerializedName("country")
    private String country;
    @SerializedName("population")
    private String population;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coord getCoord() {
        return coord;
    }

    public String getCountry() {
        return country;
    }

    public String getPopulation() {
        return population;
    }
}