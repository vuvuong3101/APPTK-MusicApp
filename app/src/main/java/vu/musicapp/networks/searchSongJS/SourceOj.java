package vu.musicapp.networks.searchSongJS;

import com.google.gson.annotations.SerializedName;

/**
 * Created by mac-vuongvu on 7/22/17.
 */

public  class SourceOj {
    @SerializedName("128")
    private String linkSource;

    public String getLinkSource() {
        return linkSource;
    }

    public void setLinkSource(String linkSource) {
        this.linkSource = linkSource;
    }

    public SourceOj(String linkSource) {
        this.linkSource = linkSource;
    }
}
