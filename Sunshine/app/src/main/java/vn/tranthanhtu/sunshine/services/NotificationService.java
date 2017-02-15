package vn.tranthanhtu.sunshine.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import vn.tranthanhtu.sunshine.R;
import vn.tranthanhtu.sunshine.activitis.MainActivity;
import vn.tranthanhtu.sunshine.managers.RealmHandle;
import vn.tranthanhtu.sunshine.models.APImodels.WeatherCity;
import vn.tranthanhtu.sunshine.models.APImodels.modelNextDay.List;
import vn.tranthanhtu.sunshine.utils.SunshineWeatherUtils;

/**
 * Created by Dell latitude E6520 on 2/13/2017.
 */

public class NotificationService extends Service {
    WeatherCity weatherCity;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        weatherCity = RealmHandle.getInstances().getWeatherCity();
        List list = weatherCity.getList().get(0);
        String shortDescription = SunshineWeatherUtils
                .getStringForWeatherCondition(
                        getApplicationContext(),
                        list.getWeather().get(0).getId()
                );
        Resources resources = getApplicationContext().getResources();
        int largeArtResourceId = SunshineWeatherUtils
                .getLargeArtResourceIdForWeatherCondition(list.getWeather().get(0).getId());

        Bitmap largeIcon = BitmapFactory.decodeResource(
                resources,
                largeArtResourceId);


        NotificationManager notificationManager = (NotificationManager) getApplicationContext()
                .getSystemService(Context.NOTIFICATION_SERVICE);

        Intent repeat_intent = new Intent(getApplicationContext(), MainActivity.class);

        repeat_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                getApplicationContext(),
                0,
                repeat_intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext())
                .setColor(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimary))
                .setSmallIcon(SunshineWeatherUtils
                        .getSmallArtResourceIdForWeatherCondition(list.getWeather().get(0).getId()))
                .setLargeIcon(largeIcon)
                .setContentTitle("Sunshine")
                .setContentText("Forcast:" + shortDescription
                        + " - " + "High:" + list.getTemp().getMax()
                        + " - " + "Low:" + list.getTemp().getMin())
                .setAutoCancel(true);

        builder.setContentIntent(pendingIntent);

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        builder.setSound(alarmSound);

        notificationManager.notify(100, builder.build());

    }


}
