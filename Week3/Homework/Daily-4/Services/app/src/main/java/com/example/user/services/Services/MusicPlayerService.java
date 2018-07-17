package com.example.user.services.Services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.user.services.Code.Constants;
import com.example.user.services.Entity.Song;
import com.example.user.services.MainActivity;
import com.example.user.services.R;

public class MusicPlayerService extends Service {
    private static final int NOTIFICATION_ID = 1;
    private final String TAG = MusicPlayerService.class.getSimpleName() + "_TAG";
    public MediaPlayer mediaPlayer;
    Notification status;

    public MusicPlayerService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(TAG, "onStartCommand: " + intent.getAction());
        if (intent.getAction().equals(Constants.ACTION.STARTFOREGROUND_ACTION)) {
            Song song = intent.getParcelableExtra("Song");
            showNotification(song);
            Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();

        } else if (intent.getAction().equals(Constants.ACTION.PREV_ACTION)) {
            Toast.makeText(this, "Clicked Previous", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Clicked Previous");
        } else if (intent.getAction().equals(Constants.ACTION.PLAY_ACTION)) {
            Toast.makeText(this, "Clicked Play", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Clicked Play");
        } else if (intent.getAction().equals(Constants.ACTION.NEXT_ACTION)) {
            Toast.makeText(this, "Clicked Next", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Clicked Next");
        } else if (intent.getAction().equals(
                Constants.ACTION.STOPFOREGROUND_ACTION)) {
            Log.d(TAG, "Received Stop Foreground Intent");
            Toast.makeText(this, "Service Stoped", Toast.LENGTH_SHORT).show();
            stopForeground(true);
            stopSelf();
        }
        return START_STICKY;
    }

    private void showNotification(Song song) {
        //region Play Music
        mediaPlayer = MediaPlayer.create(getApplicationContext(), song.getRawId());
        mediaPlayer.start();
        //endregion

        //region Using RemoteViews to bind custom layouts into Notification
        RemoteViews views = new RemoteViews(getPackageName(),
                R.layout.status_bar);
        RemoteViews bigViews = new RemoteViews(getPackageName(),
                R.layout.status_bar_expanded);
        //endregion

        //region showing default album image
        views.setViewVisibility(R.id.status_bar_icon, View.VISIBLE);
        views.setViewVisibility(R.id.status_bar_album_art, View.GONE);
        /*bigViews.setImageViewBitmap(R.id.status_bar_album_art,
                Constants.getDefaultAlbumArt(this));*/
        bigViews.setImageViewResource(R.id.status_bar_album_art, song.getRawId());
        //endregion

        //region notificationIntent
        Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.setAction(Constants.ACTION.MAIN_ACTION);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);
        //endregion

        //region Previous Intent
        Intent previousIntent = new Intent(this, NotificationService.class);
        previousIntent.setAction(Constants.ACTION.PREV_ACTION);
        PendingIntent ppreviousIntent = PendingIntent.getService(this, 0,
                previousIntent, 0);
        //endregion

        //region Play Intent
        Intent playIntent = new Intent(this, NotificationService.class);
        playIntent.setAction(Constants.ACTION.PLAY_ACTION);
        PendingIntent pplayIntent = PendingIntent.getService(this, 0,
                playIntent, 0);
        //endregion

        //region Next Intent
        Intent nextIntent = new Intent(this, NotificationService.class);
        nextIntent.setAction(Constants.ACTION.NEXT_ACTION);
        PendingIntent pnextIntent = PendingIntent.getService(this, 0,
                nextIntent, 0);
        //endregion

        //region Close Intent
        Intent closeIntent = new Intent(this, NotificationService.class);
        closeIntent.setAction(Constants.ACTION.STOPFOREGROUND_ACTION);
        PendingIntent pcloseIntent = PendingIntent.getService(this, 0,
                closeIntent, 0);
        //endregion

        //region Set on click Listener
//        views.setOnClickPendingIntent(R.id.status_bar_play, pplayIntent);
//        bigViews.setOnClickPendingIntent(R.id.status_bar_play, pplayIntent);
//
//        views.setOnClickPendingIntent(R.id.status_bar_next, pnextIntent);
//        bigViews.setOnClickPendingIntent(R.id.status_bar_next, pnextIntent);
//
//        views.setOnClickPendingIntent(R.id.status_bar_prev, ppreviousIntent);
//        bigViews.setOnClickPendingIntent(R.id.status_bar_prev, ppreviousIntent);
//
//        views.setOnClickPendingIntent(R.id.status_bar_collapse, pcloseIntent);
//        bigViews.setOnClickPendingIntent(R.id.status_bar_collapse, pcloseIntent);
        //endregion

        //region set pause image
        views.setImageViewResource(R.id.status_bar_play, R.drawable.ic_playe_pause);
        bigViews.setImageViewResource(R.id.status_bar_play, R.drawable.ic_playe_pause);
        //endregion

        //region set Player Values from Song
        views.setTextViewText(R.id.status_bar_track_name, song.getTitle());
        bigViews.setTextViewText(R.id.status_bar_track_name, song.getTitle());

        views.setTextViewText(R.id.status_bar_artist_name, song.getArtist());
        bigViews.setTextViewText(R.id.status_bar_artist_name, song.getArtist());

        bigViews.setTextViewText(R.id.status_bar_album_name, song.getAlbum());
        //endregion

        //region Builder config
        status = new Notification.Builder(this).build();
        status.contentView = views;
        status.bigContentView = bigViews;
        status.flags = Notification.FLAG_ONGOING_EVENT;
        status.icon = R.drawable.ic_player_play;
        status.contentIntent = pendingIntent;
        //endregion

        startForeground(Constants.NOTIFICATION_ID.FOREGROUND_SERVICE, status);
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
