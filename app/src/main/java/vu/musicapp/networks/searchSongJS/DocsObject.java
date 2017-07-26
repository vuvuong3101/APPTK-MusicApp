package vu.musicapp.networks.searchSongJS;

/**
 * Created by mac-vuongvu on 7/22/17.
 */

public class DocsObject {
    private String title;
    private String artist;
    private SourceOj source;

    public DocsObject(String title, String artist, SourceOj source) {
        this.title = title;
        this.artist = artist;
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public SourceOj getSource() {
        return source;
    }

    public void setSource(SourceOj source) {
        this.source = source;
    }
}
