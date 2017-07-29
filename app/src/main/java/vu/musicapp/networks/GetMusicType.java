package vu.musicapp.networks;

import retrofit2.Call;
import retrofit2.http.GET;
import vu.musicapp.networks.musicJS.AllMusicTypeJSModel;

/**
 * Created by mac-vuongvu on 7/15/17.
 */

public interface GetMusicType {
    @GET("api")
    Call<AllMusicTypeJSModel> getMusicType();
}
