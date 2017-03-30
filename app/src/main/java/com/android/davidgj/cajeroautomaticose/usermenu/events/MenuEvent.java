package com.android.davidgj.cajeroautomaticose.usermenu.events;


import com.android.davidgj.cajeroautomaticose.entities.Transaction;
import com.android.davidgj.cajeroautomaticose.entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DaviDGJ on 27/3/2017.
 */

public class MenuEvent {
    public static final int retirarDinero = 0;
    public static final int sacarDinero = 1;
    public static final int verSaldo = 2;
    public static final int verExxtracto = 3;
    public static final int errorTrasation = 4;

    public User user;

    public ArrayList<Transaction> listTransaction;

    public ArrayList<Transaction> getListTransaction() {
        return listTransaction;
    }

    public void setListTransaction(ArrayList<Transaction> listTransaction) {
        this.listTransaction = listTransaction;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String errorMessage;


    public int typeEvent;

    public int getTypeEvent() {
        return typeEvent;
    }

    public void setTypeEvent(int typeEvent) {
        this.typeEvent = typeEvent;
    }
}
