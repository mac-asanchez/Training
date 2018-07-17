package com.example.user.mymusicplayer.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Song implements Parcelable {
    int SongId;
    String Title;
    String Artist;
    String Album;
    int AlbumnPicture;
    String Detail;
    int RawId;

    public Song(int songId, String title, String artist, String album, int albumnPicture, String detail, int rawId) {
        SongId = songId;
        Title = title;
        Artist = artist;
        Album = album;
        AlbumnPicture = albumnPicture;
        Detail = detail;
        RawId = rawId;
    }

    protected Song(Parcel in) {
        SongId = in.readInt();
        Title = in.readString();
        Artist = in.readString();
        Album = in.readString();
        AlbumnPicture = in.readInt();
        Detail = in.readString();
        RawId = in.readInt();
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };

    @Override
    public String toString() {
        return "Song{" +
                "SongId=" + SongId +
                ", Title='" + Title + '\'' +
                ", Artist='" + Artist + '\'' +
                ", Album='" + Album + '\'' +
                ", AlbumnPicture=" + AlbumnPicture +
                ", Detail='" + Detail + '\'' +
                ", RawId=" + RawId +
                '}';
    }

    public int getSongId() {
        return SongId;
    }

    public void setSongId(int songId) {
        SongId = songId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getArtist() {
        return Artist;
    }

    public void setArtist(String artist) {
        Artist = artist;
    }

    public String getAlbum() {
        return Album;
    }

    public void setAlbum(String album) {
        Album = album;
    }

    public int getAlbumnPicture() {
        return AlbumnPicture;
    }

    public void setAlbumnPicture(int albumnPicture) {
        AlbumnPicture = albumnPicture;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public int getRawId() {
        return RawId;
    }

    public void setRawId(int rawId) {
        RawId = rawId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(SongId);
        parcel.writeString(Title);
        parcel.writeString(Artist);
        parcel.writeString(Album);
        parcel.writeInt(AlbumnPicture);
        parcel.writeString(Detail);
        parcel.writeInt(RawId);
    }
}

