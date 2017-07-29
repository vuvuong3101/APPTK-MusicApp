package vu.musicapp.networks.musicJS;

import java.util.List;

/**
 * Created by mac-vuongvu on 7/15/17.
 */

public class AllMusicTypeJSModel {
    private  List<MusicTypeJSModel> subgenres;

    public AllMusicTypeJSModel(List<MusicTypeJSModel> subgenres) {
        this.subgenres = subgenres;
    }

    public List<MusicTypeJSModel> getSubgenres() {
        return subgenres;
    }

    public void setSubgenres(List<MusicTypeJSModel> subgenres) {
        this.subgenres = subgenres;
    }
}
