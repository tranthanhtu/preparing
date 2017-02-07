package vn.tranthanhtu.sunshine.services;

import android.Manifest;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Dell latitude E6520 on 2/7/2017.
 */

public class LoadLocationCityGPS extends IntentService {
    public static final String TAG = LoadLocationCityGPS.class.toString();
    private String city;

    public LoadLocationCityGPS() {
        super("LoadLocationCityGPS");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        loadLocation();
    }

    private void loadLocation() {

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        city = hereLocation(location.getLatitude(), location.getLongitude());
        Log.d(TAG, "loadLocation: " + city);
        Log.d(TAG, "loadLocation: "+ location.getLatitude());
        Log.d(TAG, "loadLocation: "+ location.getLongitude());

//            EventBus.getDefault().post(new LoadLocationSuccessEvent());
    }

    public String hereLocation(double lat, double lon) {
        String curCity = "";

        Geocoder geocoder = new Geocoder(LoadLocationCityGPS.this, Locale.getDefault());

        List<Address> addresses;

        try {
            addresses = geocoder.getFromLocation(lat, lon, 1);
            if (addresses.size() > 0) {
                curCity = addresses.get(0).getLocality();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return curCity;

    }
}
