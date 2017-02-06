package vn.tranthanhtu.sunshine.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import vn.tranthanhtu.sunshine.models.APImodels.WeatherCityCurrent;

/**
 * Created by Dell latitude E6520 on 2/6/2017.
 */

public interface APIOpenWeatherCurrentDay {
    @GET("/data/2.5/weather")
    Call<WeatherCityCurrent> getWeatherCityCurrent(@Query("q") String q,
                                                   @Query("appid") String appid);
}
