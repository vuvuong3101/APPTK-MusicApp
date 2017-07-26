package vu.musicapp.networks.topsongJS;

/**
 * Created by apple on 7/21/17.
 */

public class AttributesJSONModel {
    private String height;

    public AttributesJSONModel(String height) {
        this.height = height;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "AttributesJSONModel{" +
                "height='" + height + '\'' +
                '}';
    }
}


