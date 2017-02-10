package vn.tranthanhtu.sunshine.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell latitude E6520 on 2/6/2017.
 */

public class NextDayModel {
    private int id;
    private String description;
    private String temperatureMax;
    private String temperatureMin;
    private long dateMilis;

    public NextDayModel(int id, String description, String temperatureMax, String temperatureMin, long dateMilis) {
        this.id = id;
        this.description = description;
        this.temperatureMax = temperatureMax;
        this.temperatureMin = temperatureMin;
        this.dateMilis = dateMilis;
    }

    public long getDateMilis() {
        return dateMilis;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getTemperatureMax() {
        return temperatureMax;
    }

    public String getTemperatureMin() {
        return temperatureMin;
    }

    public static List<NextDayModel> list = new ArrayList<>();
}
