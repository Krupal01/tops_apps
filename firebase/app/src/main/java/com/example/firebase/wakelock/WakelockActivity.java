package com.example.firebase.wakelock;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.widget.Toast;

import com.example.firebase.databinding.ActivityWakelockBinding;

public class WakelockActivity extends AppCompatActivity {

    private ActivityWakelockBinding binding;
    private PowerManager.WakeLock wakeLock;
    @SuppressLint("InvalidWakeLockTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWakelockBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.WakeOn.setOnClickListener(v -> {
            PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
            wakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "my Wakelock");
            wakeLock.acquire();
            Toast.makeText(getApplicationContext(),"on",Toast.LENGTH_LONG).show();
        });

        binding.WakeOff.setOnClickListener(v -> {
            wakeLock.release();
            Toast.makeText(getApplicationContext(),"off",Toast.LENGTH_LONG).show();
        });
    }
}