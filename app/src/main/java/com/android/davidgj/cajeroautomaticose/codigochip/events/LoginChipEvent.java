package com.android.davidgj.cajeroautomaticose.codigochip.events;

/**
 * Created by DaviDGJ on 23/3/2017.
 */
public class LoginChipEvent {

    public static final int onCodChipSuccess = 0;
    public static final int onCodChipError = 1;


    private int eventType;

    private String errorMessage;


    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
