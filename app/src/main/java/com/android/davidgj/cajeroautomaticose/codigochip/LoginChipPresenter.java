package com.android.davidgj.cajeroautomaticose.codigochip;


import com.android.davidgj.cajeroautomaticose.codigochip.events.LoginChipEvent;

/**
 * Created by DaviDGJ on 23/3/2017.
 */
public interface LoginChipPresenter {

    void onDestroy();
    void onCreate();
    void validateCodChip(int codChip);

    void onEventMainThred(LoginChipEvent event);


}
