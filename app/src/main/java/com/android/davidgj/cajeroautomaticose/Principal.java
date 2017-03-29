package com.android.davidgj.cajeroautomaticose;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.FirebaseDatabase;

public class Principal extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        setupFirebase();

    }

    private void setupFirebase() {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
