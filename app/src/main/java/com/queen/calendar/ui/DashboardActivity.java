package com.queen.calendar.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.EventLog;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.events.Event;
import com.queen.calendar.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    Calendar calendar = Calendar.getInstance();
    CalendarView calendarView;
    Button mButn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_ativity);

        calendarView = (CalendarView) findViewById(R.id.calendar);
        mButn = (Button) findViewById(R.id.ctick);
        calendarView.setClickable(true);

        calendarView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                ///Log.e("event", "click");
            }
        });

        calendarView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, ListActivity.class);
                Toast.makeText(DashboardActivity.this, "Period start", Toast.LENGTH_SHORT).show();
                //Log.e("event", "click");
                return false;
            }
        });

        mButn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, CalActivity.class);
                startActivity(intent);
            }
        });
//        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
//                calendarView.setDate(5,true,true);
//                Log.e("event", "datechanged");
//            }
//        });

        //calendarView.setonCellClickListener()
    }

//    public Calendar getCalendar() {
//        calendar.set(Calendar.DAY_OF_MONTH, 5);
//        calendar.set(Calendar.HOUR, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//
//        long eventStart = calendar.getTimeInMillis();
//        calendar.add(Calendar.DAY_OF_MONTH,5);
//        long eventEnd = calendar.getTimeInMillis();
//
//        Event event = new Event(DashboardActivity.class, eventStart);
//        List<Event> events = new ArrayList<Event>();
//        events.add(event);
//        calendarView.getEventAdapater();
//
//
//        return calendar;
//    }
//
//    public void setCalendarView(CalendarView calendarView) {
//        this.calendarView = calendarView;
//    }

}