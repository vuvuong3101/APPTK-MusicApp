package vu.musicapp.manager;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vu.musicapp.models.TopSongModel;
import vu.musicapp.networks.GetSearchSong;
import vu.musicapp.networks.RetrofitFactory;
import vu.musicapp.networks.searchSongJS.DocsObject;
import vu.musicapp.networks.searchSongJS.SearchMain;
import vu.musicapp.utils.FuzzyMatch;
import vu.musicapp.views.fragments.fragments.TopSongFragment;

/**
 * Created by mac-vuongvu on 7/22/17.
 */

public class MusicManager {
    public  static  void loadSearchSong(final TopSongModel topSongModel, final Context context) {
        GetSearchSong getSearchSong = RetrofitFactory.getInstance().create(GetSearchSong.class);
        getSearchSong.getSearchSong("{\"q\":\" "+ topSongModel.getName() + "" + topSongModel.getArtist()+ "\", \"sort\":\"hot\", \"start\":\"0\", \"length\":\"10\"}")
                .enqueue(new Callback<SearchMain>() {
                    public  final String TAG = TopSongFragment.class.toString();

                    @Override
                    public void onResponse(Call<SearchMain> call, Response<SearchMain> response) {
                        if (response.body().getDocs().size() > 0) {

                            // list ratio
                            List<Integer> ratioList =  new ArrayList<>();

                            for (DocsObject docsObject : response.body().getDocs()){
                                int ratio = FuzzyMatch.getRatio(topSongModel.getName() + " "+ topSongModel.getArtist() +" "
                                        , docsObject.getTitle() + " " + docsObject.getArtist(),false);
                                ratioList.add(ratio);
                            }
                            // get max
                            for (int j = 0; j < ratioList.size(); j ++) {
                                if (Collections.max(ratioList) ==  ratioList.get(j)) {
                                    String linkSource =  response.body().getDocs().get(j).getSource().getLinkSource();
                                    topSongModel.setLinkSource(linkSource);
                                    Log.d(TAG, "onResponse: " +  linkSource);
                                    
                                }
                            }
                        }else  {
                            Toast.makeText(context, "Not Found", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<SearchMain> call, Throwable t) {
                        Toast.makeText(context, "Not Found", Toast.LENGTH_LONG).show();

                    }
                });


    }
}
