package com.example.user.navigationdrawer1;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class musicFragment extends Fragment {
    static MediaPlayer mediaPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.music, container, false);

        mediaPlayer = MediaPlayer.create(v.getContext(), R.raw.m_becoming_insane);

        Button btnPlay = (Button)v.findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                } else {
                    mediaPlayer.start();
                }
            }
        });

        return v;
    }
}
