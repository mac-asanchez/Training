package com.example.user.mymusicplayer.Services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import com.example.user.mymusicplayer.Model.Constants;
import com.example.user.mymusicplayer.Model.Song;
import com.example.user.mymusicplayer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MySongsService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "com.example.user.mymusicplayer.Services.action.FOO";
    private static final String ACTION_BAZ = "com.example.user.mymusicplayer.Services.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.example.user.mymusicplayer.Services.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.example.user.mymusicplayer.Services.extra.PARAM2";

    public MySongsService() {
        super("MySongsService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MySongsService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MySongsService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        List<Song> MySongs = new ArrayList<>();

        MySongs.add(new Song(1, "One Kiss", "Calvin Harris & Dua Lipa",
                "Single", R.drawable.one_kiss,
                "\"One Kiss\" is a song by Scottish record producer Calvin Harris and English singer Dua Lipa.",
                R.raw.one_kiss_calvin_harris));

        MySongs.add(new Song(2, "Me Niego", "Reik ft. Ozuna, Wisin",
                "Single", R.drawable.me_niego,
                "\"Me niego\" (\"I refuse\") is a song by Mexican band Reik featuring Puerto Rican singers Ozuna and Wisin.",
                R.raw.me_niego_reik));

        MySongs.add(new Song(3, "To My Love", "Bomba Estéreo",
                "Single", R.drawable.to_my_love,
                "\"To My Love\" is a Song by Bomba Estéreo.",
                R.raw.to_my_love_bomba_estereo));

        MySongs.add(new Song(4, "Bella", "Wolfine",
                "Single", R.drawable.bella,
                "\"Bella\" single that has more than 400 million views on the Youtube channel of the company that manages the artist.",
                R.raw.bella_wolfine));

        /*Intent*/ intent = new Intent(Constants.ACTION.PASS_SONGS);
        intent.putParcelableArrayListExtra(Constants.SongsTag, (ArrayList<? extends Parcelable>) MySongs);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
