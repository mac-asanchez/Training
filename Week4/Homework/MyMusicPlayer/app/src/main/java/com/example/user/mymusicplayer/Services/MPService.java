package com.example.user.mymusicplayer.Services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.example.user.mymusicplayer.Model.Constants;
import com.example.user.mymusicplayer.Model.Logger;
import com.example.user.mymusicplayer.Model.Song;
import com.example.user.mymusicplayer.R;

import java.util.Objects;

public class MPService extends Service {
    MediaPlayer mediaPlayer = new MediaPlayer();
    Notification notification;
    Song song;

    public MPService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();
        Log.d(Logger.getTAG(this), "onStartCommand: " + action);
        switch (action) {
            case Constants.ACTION.PLAY:
                song = intent.getParcelableExtra(Constants.SongTAG);
                Play();
                break;
            case Constants.ACTION.PAUSE:
                Pause();
                break;
            case Constants.ACTION.STOP:
                onDestroy();
                break;
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void Pause() {
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        } else {
            mediaPlayer.start();
        }
    }

    private void Play() {
        //region Media Player
        mediaPlayer.stop();
        mediaPlayer = MediaPlayer.create(getApplicationContext(), song.getRawId());
        mediaPlayer.start();
        //endregion

        //region Stop Intent
        Intent intentStop = new Intent(getApplicationContext(), MPService.class);
        intentStop.setAction(Constants.ACTION.STOP);
        PendingIntent pendingStop =
                PendingIntent.getService(getApplicationContext(), 0, intentStop, PendingIntent.FLAG_CANCEL_CURRENT);
        //endregion

        //region Pause Intent
        Intent intentPause = new Intent(getApplicationContext(), MPService.class);
        intentPause.putExtra(Constants.SongTAG, song);
        intentPause.setAction(Constants.ACTION.PAUSE);
        PendingIntent pendingPause =
                PendingIntent.getService(getApplicationContext(), 0, intentPause, PendingIntent.FLAG_CANCEL_CURRENT);
        //endregion

        //region Create notification
        notification = new NotificationCompat.Builder(getApplicationContext(), Constants.ChannelId)
                // Show controls on lock screen even when user hides sensitive content.
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setSmallIcon(R.drawable.ic_player_play)
                .addAction(R.drawable.ic_player_play, "Pause", pendingPause) // #0
                .addAction(R.drawable.ic_player_stop, "Stop", pendingStop) // #0
                .setContentTitle(song.getTitle())
                .setContentText(song.getArtist())
                .build();
        //endregion

        startForeground(1, notification);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        stopSelf();
        super.onDestroy();
    }
}
