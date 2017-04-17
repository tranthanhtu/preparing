package vn.tranthanhtu.sunshine;

import android.app.Application;
import android.util.Log;

import vn.tranthanhtu.sunshine.managers.RealmHandle;
import vn.tranthanhtu.sunshine.networks.NetworkManager;


public class MainAplication extends Application {
    private static final String TAG = MainAplication.class.toString();
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
        NetworkManager.init(this);
        RealmHandle.init(this);
    }
}
