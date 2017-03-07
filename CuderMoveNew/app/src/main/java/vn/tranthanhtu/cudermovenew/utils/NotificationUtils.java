package vn.tranthanhtu.cudermovenew.utils;

import android.content.Context;
import android.widget.Toast;


public final class NotificationUtils {

    public static void notification(String notification, Context context) {
        Toast.makeText(context, notification, Toast.LENGTH_LONG).show();
    }
}
