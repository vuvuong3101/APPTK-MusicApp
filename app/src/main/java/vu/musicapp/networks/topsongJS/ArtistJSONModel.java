package vu.musicapp.networks.topsongJS;

/**
 * Created by apple on 7/21/17.
 */

public class ArtistJSONModel {
    private String label;
    private ArtistAttributesJSONMdel attributes;

    public ArtistJSONModel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "ArtistJSONModel{" +
                "label='" + label + '\'' +
                ", attributes=" + attributes +
                '}';
    }
}
