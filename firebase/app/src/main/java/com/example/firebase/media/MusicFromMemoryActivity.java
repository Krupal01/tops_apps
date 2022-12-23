package com.example.firebase.media;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.example.firebase.R;
import com.example.firebase.databinding.ActivityMusicFromMemoryBinding;

public class MusicFromMemoryActivity extends AppCompatActivity {

    private ActivityMusicFromMemoryBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMusicFromMemoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String path = Environment.getExternalStorageDirectory().getPath()+"/Music/'Laadki' - Sachin-Jigar, Taniskha S, Kirtidan G, Rekha B - Coke Studio@MTV Season 4 ( 256kbps cbr ).mp3";
        MediaPlayer player = new MediaPlayer();

        try {
            player.setDataSource(path);
            player.prepare();
        } catch (Exception e) {
            Log.i("Memory",e.toString());
        }
binding.play.setOnClickListener(v -> {
    player.start();
});
        player.start();
    }
}