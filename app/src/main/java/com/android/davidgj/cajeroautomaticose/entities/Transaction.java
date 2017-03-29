package com.android.davidgj.cajeroautomaticose.entities;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by DaviDGJ on 27/3/2017.
 */
@IgnoreExtraProperties
public class Transaction {

    private String tipo;
    private double monto;

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
