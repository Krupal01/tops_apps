package com.example.firebase.media;

import androidx.appcompat.app.AppCompatActivity;


import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;

import com.example.firebase.R;
import com.example.firebase.databinding.ActivityVideoBinding;

public class VideoActivity extends AppCompatActivity {

    private ActivityVideoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVideoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.videoView.setVideoURI(Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"));

        MediaController mediaController=new MediaController(this);
        binding.videoView.setMediaController(mediaController);
        mediaController.setAnchorView(binding.videoView);

        binding.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                binding.videoView.start();
            }
        });

        binding.videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

            }
        });

    }
}