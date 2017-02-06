package vn.tranthanhtu.sunshine.models.APImodels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Dell latitude E6520 on 2/6/2017.
 */

public class WeatherCityCurrent {

    @SerializedName("coord")
    public Coord coord;
    @SerializedName("weather")
    public List<Weather> weather;
    @SerializedName("base")
    public String base;
    @SerializedName("main")
    public Main main;
    @SerializedName("wind")
    public Wind wind;
    @SerializedName("clouds")
    public Clouds clouds;
    @SerializedName("dt")
    public String dt;
    @SerializedName("sys")
    public Sys sys;
    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName("cod")
    public String cod;

    public Coord getCoord() {
        return coord;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public String getBase() {
        return base;
    }

    public Main getMain() {
        return main;
    }

    public Wind getWind() {
        return wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public String getDt() {
        return dt;
    }

    public Sys getSys() {
        return sys;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCod() {
        return cod;
    }

    public static class Coord {
        @SerializedName("lon")
        public String lon;
        @SerializedName("lat")
        public String lat;

        public String getLon() {
            return lon;
        }

        public String getLat() {
            return lat;
        }
    }

    public static class Weather {
        @SerializedName("id")
        public String id;
        @SerializedName("main")
        public String main;
        @SerializedName("description")
        public String description;
        @SerializedName("icon")
        public String icon;

        public String getId() {
            return id;
        }

        public String getMain() {
            return main;
        }

        public String getDescription() {
            return description;
        }

        public String getIcon() {
            return icon;
        }
    }

    public static class Main {
        @SerializedName("temp")
        public String temp;
        @SerializedName("pressure")
        public String pressure;
        @SerializedName("humidity")
        public String humidity;
        @SerializedName("temp_min")
        public float temp_min;
        @SerializedName("temp_max")
        public float temp_max;
        @SerializedName("sea_level")
        public String sea_level;
        @SerializedName("grnd_level")
        public String grnd_level;

        public String getTemp() {
            return temp;
        }

        public String getPressure() {
            return pressure;
        }

        public String getHumidity() {
            return humidity;
        }

        public float getTemp_min() {
            return temp_min;
        }

        public float getTemp_max() {
            return temp_max;
        }

        public String getSea_level() {
            return sea_level;
        }

        public String getGrnd_level() {
            return grnd_level;
        }
    }

    public static class Wind {
        @SerializedName("speed")
        public String speed;
        @SerializedName("deg")
        public String deg;

        public String getSpeed() {
            return speed;
        }

        public String getDeg() {
            return deg;
        }
    }

    public static class Clouds {
        @SerializedName("all")
        public String all;

        public String getAll() {
            return all;
        }
    }

    public static class Sys {
        @SerializedName("message")
        public String message;
        @SerializedName("country")
        public String country;
        @SerializedName("sunrise")
        public String sunrise;
        @SerializedName("sunset")
        public String sunset;

        public String getMessage() {
            return message;
        }

        public String getCountry() {
            return country;
        }

        public String getSunrise() {
            return sunrise;
        }

        public String getSunset() {
            return sunset;
        }
    }
}
