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

public class MySongsService extends IntentService {
    public MySongsService() {
        super("MySongsService");
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
}
