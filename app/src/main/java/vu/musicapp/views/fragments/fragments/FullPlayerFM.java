package vu.musicapp.views.fragments.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import hybridmediaplayer.HybridMediaPlayer;
import vu.musicapp.R;
import vu.musicapp.events.OnClickTopMusic;
import vu.musicapp.manager.MusicManager;
import vu.musicapp.models.TopSongModel;
import vu.musicapp.views.fragments.activities.Main2Activity;

/**
 * A simple {@link Fragment} subclass.
 */
public class FullPlayerFM extends Fragment {
    private static final String TAG = FullPlayerFM.class.toString();
    private TopSongModel topSongModel;
    private ImageView imageMusic, imSong, playpause, close ;
    private TextView tv_nameSong, tv_single,  tvDuration, tvCurent;
    private RelativeLayout viewMain;
    private SeekBar seekBar;
    private FloatingActionButton fab;
    private Main2Activity main2Activity;
    private HybridMediaPlayer hybridMediaPlayer;
    public FullPlayerFM() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_full_player_fm, container, false);
        imageMusic = view.findViewById(R.id.background_image);
        tv_nameSong = view.findViewById(R.id.name_song);
        imSong=  view.findViewById(R.id.image_song);
        tv_single = view.findViewById(R.id.single);
        viewMain = view.findViewById(R.id.playView);
        seekBar =  view.findViewById(R.id.seekBarFull);
        playpause = view.findViewById(R.id.play_pause);
        close = view.findViewById(R.id.close);
        tvDuration = view.findViewById(R.id.tv_durent);
        tvCurent = view.findViewById(R.id.tv_curent);
        ///
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        ///
        playpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MusicManager.playPause();
            }
        });
        ///
        viewMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        EventBus.getDefault().register(this);
        Animation();

        MusicManager.updateTimeSeekBar(seekBar, playpause, tvCurent, tvDuration);

        return view;
    }



    public  void Animation() {
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(15000);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        imSong.startAnimation(rotateAnimation);
    }




    @Subscribe(sticky = true)
    public  void recivedMusic(OnClickTopMusic onClickTopMusic) {
        topSongModel= onClickTopMusic.getTopSongModel();
        Picasso.with(getContext()).load(topSongModel.getImage()).into(imageMusic);
        Picasso.with(getContext()).load(topSongModel.getImage()).into(imSong);

        tv_nameSong.setText(topSongModel.getName());
        tv_single.setText(topSongModel.getArtist());

    }





}
