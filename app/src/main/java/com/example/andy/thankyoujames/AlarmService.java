package com.example.andy.thankyoujames;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import static com.example.andy.thankyoujames.MainJames.CHANNEL_ID;


public class AlarmService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){

        Toast.makeText(this,"ALARM...!", Toast.LENGTH_LONG).show();

        //Ton für Alarm, sollte noch auf Vibration oder ähnliches angepasst werden
        //Quelle: https://www.codingconnect.net/android-application-creates-alarm-clock/
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null)
        {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        Ringtone ringtone = RingtoneManager.getRingtone(this, alarmUri);
        ringtone.play();


        //Notification "Essen ist fertig!", wenn Zeit für Abholung erreicht ist
        //Quelle:https://codinginflow.com/tutorials/android/foreground-service
        // und https://developer.android.com/training/notify-user/build-notification
        Intent notificationIntent = new Intent(this, OrderSubmitted.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Essen ist fertig")
                .setContentText("Dein Essen steht zur Abholung bereit!")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();

        startForeground(1, notification);


        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}


