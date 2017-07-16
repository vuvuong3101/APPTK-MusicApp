package vu.musicapp.views.fragments.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TableLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vu.musicapp.R;
import vu.musicapp.networks.GetMusicType;
import vu.musicapp.networks.RetrofitFactory;
import vu.musicapp.networks.jsonmodel.AllMusicTypeJSModel;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.toString();
    GetMusicType getMusicType;
    private ViewPager viewPager;
    private TableLayout tableLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         getMusicType    = RetrofitFactory.getInstance().create(GetMusicType.class);

        getMusicType.getMusicType().enqueue(new Callback<AllMusicTypeJSModel>() {
            @Override
            public void onResponse(Call<AllMusicTypeJSModel> call, Response<AllMusicTypeJSModel> response) {
                for (int i = 0 ; i< response.body().getSubgenres().size(); i++){
                    String x = response.body().getSubgenres().get(i).getId().toString();
                    String y = response.body().getSubgenres().get(i).getTranslation_key().toString();
                                    Log.d(TAG, "onResponse: "+  x + "  -  " + y);
                }
            }

            @Override
            public void onFailure(Call<AllMusicTypeJSModel> call, Throwable t) {

            }
        });
    }
}
