package com.example.firebase.broadcast_reciver;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.firebase.R;
import com.example.firebase.databinding.ActivityAlarmBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AlarmActivity extends AppCompatActivity {

    private int hour,minute;
    private ActivityAlarmBinding binding;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private Calendar mcurrentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAlarmBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.Time.setOnClickListener(v -> {

                mcurrentTime = Calendar.getInstance();
                mcurrentTime.set(Calendar.MINUTE,minute);
                mcurrentTime.set(Calendar.HOUR,hour);

                TimePickerDialog mTimePicker = new TimePickerDialog(AlarmActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        binding.Time.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
        });

        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent alarmIntent = new Intent(this, MyReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);

        binding.SetAlarm.setOnClickListener(v -> alarmManager.set(AlarmManager.RTC_WAKEUP, mcurrentTime.getTimeInMillis() , pendingIntent));

    }

}