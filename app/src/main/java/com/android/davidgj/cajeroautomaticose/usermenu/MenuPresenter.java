package com.android.davidgj.cajeroautomaticose.usermenu;


import com.android.davidgj.cajeroautomaticose.usermenu.events.MenuEvent;

/**
 * Created by DaviDGJ on 27/3/2017.
 */

public interface MenuPresenter {

    void onDestroy();
    void onCreate();

    void seeBalance(int codChip);
    //ver extracto
    void seeExtract(int codChip);

    void onEventMainThread(MenuEvent event);
}
