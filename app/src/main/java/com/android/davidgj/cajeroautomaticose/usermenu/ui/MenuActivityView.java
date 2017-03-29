package com.android.davidgj.cajeroautomaticose.usermenu.ui;


import com.android.davidgj.cajeroautomaticose.entities.User;

/**
 * Created by Thanos on 26/3/2017.
 */

public interface MenuActivityView {

    void handleRetirarDinero();
    void handleDepositarDinero();
    void handleVerSaldo();
    void handleVerExtracto();

    void showBalance(User userSaldo);
    void showExtract(User extractUser);

    void setError(String errorMessage);

}
