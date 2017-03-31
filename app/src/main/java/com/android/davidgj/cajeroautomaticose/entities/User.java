package com.android.davidgj.cajeroautomaticose.entities;

import com.google.firebase.database.IgnoreExtraProperties;


/**
 * Created by DaviDGJ on 23/3/2017.
 */
@IgnoreExtraProperties
public class User {
    private String name;
    private String lastname;
    private int codChip;
    private int codPin;
    private double saldo;

    private String tipoUser;




    public User() {
    }

    public String getTipoUser() {
        return tipoUser;
    }

    public void setTipoUser(String tipoUser) {
        this.tipoUser = tipoUser;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public int getCodChip() {
        return codChip;
    }

    public int getCodPin() {
        return codPin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setCodChip(int codChip) {
        this.codChip = codChip;
    }

    public void setCodPin(int codPin) {
        this.codPin = codPin;
    }


    @Override
    public boolean equals(Object obj) {
        boolean isEquals = false;
        if(obj instanceof  User){
            User u = (User)obj;

            isEquals = this.codChip == u.getCodChip();
        }
        return isEquals;
    }
}
