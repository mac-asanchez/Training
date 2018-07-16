package com.example.user.services.Entity;

import com.example.user.services.R;

import java.util.ArrayList;
import java.util.List;

public class DataCreator {
    public static List<Song> getSongs() {
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

        return MySongs;
    }
}
