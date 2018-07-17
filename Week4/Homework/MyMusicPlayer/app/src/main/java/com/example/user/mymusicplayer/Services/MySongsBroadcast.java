package com.example.user.mymusicplayer.Services;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.user.mymusicplayer.Model.Constants;
import com.example.user.mymusicplayer.Model.RVSongAdapter;
import com.example.user.mymusicplayer.Model.Song;
import com.example.user.mymusicplayer.R;

import java.util.List;
import java.util.Objects;

public class MySongsBroadcast extends BroadcastReceiver {
    RecyclerView rvSongs;
    Context ctx;

    public MySongsBroadcast(RecyclerView rvSongs) {
        this.rvSongs = rvSongs;
    }

    @Override
    public void onReceive(final Context context, Intent intent) {
        ctx = context;
        switch (Objects.requireNonNull(intent.getAction())) {
            case Constants.ACTION.PASS_SONGS:
                List<Song> MySongs = intent.getParcelableArrayListExtra(Constants.SongsTag);
                populateRecycleView(MySongs);
                break;
        }
    }

    private void populateRecycleView(List<Song> Songs) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ctx);
        RVSongAdapter adapter = new RVSongAdapter(Songs, getSongListener());
        rvSongs.setLayoutManager(layoutManager);
        rvSongs.setAdapter(adapter);
    }

    @NonNull
    private RVSongAdapter.OnItemClickListener getSongListener() {
        return new RVSongAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Song song) {
                startService(song);
                scheduleNotification(getNotification("Song "+ song.getTitle()), 5000);
            }
        };
    }

    private void scheduleNotification(Notification notification, int delay) {
        Toast.makeText(ctx, "scheduleNotification", Toast.LENGTH_SHORT).show();

        Intent notificationIntent = new Intent(ctx, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(ctx, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager)ctx.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }

    private Notification getNotification(String content) {
        Notification.Builder builder = new Notification.Builder(ctx);
        builder.setContentTitle("Scheduled Notification");
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.ic_player_play);
        return builder.build();
    }

    private void startService(Song song) {
        Intent musicPlayerServiceIntent = new Intent(ctx, MPService.class);
        musicPlayerServiceIntent.setAction(Constants.ACTION.PLAY);
        musicPlayerServiceIntent.putExtra("Song", song);
        ctx.startService(musicPlayerServiceIntent);
    }
}
