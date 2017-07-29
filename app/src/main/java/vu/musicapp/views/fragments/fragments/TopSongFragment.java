package vu.musicapp.views.fragments.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vu.musicapp.R;
import vu.musicapp.adapters.TopSongsAdapter;
import vu.musicapp.events.OnClickTopMusic;
import vu.musicapp.events.OnclickMusicType;
import vu.musicapp.manager.MusicManager;
import vu.musicapp.models.MusicModel;
import vu.musicapp.models.TopSongModel;
import vu.musicapp.networks.GetTopSong;
import vu.musicapp.networks.RetrofitFactory;
import vu.musicapp.networks.topsongJS.AllSongsJSONModel;
import vu.musicapp.networks.topsongJS.SongInfoJSONModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopSongFragment extends Fragment implements View.OnClickListener {
    RecyclerView rvTopSongs;
    ImageView iv_thumbail;
//    @BindView(R.id.)
    TextView tvTopSong;
    private  TopSongsAdapter topSongsAdapter;
    private MusicModel musicModel;
    private Toolbar toolbar;
    private List<TopSongModel> topSongModelList = new ArrayList<>();
    private  TopSongModel topSongModel;
    private RelativeLayout mini_player;
    private ImageView ivBack;
    private  SeekBar seekBar;
    private ImageView buttonPlayPause;
    private TextView tvTypeSong, tvCountSong;
    public TopSongFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_song, container, false);
        setupUI(view);
        loadData(view);
        seekBar = getActivity().findViewById(R.id.seekBar_mini);
        mini_player = view.findViewById(R.id.mini_player);
        toolbar = view.findViewById(R.id.toolbar_top_song);
        ivBack = view.findViewById(R.id.ic_back);
        buttonPlayPause = getActivity().findViewById(R.id.btn_miniPlay);
        Back();
        return view;
    }

    public  void Back() {
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    private void loadData(View view){
        final GetTopSong getTopSongs = RetrofitFactory.getInstance().create(GetTopSong.class);
        getTopSongs.getTopSongs(musicModel.getId()).enqueue(new Callback<AllSongsJSONModel>() {
            public  final String TAG = TopSongFragment.class.toString();

            @Override
            public void onResponse(@NonNull Call<AllSongsJSONModel> call, @NonNull Response<AllSongsJSONModel> response) {
                List<SongInfoJSONModel> listSongInfo;
                listSongInfo = response.body().getFeed().getEntry();
                for(int i = 0; i < listSongInfo.size(); i++){
                    String x = listSongInfo.get(i).getName().getLabel();
                    String y = listSongInfo.get(i).getArtist().getLabel();
                    String z = listSongInfo.get(i).getImage().get(2).getLabel();

                            Log.d(TAG, "onResponse: " + x + "---" + y + "---" + z);
                    TopSongModel topSongModel = new TopSongModel();
                    topSongModel.setName(listSongInfo.get(i).getName().getLabel());
                    topSongModel.setArtist(listSongInfo.get(i).getArtist().getLabel());
                    topSongModel.setImage(z);
                    topSongModelList.add(topSongModel);
                }

                topSongsAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<AllSongsJSONModel> call, Throwable t) {

            }
        });
    }

    private void setupUI(View view) {
            /// dki vs event bus de nhan su kien onclick
            EventBus.getDefault().register(this);
            tvTypeSong = view.findViewById(R.id.type_music_title);
            tvTypeSong.setText(musicModel.getKey());
            rvTopSongs = view.findViewById(R.id.rv_top_song);
            iv_thumbail = view.findViewById(R.id.iv_music_type);
            topSongsAdapter = new TopSongsAdapter(topSongModelList, getContext());
            rvTopSongs.setLayoutManager(new LinearLayoutManager(getContext()));
            rvTopSongs.setAdapter(topSongsAdapter);
            DividerItemDecoration diveidor = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
            rvTopSongs.addItemDecoration(diveidor);
            iv_thumbail.setImageResource(musicModel.getImageID());
            /// nhan su kien
            topSongsAdapter.setOnItemClick(this);

    }
    @Subscribe(sticky = true)
    public  void onRecivedMusic (OnclickMusicType onclickMusicType){
        musicModel = onclickMusicType.getMusicModel();
    }


    @Override
    public void onClick(View view) {
        TopSongModel topSongModel = (TopSongModel) view.getTag();
        MusicManager.loadSearchSong(topSongModel, getContext(), seekBar, buttonPlayPause);

        //
        Toast.makeText(getContext(), topSongModel.getName(), Toast.LENGTH_SHORT).show();
        EventBus.getDefault().postSticky(new OnClickTopMusic(topSongModel));
    }
}
