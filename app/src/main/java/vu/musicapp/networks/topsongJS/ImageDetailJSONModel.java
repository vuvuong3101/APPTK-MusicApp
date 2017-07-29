package vu.musicapp.networks.topsongJS;

/**
 * Created by apple on 7/21/17.
 */

public class ImageDetailJSONModel {
    private String label;
    private AttributesJSONModel attributes;

    public ImageDetailJSONModel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public AttributesJSONModel getAttributes() {
        return attributes;
    }

    public void setAttributes(AttributesJSONModel attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "ImageDetailJSONModel{" +
                "label='" + label + '\'' +
                ", attributes=" + attributes +
                '}';
    }
}


