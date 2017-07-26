package vu.musicapp.networks.topsongJS;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by apple on 7/21/17.
 */

public class SongInfoJSONModel {
    @SerializedName("im:name")
    NameSongJSONModel name;
    @SerializedName("im:image")
    List<ImageDetailJSONModel> image;
    @SerializedName("im:artist")
    ArtistJSONModel artist;

    public SongInfoJSONModel(NameSongJSONModel name, List<ImageDetailJSONModel> image, ArtistJSONModel artist) {
        this.name = name;
        this.image = image;
        this.artist = artist;
    }

    public NameSongJSONModel getName() {
        return name;
    }

    public void setName(NameSongJSONModel name) {
        this.name = name;
    }

    public List<ImageDetailJSONModel> getImage() {
        return image;
    }

    public void setImage(List<ImageDetailJSONModel> image) {
        this.image = image;
    }

    public ArtistJSONModel getArtist() {
        return artist;
    }

    public void setArtist(ArtistJSONModel artist) {
        this.artist = artist;
    }


    @Override
    public String toString() {
        return "SongInfoJSONModel{" +
                "name=" + name +
                ", image=" + image +
                ", artist=" + artist +
                '}';
    }
}
