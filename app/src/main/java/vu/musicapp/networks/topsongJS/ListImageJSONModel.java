package vu.musicapp.networks.topsongJS;

import java.util.List;

/**
 * Created by apple on 7/21/17.
 */

public class ListImageJSONModel {
    private List<ImageDetailJSONModel> listImages;

    public ListImageJSONModel(List<ImageDetailJSONModel> listImages) {
        this.listImages = listImages;
    }

    public List<ImageDetailJSONModel> getListImages() {
        return listImages;
    }

    public void setListImages(List<ImageDetailJSONModel> listImages) {
        this.listImages = listImages;
    }

    @Override
    public String toString() {
        return "ListImageJSONModel{" +
                "listImages=" + listImages +
                '}';
    }
}
