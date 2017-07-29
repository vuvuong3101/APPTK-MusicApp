package vu.musicapp.manager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import vu.musicapp.models.MusicModel;

/**
 * Created by mac-vuongvu on 7/19/17.
 */

public  class ScreenManager {
    public static MusicModel musicModel;
    public  static void OpenFragment(FragmentManager fragmentManager, Fragment fragment, int layoutID) {
//        musicModel = model;
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        fragmentTransaction.add(layoutID, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
