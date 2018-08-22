package com.example.andy.thankyoujames;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;

public class TimerActivity extends AppCompatActivity {

    TimePicker timer;
    Button finalSendOrder;

    AlarmManager alarm_manager;
    PendingIntent pendingIntent;

    private int hour;
    private int minute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        initView();
        setTime();

    }

    private void initView(){
        timer = findViewById(R.id.simpleTimePicker);
        finalSendOrder = findViewById(R.id.btnFinalSendOrder);

        timer.setIs24HourView(true);
    }

    //Quellen:
    //https://www.codingconnect.net/android-application-creates-alarm-clock/
    //https://github.com/annathehybrid/Github/tree/master/WhaleAlarmClock/app/src/main/java/anna/whalealarmclock
    private void setAlarm() {

            long time;

            if (Build.VERSION.SDK_INT >= 23) {
                hour = timer.getHour();
                minute = timer.getMinute();
            } else {
                hour = timer.getCurrentHour();
                minute = timer.getCurrentMinute();
            }

            Calendar calendar = Calendar.getInstance();
            alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);

            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);

            Intent alarmIntent = new Intent(TimerActivity.this, AlarmService.class);

            pendingIntent = PendingIntent.getService(TimerActivity.this, 0,
                    alarmIntent, 0);

            time = (calendar.getTimeInMillis() - (calendar.getTimeInMillis() % 60000));
            if (System.currentTimeMillis() > time) {
                if (calendar.AM_PM == 0)
                    time = time + (1000 * 60 * 60 * 12);
                else
                    time = time + (1000 * 60 * 60 * 24);
            }

            alarm_manager.set(AlarmManager.RTC_WAKEUP,time,pendingIntent);




    }

    private void setTime(){
        finalSendOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Quelle: https://www.tutlane.com/tutorial/android/android-timepicker-with-examples

                if (Build.VERSION.SDK_INT >= 23 ){
                    hour = timer.getHour();
                    minute = timer.getMinute();
                }
                else{
                    hour = timer.getCurrentHour();
                    minute = timer.getCurrentMinute();
                }


                String hourText = String.valueOf(hour);
                String minuteText = String.valueOf(minute);

                if(minute < 10){
                    minuteText = "0" + String.valueOf(minute);
                }

                if(hour < 10){
                    hourText = "0" + String.valueOf(hour);
                }


                Intent setTime = new Intent(TimerActivity.this,OrderSubmitted.class);
                setTime.putExtra("Hour",hourText);
                setTime.putExtra("Minute",minuteText);

                setAlarm();
                startActivity(setTime);
            }
        });
    }
}
