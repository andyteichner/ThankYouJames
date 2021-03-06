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

        //Notification
        // - Vibration
        // when Notification comes in
        //Source: https://stackoverflow.com/questions/13950338/how-to-make-an-android-device-vibrate
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500,VibrationEffect.DEFAULT_AMPLITUDE));
        }else {
            v.vibrate(500);
        }

        //Notification
        //-Sound
        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        //Notification
        // - Message
        // "Essen ist fertig!", when time for picking up food has arrived
        //Source:https://codinginflow.com/tutorials/android/foreground-service
        // and   https://developer.android.com/training/notify-user/build-notification
        Intent notificationIntent = new Intent(this, OrderSubmitted.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);

        //Notification
        // - Interaction
        // attaches possibility to notification to be directed to the Google Maps App to locate the restaurant
        //Source: https://developers.google.com/maps/documentation/urls/android-intents
        Uri gmmIntentUri = Uri.parse("geo:48.998589,12.094472?q="+ Uri.encode("Universität Regensburg"));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        mapIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent gpsPendingIntent = PendingIntent.getActivity(this,0,mapIntent,0);


        //Notification Builder
        //Source:https://developer.android.com/training/notify-user/build-notification
        //       https://codinginflow.com/tutorials/android/foreground-service
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


