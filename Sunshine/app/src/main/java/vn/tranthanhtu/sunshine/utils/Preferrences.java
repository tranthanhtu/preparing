package vn.tranthanhtu.sunshine.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Hau on 10/01/2017.
 */

public class Preferrences {

    public static final String KEY_CITY = "location";
    public static final String CITY_DEFAULT = "Ha noi";

    public static final String KEY_UNITS = "units";
    public static final String UNIT_DEFAUT = "metric";

    private SharedPreferences sharedPreferences;

    public Preferrences(Context context) {
        this.sharedPreferences = context.getSharedPreferences("weather", Context.MODE_PRIVATE);
    }

    public void putCity(String city) {
        this.sharedPreferences.edit()
                .putString(KEY_CITY, city)
                .commit();
    }

    public String getCity() {
        return this.sharedPreferences.getString(KEY_CITY, CITY_DEFAULT);
    }

    public void putUnits(String units){
        this.sharedPreferences.edit()
                .putString(KEY_UNITS, units)
                .commit();
    }

    public String getUnit(){
        return this.sharedPreferences.getString(KEY_UNITS, UNIT_DEFAUT);
    }

    private static Preferrences _sharePointer;

    public static Preferrences getInstance() {
        return _sharePointer;
    }

    public static void init(Context context) {
        _sharePointer = new Preferrences(context);
    }
}
