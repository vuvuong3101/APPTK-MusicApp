package vu.musicapp.networks.topsongJS;

/**
 * Created by apple on 7/21/17.
 */

public class AllSongsJSONModel {
    private FeedJSONModel feed;

    public AllSongsJSONModel(FeedJSONModel feed) {
        this.feed = feed;
    }

    public FeedJSONModel getFeed() {
        return feed;
    }

    public void setFeed(FeedJSONModel feed) {
        this.feed = feed;
    }

    @Override
    public String toString() {
        return "AllSongsJSONModel{" +
                "feed=" + feed +
                '}';
    }
}
