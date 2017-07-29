package vu.musicapp.networks.topsongJS;

import java.util.List;

/**
 * Created by apple on 7/21/17.
 */

public class FeedJSONModel {
    private List<SongInfoJSONModel> entry;

    public FeedJSONModel(List<SongInfoJSONModel> entry) {
        this.entry = entry;
    }

    public List<SongInfoJSONModel> getEntry() {
        return entry;
    }

    public void setEntry(List<SongInfoJSONModel> entry) {
        this.entry = entry;
    }

    @Override
    public String toString() {
        return "FeedJSONModel{" +
                "entry=" + entry +
                '}';
    }
}
