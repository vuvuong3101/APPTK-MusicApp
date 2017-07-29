package vu.musicapp.networks.searchSongJS;

import java.util.List;

/**
 * Created by mac-vuongvu on 7/22/17.
 */

public class SearchMain {
    private List<DocsObject> docs;

    public SearchMain(List<DocsObject> docs) {
        this.docs = docs;
    }

    public List<DocsObject> getDocs() {
        return docs;
    }

    public void setDocs(List<DocsObject> docs) {
        this.docs = docs;
    }
}
