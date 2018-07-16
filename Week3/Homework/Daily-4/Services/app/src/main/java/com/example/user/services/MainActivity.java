package com.example.user.services;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.user.services.Adapter.RVSongAdapter;
import com.example.user.services.Code.Constants;
import com.example.user.services.Entity.DataCreator;
import com.example.user.services.Entity.Song;
import com.example.user.services.Services.MusicPlayerService;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.one_kiss_calvin_harris);
        mediaPlayer.start();*/

        populateRecycleView();
    }

    private void startService(Song song) {
        Intent musicPlayerServiceIntent = new Intent(MainActivity.this, MusicPlayerService.class);
        musicPlayerServiceIntent.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
        musicPlayerServiceIntent.putExtra("Song", song);
        startService(musicPlayerServiceIntent);
    }

    private void populateRecycleView() {
        RecyclerView rvBooks = findViewById(R.id.rvSongs);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        List<Song> Songs = DataCreator.getSongs();
        RVSongAdapter adapter = new RVSongAdapter(Songs, getSongListener());
        rvBooks.setLayoutManager(layoutManager);
        rvBooks.setAdapter(adapter);
    }

    @NonNull
    private RVSongAdapter.OnItemClickListener getSongListener() {
        return new RVSongAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Song song) {
                //Toast.makeText(MainActivity.this, "Song clicked", Toast.LENGTH_SHORT).show();
                startService(song);
            }
        };
    }

    public void onSongStop(View view) {
        Intent intentStop = new Intent(MainActivity.this, MusicPlayerService.class);
        stopService(intentStop);
    }

    public void onPlayerButton(View view) {
        Toast.makeText(this, view.getId(), Toast.LENGTH_SHORT).show();
    }
}
