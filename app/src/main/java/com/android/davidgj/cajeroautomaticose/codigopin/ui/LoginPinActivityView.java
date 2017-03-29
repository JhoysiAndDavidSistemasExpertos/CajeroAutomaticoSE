package com.android.davidgj.cajeroautomaticose.codigopin.ui;

/**
 * Created by DaviDGJ on 23/3/2017.
 */
public interface LoginPinActivityView {

    void showTextLoading();
    void hideTextLoading();
    void enableView();
    void disenableView();
    void navigateToPinScreen();
    void handleCodPinAndCodChip();

    void setcodPinError(String error);
}
