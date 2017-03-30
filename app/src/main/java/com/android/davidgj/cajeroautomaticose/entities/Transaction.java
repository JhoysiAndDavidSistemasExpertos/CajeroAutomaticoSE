package com.android.davidgj.cajeroautomaticose.entities;

import com.google.firebase.database.IgnoreExtraProperties;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by DaviDGJ on 27/3/2017.
 */
@IgnoreExtraProperties
public class Transaction {

    private String tipo;
    private double monto;

    private String date;

    public String getDate() {

        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Transaction() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}
