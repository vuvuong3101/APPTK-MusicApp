package vu.musicapp.networks.topsongJS;

/**
 * Created by apple on 7/21/17.
 */

public class ArtistAttributesJSONMdel {
    private String href;

    public ArtistAttributesJSONMdel(String href) {
        this.href = href;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String toString() {
        return "ArtistAttributesJSONMdel{" +
                "href='" + href + '\'' +
                '}';
    }
}
