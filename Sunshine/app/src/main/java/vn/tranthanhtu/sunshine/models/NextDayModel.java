package vn.tranthanhtu.sunshine.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dell latitude E6520 on 2/6/2017.
 */

public class NextDayModel {
    private String urlIcon;
    private String description;
    private String temperatureMax;
    private String temperatureMin;

    public NextDayModel(String urlIcon, String description, String temperatureMax, String temperatureMin) {
        this.urlIcon = urlIcon;
        this.description = description;
        this.temperatureMax = temperatureMax;
        this.temperatureMin = temperatureMin;
    }

    public String getUrlIcon() {
        return urlIcon;
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
