package vu.musicapp.networks;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import vu.musicapp.networks.topsongJS.AllSongsJSONModel;

/**
 * Created by mac-vuongvu on 7/21/17.
 */

public interface GetTopSong {
    @GET("https://itunes.apple.com/us/rss/topsongs/limit=50/genre={id}/explicit=true/json")
    Call<AllSongsJSONModel> getTopSongs(@Path("id") String id);
}
