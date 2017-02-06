package vn.tranthanhtu.sunshine.models.APImodels;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dell latitude E6520 on 2/6/2017.
 */

public class WeatherCity {

    @SerializedName("city")
    public City city;
    @SerializedName("cod")
    public String cod;
    @SerializedName("message")
    public String message;
    @SerializedName("cnt")
    public String cnt;
    @SerializedName("list")
    public java.util.List<List> list;

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

    public java.util.List<List> getList() {
        return list;
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

    public static class City {
        @SerializedName("id")
        public String id;
        @SerializedName("name")
        public String name;
        @SerializedName("coord")
        public Coord coord;
        @SerializedName("country")
        public String country;
        @SerializedName("population")
        public String population;

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

    public static class Temp {
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

    public static class List {
        @SerializedName("dt")
        public String dt;
        @SerializedName("temp")
        public Temp temp;
        @SerializedName("pressure")
        public String pressure;
        @SerializedName("humidity")
        public String humidity;
        @SerializedName("weather")
        public java.util.List<Weather> weather;
        @SerializedName("speed")
        public String speed;
        @SerializedName("deg")
        public String deg;
        @SerializedName("clouds")
        public String clouds;

        public String getDt() {
            return dt;
        }

        public Temp getTemp() {
            return temp;
        }

        public String getPressure() {
            return pressure;
        }

        public String getHumidity() {
            return humidity;
        }

        public java.util.List<Weather> getWeather() {
            return weather;
        }

        public String getSpeed() {
            return speed;
        }

        public String getDeg() {
            return deg;
        }

        public String getClouds() {
            return clouds;
        }
    }
}
