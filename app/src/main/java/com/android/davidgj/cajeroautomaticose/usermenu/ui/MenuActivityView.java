package com.android.davidgj.cajeroautomaticose.usermenu.ui;


import com.android.davidgj.cajeroautomaticose.entities.Transaction;
import com.android.davidgj.cajeroautomaticose.entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thanos on 26/3/2017.
 */

public interface MenuActivityView {

    void handleRetirarDinero();
    void handleDepositarDinero();
    void handleVerSaldo();
    void handleVerExtracto();

    void showBalance(User userSaldo);
    void showExtract(User extractUser, ArrayList<Transaction> arrayList);

    void setError(String errorMessage);

}
