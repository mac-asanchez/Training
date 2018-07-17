package com.example.user.mymusicplayer;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.user.mymusicplayer.Model.Constants;
import com.example.user.mymusicplayer.Model.DataCreator;
import com.example.user.mymusicplayer.Model.Song;
import com.example.user.mymusicplayer.Services.CustomReceiver;
import com.example.user.mymusicplayer.Services.MPService;
import com.example.user.mymusicplayer.Services.MySongsBroadcast;
import com.example.user.mymusicplayer.Services.MySongsService;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    CustomReceiver customReceiver;
    RecyclerView rvSongs;
    private MySongsBroadcast myReceiver;
    private IntentFilter intentFilter;

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

        customReceiver = new CustomReceiver();
        IntentFilter newintentFilter = new IntentFilter();
        newintentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        newintentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        newintentFilter.addAction(Intent.ACTION_CAMERA_BUTTON);
        newintentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        newintentFilter.addAction(Intent.ACTION_HEADSET_PLUG);
        LocalBroadcastManager.getInstance(this).registerReceiver(customReceiver, newintentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(myReceiver);

        LocalBroadcastManager.getInstance(this).unregisterReceiver(customReceiver);
    }

    private void startDataGeneratorService() {
        Intent intIntent = new Intent(getApplicationContext(), MySongsService.class);
        startService(intIntent);
    }

    //region Buttons
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
    //endregion
}
