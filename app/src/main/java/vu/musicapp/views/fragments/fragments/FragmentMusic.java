package vu.musicapp.views.fragments.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vu.musicapp.R;
import vu.musicapp.adapters.MusicAdapter;
import vu.musicapp.events.OnclickMusicType;
import vu.musicapp.manager.ScreenManager;
import vu.musicapp.models.MusicModel;
import vu.musicapp.networks.GetMusicType;
import vu.musicapp.networks.RetrofitFactory;
import vu.musicapp.networks.musicJS.AllMusicTypeJSModel;
import vu.musicapp.networks.musicJS.MusicTypeJSModel;

public class FragmentMusic extends Fragment implements View.OnClickListener {
    public  final String TAG = FragmentMusic.class.toString();
    @BindView(R.id.rv_music)

    RecyclerView recyclerViewMusic;
    private List<MusicModel> musicmodelList = new ArrayList<>();
    GetMusicType getMusicType;
    private MusicAdapter musicAdapter;
    private Toolbar toolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music, container, false);

        setupUI(view);
        loadData();
        // Inflate the layout for this fragment
        return view;
    }

    private void loadData() {
          getMusicType = RetrofitFactory.getInstance().create(GetMusicType.class);

        getMusicType.getMusicType().enqueue(new Callback<AllMusicTypeJSModel>() {
            public  final String TAG = FragmentMusic.class.toString();

            @Override
            public void onResponse(@NonNull Call<AllMusicTypeJSModel> call, @NonNull Response<AllMusicTypeJSModel> response) {
                for (int i = 0 ; i< response.body().getSubgenres().size(); i++){
                    String x = response.body().getSubgenres().get(i).getId();
                    String y = response.body().getSubgenres().get(i).getTranslation_key();
                    Log.d(TAG, "onResponse: "+  x + "  -  " + y);


                }

                for (MusicTypeJSModel musicJSmodel: response.body().getSubgenres()) {
                    MusicModel musicmodel = new MusicModel();
                    musicmodel.setId(musicJSmodel.getId());
                    musicmodel.setKey(musicJSmodel.getTranslation_key());
                    musicmodel.setImageID(getContext().getResources().getIdentifier("genre_x2_" + musicJSmodel.getId(),
                            "raw",
                            getContext().getPackageName()));
                    musicmodelList.add(musicmodel);
                }
                musicAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<AllMusicTypeJSModel> call, Throwable t) {
                Toast.makeText(getContext(), "no connection" , Toast.LENGTH_LONG).show();
                Log.d(TAG, "onFailure: " );
            }
        });
    }

    private  void setupUI(View v) {
        ButterKnife.bind(this, v);
         musicAdapter = new MusicAdapter(musicmodelList, getContext());
        recyclerViewMusic.setAdapter(musicAdapter);
        GridLayoutManager layoutMG =  new GridLayoutManager((getContext()), 2 , GridLayoutManager.VERTICAL, false);
        layoutMG.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position % 3 == 0 ? 2: 1;
            }
        });

        recyclerViewMusic.setLayoutManager(layoutMG);
        musicAdapter.setOnclikListener(this);
    }

    @Override
    public void onClick(View view) {
        MusicModel musicModel = (MusicModel) view.getTag();

        // dung eventbus

        EventBus.getDefault().postSticky(new OnclickMusicType(musicModel)); // send musicmodel sang topsong
//        Log.d(TAG, "onClick: " + musicModel);
        TopSongFragment fragment = new TopSongFragment();
        ScreenManager.OpenFragment(getActivity().getSupportFragmentManager(), fragment, R.id.container_main);
    }
}