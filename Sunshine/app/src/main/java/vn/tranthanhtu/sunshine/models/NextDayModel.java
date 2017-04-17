package vn.tranthanhtu.sunshine.models;

import java.util.ArrayList;
import java.util.List;


public class NextDayModel {
    private final int id;
    private final String description;
    private final String temperatureMax;
    private final String temperatureMin;
    private final long dateMilis;

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

    public static final List<NextDayModel> list = new ArrayList<>();
}
