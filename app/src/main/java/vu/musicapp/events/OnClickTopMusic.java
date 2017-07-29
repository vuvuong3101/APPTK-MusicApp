package vu.musicapp.events;

import vu.musicapp.models.TopSongModel;

/**
 * Created by mac-vuongvu on 7/25/17.
 */

public class OnClickTopMusic {
    private TopSongModel topSongModel;

    public OnClickTopMusic(TopSongModel topSongModel) {
        this.topSongModel = topSongModel;
    }

    public TopSongModel getTopSongModel() {
        return topSongModel;
    }

    public void setTopSongModel(TopSongModel topSongModel) {
        this.topSongModel = topSongModel;
    }
}
