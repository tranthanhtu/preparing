package vn.tranthanhtu.sunshine.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import vn.tranthanhtu.sunshine.models.APImodels.WeatherCityCurrent;


public interface APIOpenWeatherCurrentDay {
    @GET("/data/2.5/weather")
    Call<WeatherCityCurrent> getWeatherCityCurrent(@Query("q") String q,
                                                   @Query("appid") String appid);
}
