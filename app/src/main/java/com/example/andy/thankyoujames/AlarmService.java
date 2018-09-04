package com.example.andy.thankyoujames;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
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


        //Ton für Alarm, sollte noch auf Vibration oder ähnliches angepasst werden
        //Quelle: https://www.codingconnect.net/android-application-creates-alarm-clock/

//        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        Ringtone ringtone = RingtoneManager.getRingtone(this, alarmUri);
//        ringtone.play();


        //Vibration
        //Quelle: https://stackoverflow.com/questions/13950338/how-to-make-an-android-device-vibrate
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500,VibrationEffect.DEFAULT_AMPLITUDE));
        }else {
            //deprecated in API 26
            v.vibrate(500);
        }

        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        //Notification "Essen ist fertig!", wenn Zeit für Abholung erreicht ist
        //Quelle:https://codinginflow.com/tutorials/android/foreground-service
        // und https://developer.android.com/training/notify-user/build-notification
        Intent notificationIntent = new Intent(this, OrderSubmitted.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);


        Uri gmmIntentUri = Uri.parse("geo:48.998589,12.094472?q="+ Uri.encode("Universität Regensburg"));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        mapIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent gpsPendingIntent = PendingIntent.getActivity(this,0,mapIntent,0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Essen ist fertig")
                .setContentText("Dein Essen steht zur Abholung bereit!")
                .setSmallIcon(R.drawable.butlericon)
                .setAutoCancel(true)
                .setSound(soundUri)
                .setContentIntent(pendingIntent)
                .addAction(R.drawable.butlericon,"Wo muss ich hin?",gpsPendingIntent)
                .build();

        startForeground(1, notification);


        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }


}


