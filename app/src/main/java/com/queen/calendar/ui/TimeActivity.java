package com.queen.calendar.ui;

import androidx.appcompat.app.AppCompatActivity;
import com.queen.calendar.R;
import com.queen.calendar.adapter.AlamReceiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class TimeActivity extends AppCompatActivity implements View.OnClickListener {
    private int notificationId = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        //set onClick listener
        findViewById(R.id.set).setOnClickListener(this);
        findViewById(R.id.cancel).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        EditText editText = findViewById(R.id.edit);
        TimePicker timePicker = findViewById(R.id.timePicker);

        //Set NotificationId & message
        Intent intent = new Intent(TimeActivity.this, AlamReceiver.class);
        intent.putExtra("notificationId", notificationId);
        intent.putExtra("message", editText.getText().toString());

        //Pending Intent
        PendingIntent alarmIntent = PendingIntent.getBroadcast(TimeActivity.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        //AlarmManager
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        switch (v.getId()){
            case R.id.set:
                //setAlarm
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();

                //create time
                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, hour);
                startTime.set(Calendar.MINUTE, minute);
                startTime.set(Calendar.SECOND, 0);
                long alarmStartTime = startTime.getTimeInMillis();

                //SetAlarm
                alarmManager.set(AlarmManager.RTC_WAKEUP, alarmStartTime, alarmIntent);
                Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
                break;

            case R.id.cancel:
                //cancelAlarm
                alarmManager.cancel(alarmIntent);
                Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
                break;
        }


    }
}