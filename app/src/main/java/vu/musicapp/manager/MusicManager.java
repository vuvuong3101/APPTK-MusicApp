package vu.musicapp.manager;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import hybridmediaplayer.HybridMediaPlayer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vu.musicapp.R;
import vu.musicapp.models.TopSongModel;
import vu.musicapp.networks.GetSearchSong;
import vu.musicapp.networks.RetrofitFactory;
import vu.musicapp.networks.searchSongJS.DocsObject;
import vu.musicapp.networks.searchSongJS.SearchMain;
import vu.musicapp.utils.FuzzyMatch;
import vu.musicapp.utils.Utils;
import vu.musicapp.views.fragments.fragments.TopSongFragment;

/**
 * Created by mac-vuongvu on 7/22/17.
 */

public class MusicManager {
    public static HybridMediaPlayer hybridMediaPlayer;
    private  static  SeekBar seekBar;
    private static ImageView btnPlaypause;
    public  static  void loadSearchSong(final TopSongModel topSongModel, final Context context, SeekBar seekbar, ImageView btnPlayPause1) {
        MusicManager.seekBar = seekbar;
        MusicManager.btnPlaypause  = btnPlayPause1;

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
                                    setupMusic(topSongModel, context);
                                    
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

    public static  void setupMusic(final TopSongModel topSongModel , Context context) {
        if (hybridMediaPlayer != null) {
            hybridMediaPlayer.release();
        }
        hybridMediaPlayer = HybridMediaPlayer.getInstance(context);
        hybridMediaPlayer.setDataSource(topSongModel.getLinkSource());
        hybridMediaPlayer.prepare();
        hybridMediaPlayer.setOnPreparedListener(new HybridMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(HybridMediaPlayer hybridMediaPlayer) {
                hybridMediaPlayer.play();
                updateTimeSeekBar( seekBar, MusicManager.btnPlaypause, null, null);
            }
        });
    }


    public  static  void playPause() {
        if (hybridMediaPlayer.isPlaying()) {
            hybridMediaPlayer.pause();
        }else {
            hybridMediaPlayer.play();
        }
    }




    public  static  void updateTimeSeekBar(final SeekBar seekBarMini, final ImageView btnPlayPause,
                                           final TextView tvCurrent,
                                           final TextView tvDuration) {
        final Handler handler = new Handler();
        Runnable runable = new Runnable() {
            @Override
            public void run() {
                seekBarMini.setMax(hybridMediaPlayer.getDuration()); // set time max cua seekbar
                seekBarMini.setProgress(hybridMediaPlayer.getCurrentPosition()); //  set vi tri seekbar
                if (hybridMediaPlayer.isPlaying()) {
                    btnPlayPause.setImageResource(R.drawable.ic_play_circle_outline_black_24dp);
                }
                else  {
                    btnPlayPause.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp);
                }

                if (tvCurrent != null && tvDuration != null) {
                    tvDuration.setText(Utils.convertTime(hybridMediaPlayer.getDuration()));
                    tvCurrent.setText(Utils.convertTime(hybridMediaPlayer.getCurrentPosition()));
                }
                handler.postDelayed(this, 100);
            }
        };
        runable.run();


        ///////
        final  int [] position = new int[1];
        seekBarMini.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekBar.setProgress(i);
                position[0] = i;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                    hybridMediaPlayer.seekTo(position[0]);
                    seekBar.setProgress(position[0]);



            }
        });
    }
}
