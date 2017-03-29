package com.android.davidgj.cajeroautomaticose.entities;

/**
 * Created by DaviDGJ on 27/3/2017.
 */

public class Comunicador {

        private static Object objeto = null;

        public static void setObjeto(Object newObjeto) {
            objeto = newObjeto;
        }

        public static Object getObjeto() {
            return objeto;
        }

}
