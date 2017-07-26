package vu.musicapp.events;

import vu.musicapp.models.MusicModel;

/**
 * Created by mac-vuongvu on 7/22/17.
 */

public class OnclickMusicType {

    private MusicModel musicModel;

    public OnclickMusicType(MusicModel musicModel) {
        this.musicModel = musicModel;
    }

    public MusicModel getMusicModel() {
        return musicModel;
    }

    public void setMusicModel(MusicModel musicModel) {
        this.musicModel = musicModel;
    }
}
