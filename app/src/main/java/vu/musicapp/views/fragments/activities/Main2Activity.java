package vu.musicapp.views.fragments.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import vu.musicapp.R;
import vu.musicapp.events.OnClickTopMusic;
import vu.musicapp.models.TopSongModel;
import vu.musicapp.views.fragments.fragments.FragmentDownLoad;
import vu.musicapp.views.fragments.fragments.FragmentFav;
import vu.musicapp.views.fragments.fragments.FragmentMusic;

public class Main2Activity extends AppCompatActivity {
    private static final String TAG = Main2Activity.class.toString();
    private TabLayout tabLayout;
    private ViewPagerAdapter mViewPagerAdapter;
    private ViewPager mViewPager;
    private int[] tabIcons = {
            R.drawable.ic_home,
            R.drawable.ic_fav,
            R.drawable.ic_download
    };

    private TopSongModel topSongModel;
    private RelativeLayout mini_player;
    private CircleImageView ivMinipalyer;
    private TextView tvNameSong, tvArtist;

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
//
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mViewPager = (ViewPager) findViewById(R.id.container);

        //
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        //
        EventBus.getDefault().register(this);
        mini_player = (RelativeLayout) findViewById(R.id.mini_player);
        FindViewID();
        setupViewPager(mViewPager);
        setupTabIcons();
        setupMinuPlayer();
    }

    public void FindViewID() {
        ivMinipalyer = (CircleImageView) findViewById(R.id.album_art);
        tvNameSong = (TextView) findViewById(R.id.title_mini);
        tvArtist = (TextView) findViewById(R.id.artist_mini);
    }


    @Subscribe
    public  void onRecivedMusic (OnClickTopMusic onClickTopMusic){
        topSongModel = onClickTopMusic.getTopSongModel();
        mini_player.setVisibility(View.VISIBLE);
        tvNameSong.setText(topSongModel.getName());
        tvArtist.setText(topSongModel.getArtist());
        Picasso.with(getBaseContext()).load(topSongModel.getImage()).into(ivMinipalyer);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentMusic());
        adapter.addFragment(new FragmentFav());
        adapter.addFragment(new FragmentDownLoad());
        viewPager.setAdapter(adapter);
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }

    public void setupMinuPlayer() {

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragmentList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }


        public void addFragment(Fragment fragment) {
            fragmentList.add(fragment);
            }
        }
        public  void addBackstack(Fragment fragment, String nameFM) {
            getSupportFragmentManager().beginTransaction().addToBackStack("").commit();
        }

}
