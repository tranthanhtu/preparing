package vn.tranthanhtu.sunshine.networks;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class NetworkManager {
    private static NetworkManager instance;
    private final ConnectivityManager connectivityManager;

    public static NetworkManager getInstance() {
        return instance;
    }

    public static void init(Context context) {
        instance = new NetworkManager(context);
    }

    private NetworkManager(Context context) {
        this.connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public boolean isConnectedToInternet() {
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}