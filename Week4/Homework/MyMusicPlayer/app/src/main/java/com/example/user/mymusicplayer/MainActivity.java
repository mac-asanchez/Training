package com.example.user.mymusicplayer;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.user.mymusicplayer.Model.Constants;
import com.example.user.mymusicplayer.Model.DataCreator;
import com.example.user.mymusicplayer.Model.Logger;
import com.example.user.mymusicplayer.Model.Song;
import com.example.user.mymusicplayer.Services.MPService;
import com.example.user.mymusicplayer.Services.MySongsBroadcast;
import com.example.user.mymusicplayer.Services.MySongsService;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MySongsBroadcast myReceiver;
    private IntentFilter intentFilter;
    RecyclerView rvSongs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvSongs = findViewById(R.id.rvSongs);
        startDataGeneratorService();
    }

    @Override
    protected void onStart() {
        super.onStart();
        myReceiver = new MySongsBroadcast(rvSongs);
        intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.ACTION.PASS_SONGS);
        LocalBroadcastManager.getInstance(this).registerReceiver(myReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(myReceiver);
    }

    private void startDataGeneratorService() {
        Intent intIntent = new Intent(getApplicationContext(), MySongsService.class);
        startService(intIntent);
    }

    public void onPlayClicked(View view) {
        List<Song> songs = DataCreator.getSongs();

        Intent intent = new Intent(getApplicationContext(), MPService.class);
        intent.setAction(Constants.ACTION.PLAY);
        intent.putExtra(Constants.SongTAG, songs.get(0));
        startService(intent);
    }

    public void onStopClicked(View view) {
        stopService(new Intent(getApplicationContext(), MPService.class));
    }
}
