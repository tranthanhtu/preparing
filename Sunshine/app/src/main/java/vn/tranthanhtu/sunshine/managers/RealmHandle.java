package vn.tranthanhtu.sunshine.managers;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmResults;
import vn.tranthanhtu.sunshine.models.APImodels.WeatherCity;
import vn.tranthanhtu.sunshine.models.APImodels.WeatherCityCurrent;


@SuppressWarnings("ALL")
public class RealmHandle {
    private final Realm realm;

    private RealmHandle(Context context){
        Realm.init(context);
        this.realm = Realm.getDefaultInstance();
    }
    //-----------------------------------------------------------/
    public void addWeatherCity(WeatherCity weatherCity){
        realm.beginTransaction();
        realm.copyToRealm(weatherCity);
        realm.commitTransaction();
    }

    public WeatherCity getWeatherCity(){
        WeatherCity weatherCity = null;
        try {
            RealmResults<WeatherCity> weatherCities = realm
                    .where(WeatherCity.class)
                    .findAll();

            weatherCity = weatherCities.get((int)(getCount() - 1));
        }catch (Exception ignored){

        }

        return weatherCity;
    }

    private long getCount() {
        return realm.where(WeatherCity.class).count();
    }
    //---------------------------------------------------------------/

    public void addWeatherCityCurrent(WeatherCityCurrent weatherCityCurrent){
        realm.beginTransaction();
        realm.copyToRealm(weatherCityCurrent);
        realm.commitTransaction();
    }

    public WeatherCityCurrent getWeatherCityCurrent(){
        WeatherCityCurrent weatherCityCurrent = null;
        try {
            RealmResults<WeatherCityCurrent> weatherCityCurrents = realm
                    .where(WeatherCityCurrent.class)
                    .findAll();

            weatherCityCurrent = weatherCityCurrents.get((int)(getCount() -1));
        }catch (Exception ignored){

        }
        return weatherCityCurrent;
    }

    public long getCountCurrent() {
        return realm.where(WeatherCityCurrent.class).count();
    }


    //---------------------------------------------------------------/
    private static RealmHandle instances;

    public static RealmHandle getInstances(){
        return instances;
    }

    public static void init(Context context){
        instances = new RealmHandle(context);
    }
}
