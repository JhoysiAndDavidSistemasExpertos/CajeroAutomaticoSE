package com.android.davidgj.cajeroautomaticose.transactiondinero.events;

import com.android.davidgj.cajeroautomaticose.entities.User;

/**
 * Created by DaviDGJ on 29/3/2017.
 */

public class TransactinDineroEvent {
    public static final int TRANSACTION_SUCCESS = 0;
    public static final int TRANSACTION_ERROR = 1;

    private User user;
    private int eventType;
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
