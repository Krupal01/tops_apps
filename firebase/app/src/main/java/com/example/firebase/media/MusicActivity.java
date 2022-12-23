package com.example.firebase.media;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.SeekBar;

import com.example.firebase.R;
import com.example.firebase.databinding.ActivityMusicBinding;

public class MusicActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private ActivityMusicBinding  binding;
    private MediaPlayer mediaPlayer;
    private Handler handler = new Handler();
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            int duration=mediaPlayer.getDuration();   // in millisecond
            binding.totleTime.setText(getTimeFromMillis(duration));

            int currentPosition=mediaPlayer.getCurrentPosition();
            binding.current.setText(getTimeFromMillis(currentPosition));

            // reschedule handler after 1 second
            handler.postDelayed(this, 1000);

            int progress = (currentPosition*100)/duration;
            binding.seekbar.setProgress(progress);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMusicBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.play.setOnClickListener(v -> {
            if (mediaPlayer==null){
            mediaPlayer = MediaPlayer.create(this,R.raw.blue_eyes);
            binding.play.setBackgroundResource(android.R.drawable.ic_media_pause);
            handler.postDelayed(runnable,1000);
            mediaPlayer.start();
            }else {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    binding.play.setBackgroundResource(android.R.drawable.ic_media_play);
                }else {
                    mediaPlayer.start();
                    binding.play.setBackgroundResource(android.R.drawable.ic_media_pause);
                }
            }
            binding.seekbar.setOnSeekBarChangeListener(this);

        });

    }

    private String getTimeFromMillis(int duration) {
        int hour=duration/(60*60*1000);
        int min=(duration%(60*60*1000))/(60*1000);
        int sec=((duration%(60*60*1000))%(60*1000))/1000;
        return hour+":"+min+":"+sec;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(mediaPlayer!=null && fromUser){
            // progress = percentage
            int duration=mediaPlayer.getDuration();
            int currentPosition = (progress*duration)/100;
            mediaPlayer.seekTo(currentPosition);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}