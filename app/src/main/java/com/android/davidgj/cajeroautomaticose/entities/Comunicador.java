package com.android.davidgj.cajeroautomaticose.entities;

import java.util.ArrayList;

/**
 * Created by DaviDGJ on 27/3/2017.
 */

public class Comunicador {

        private static Object objeto = null;
        private static ArrayList<Transaction> transactions = null;

    public static ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public static void setTransactions(ArrayList<Transaction> transactions) {
        Comunicador.transactions = transactions;
    }

    public static void setObjeto(Object newObjeto) {
            objeto = newObjeto;
        }

        public static Object getObjeto() {
            return objeto;
        }

}
