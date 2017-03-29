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

    private Date date;

    public String getDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MMM d HH:mm a");
        String tem = simpleDateFormat.format(date);
        return tem;
    }

    public void setDate(Date date) {
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
