package vu.musicapp.models;

/**
 * Created by mac-vuongvu on 7/19/17.
 */

public class MusicModel {
    private String id;
    private String key;
    private int imageID;

    public MusicModel(String id, String key, int imageID) {
        this.id = id;
        this.key = key;
        this.imageID = imageID;
    }

    public MusicModel() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
}
