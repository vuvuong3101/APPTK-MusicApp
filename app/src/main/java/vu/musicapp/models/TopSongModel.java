package vu.musicapp.models;

/**
 * Created by mac-vuongvu on 7/21/17.
 */

public class TopSongModel {
    private String name;
    private String image;
    private String artist;
    private String nameSong;
    private String artistSong;
    private String imageSong;

    public TopSongModel(String name, String image, String artist, String nameSong, String artistSong, String imageSong) {
        this.name = name;
        this.image = image;
        this.artist = artist;
        this.nameSong = nameSong;
        this.artistSong = artistSong;
        this.imageSong = imageSong;
    }

    public TopSongModel() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getNameSong() {
        return nameSong;
    }

    public void setNameSong(String nameSong) {
        this.nameSong = nameSong;
    }

    public String getArtistSong() {
        return artistSong;
    }

    public void setArtistSong(String artistSong) {
        this.artistSong = artistSong;
    }

    public String getImageSong() {
        return imageSong;
    }

    public void setImageSong(String imageSong) {
        this.imageSong = imageSong;
    }
}
