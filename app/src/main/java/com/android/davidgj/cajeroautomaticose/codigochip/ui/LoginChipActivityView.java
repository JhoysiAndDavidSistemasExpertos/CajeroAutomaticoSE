package com.android.davidgj.cajeroautomaticose.codigochip.ui;

/**
 * Created by DaviDGJ on 23/3/2017.
 */
public interface LoginChipActivityView {

    void showTextLoading();
    void hideTextLoading();
    void enableView();
    void disenableView();
    void navigateToPinScreen();

    void setCodChipError(String error);
}
