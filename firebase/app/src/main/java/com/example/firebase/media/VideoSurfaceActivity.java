package com.example.firebase.media;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;

import com.example.firebase.databinding.ActivityVideoSurfaceBinding;

public class VideoSurfaceActivity extends AppCompatActivity implements SurfaceHolder.Callback, MediaPlayer.OnPreparedListener {

    private ActivityVideoSurfaceBinding  binding;
    private MediaPlayer mediaPlayer;
    private SurfaceHolder surfaceHolder;
    private String videoPath="http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVideoSurfaceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        surfaceHolder = binding.SurfaceView.getHolder();
        surfaceHolder.addCallback(this);

    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        try {
            mediaPlayer=new MediaPlayer();
            mediaPlayer.setDisplay(surfaceHolder);
            mediaPlayer.setDataSource(videoPath);
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.prepareAsync();
        }catch (Exception ex){

        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }


    @Override
    public void onPrepared(MediaPlayer mp) {
        mediaPlayer.start();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();

        if(mediaPlayer != null){
            if(mediaPlayer.isPlaying()){
                mediaPlayer.stop();
            }
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

}