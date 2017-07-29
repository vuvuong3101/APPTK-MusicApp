package vu.musicapp.models;

/**
 * Created by mac-vuongvu on 7/21/17.
 */

public class TopSongModel {
    private String name;
    private String image;
    private String artist;
    private String linkSource;

    public TopSongModel(String name, String image, String artist, String linkSource) {
        this.name = name;
        this.image = image;
        this.artist = artist;
        this.linkSource = linkSource;
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

    public String getLinkSource() {
        return linkSource;
    }

    public void setLinkSource(String linkSource) {
        this.linkSource = linkSource;
    }
}
