package com.android.davidgj.cajeroautomaticose.codigopin;


import com.android.davidgj.cajeroautomaticose.codigopin.events.LoginPinEvent;

/**
 * Created by DaviDGJ on 23/3/2017.
 */
public interface LoginPinPresenter {

    void onDestroy();
    void onCreate();
    void validateCodPinAndCodChip(int codPin, int codChip);

    void onEventMainThread(LoginPinEvent event);
}
