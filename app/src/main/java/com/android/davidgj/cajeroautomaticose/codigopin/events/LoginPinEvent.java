package com.android.davidgj.cajeroautomaticose.codigopin.events;

/**
 * Created by DaviDGJ on 23/3/2017.
 */
public class LoginPinEvent {

    public static final int PIN_LOGIN_SUCCESS = 0;
    public static final int PIN_LOGIN_ERROR = 1;
    public static final int FAILED_TO_PIN= 2 ;

    private int eventType;
    private String messageError;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }
}
