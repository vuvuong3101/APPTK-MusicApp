package vu.musicapp.networks;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import vu.musicapp.networks.searchSongJS.SearchMain;

/**
 * Created by mac-vuongvu on 7/22/17.
 */

public interface GetSearchSong {
    @GET("http://api.mp3.zing.vn/api/mobile/search/song")
    Call<SearchMain> getSearchSong(@Query("requestdata") String request);
}
