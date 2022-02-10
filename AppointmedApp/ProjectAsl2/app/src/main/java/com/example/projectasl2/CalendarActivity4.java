package com.example.projectasl2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;



public class CalendarActivity4 extends AppCompatActivity {
    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar4);
        calendarView=findViewById(R.id.calendarView2);
        calendarView.setShowWeekNumber(false);
        //calendarView.addOnLayoutChangeListener();
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String aaa= i+" "+i1+" "+i2;

                Toast.makeText(getApplicationContext(), aaa, Toast.LENGTH_LONG).show();
            }
        });
    }
}