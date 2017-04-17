package vn.tranthanhtu.sunshine.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import vn.tranthanhtu.sunshine.models.APImodels.WeatherCity;


public interface APIOpenWeather {
    @GET("/data/2.5/forecast/daily")
    Call<WeatherCity> getWeatherCity(@Query("q") String q,
                                     @Query("units") String units,
                                     @Query("cnt") String cnt,
                                     @Query("appid") String appid);
}
