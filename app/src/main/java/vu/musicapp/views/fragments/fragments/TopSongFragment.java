package vu.musicapp.views.fragments.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vu.musicapp.R;
import vu.musicapp.adapters.TopSongsAdapter;
import vu.musicapp.manager.ScreenManager;
import vu.musicapp.models.MusicModel;
import vu.musicapp.models.TopSongModel;
import vu.musicapp.networks.GetTopSong;
import vu.musicapp.networks.RetrofitFactory;
import vu.musicapp.networks.topsongJS.AllSongsJSONModel;
import vu.musicapp.networks.topsongJS.SongInfoJSONModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopSongFragment extends Fragment {
    @BindView(R.id.rv_top_song)
    RecyclerView rvTopSongs;
    @BindView(R.id.iv_music_type)
    ImageView iv_thumbail;
    @BindView(R.id.title_top_song)
    TextView tvTopSong;
    TopSongsAdapter topSongsAdapter;

    private List<TopSongModel> topSongModelList = new ArrayList<>();
    private  TopSongModel topSongModel;
    public TopSongFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_song, container, false);

        setupUI(view);
        loadData(view);
        return view;
    }



    private void loadData(View view){
        final GetTopSong getTopSongs = RetrofitFactory.getInstance().create(GetTopSong.class);
        final MusicModel musicTypeModel = (MusicModel) view.getTag();
        getTopSongs.getTopSongs(ScreenManager.musicModel.getId()).enqueue(new Callback<AllSongsJSONModel>() {
            public  final String TAG = TopSongFragment.class.toString();

            @Override
            public void onResponse(@NonNull Call<AllSongsJSONModel> call, @NonNull Response<AllSongsJSONModel> response) {
                List<SongInfoJSONModel> listSongInfo;
                listSongInfo = response.body().getFeed().getEntry();
                for(int i = 0; i < listSongInfo.size(); i++){
                    String x = listSongInfo.get(i).getName().getLabel();
                    String y = listSongInfo.get(i).getArtist().getLabel();
                    String z = listSongInfo.get(i).getImage().toString();


                    Log.d(TAG, "onResponse: " + x + "---" +y + "---" + z);
                    TopSongModel topSongModel = new TopSongModel();
                    topSongModel.setNameSong(listSongInfo.get(i).getName().getLabel());
                    topSongModel.setArtistSong(listSongInfo.get(i).getArtist().getLabel());
                    Log.d(TAG, "onResponse: " + topSongModel.getNameSong());
                    topSongModelList.add(topSongModel);
                }
            }

            @Override
            public void onFailure(Call<AllSongsJSONModel> call, Throwable t) {

            }
        });
    }

    private void setupUI(View view) {

    }
}
