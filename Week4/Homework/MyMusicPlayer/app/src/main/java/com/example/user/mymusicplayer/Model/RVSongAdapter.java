package com.example.user.mymusicplayer.Model;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.mymusicplayer.R;

import java.util.List;

public class RVSongAdapter extends RecyclerView.Adapter<RVSongAdapter.ViewHolder> {
    public interface OnItemClickListener {
        void onItemClick(Song item);
    }

    List<Song> Songs;
    private final OnItemClickListener listener;
    public static final String TAG = RVSongAdapter.class.getSimpleName() + "_TAG";

    public RVSongAdapter(List<Song> Songs, OnItemClickListener listener) {
        this.Songs = Songs;
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        Song Song = Songs.get(position);
        //region change layout
        /*if (Song.getISBN().equals("Song-3")) {
            return 1;
        } else {
            return 0;
        }*/
        //endregion
        return 1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int currentLayout;

        currentLayout = R.layout.song_list_item;

        //region Change layout
        /*if (viewType == 1) {
            currentLayout = R.layout.Song_list_item2;
        } else {
            currentLayout = R.layout.Song_list_item;
        }*/
        //endregion

        View view = LayoutInflater.from(parent.getContext()).inflate(currentLayout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = Songs.get(position);
        holder.imgAlbum.setBackgroundResource(song.getAlbumnPicture());
        holder.imgAlbum.setContentDescription(song.getDetail());
        holder.tvTitle.setText(song.getTitle());
        holder.tvArtist.setText(song.getArtist());
        holder.tvAlbum.setText(song.getAlbum());

        holder.bind(song, listener);
    }

    @Override
    public int getItemCount() {
        return Songs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imgAlbum;
        private final TextView tvTitle;
        private final TextView tvArtist;
        private final TextView tvAlbum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //region initialize the views
            imgAlbum = itemView.findViewById(R.id.imgAlbum);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvArtist = itemView.findViewById(R.id.tvArtist);
            tvAlbum = itemView.findViewById(R.id.tvAlbum);
            //endregion
        }

        public void bind(final Song song, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(song);
                }
            });
        }
    }
}
