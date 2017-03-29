package com.android.davidgj.cajeroautomaticose.usermenu;

import android.util.Log;


import com.android.davidgj.cajeroautomaticose.entities.Transaction;
import com.android.davidgj.cajeroautomaticose.entities.User;
import com.android.davidgj.cajeroautomaticose.firebase.FirebaseHelper;
import com.android.davidgj.cajeroautomaticose.lib.EventBusHelper;
import com.android.davidgj.cajeroautomaticose.lib.GreenRobotEventBusHelperImple;
import com.android.davidgj.cajeroautomaticose.usermenu.events.MenuEvent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by DaviDGJ on 27/3/2017.
 */

public class MenuRepositoryImpl implements MenuRepository{

    private EventBusHelper eventBusHelper;
    private FirebaseHelper firebaseHelper;
    private DatabaseReference reference;

    public MenuRepositoryImpl() {
        this.eventBusHelper = GreenRobotEventBusHelperImple.getInstance();
        this.firebaseHelper = FirebaseHelper.getInstance();
    }


    @Override
    public void seeBalance(final int codChip) {
        Log.e("seeBalance", "estoy aqui");
        DatabaseReference saldoUser =firebaseHelper.getUserReference(codChip);
        saldoUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                Log.e("SeeBalancce:", user+"  codChip");
                postSeeBalance(MenuEvent.verSaldo, user);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("seeBalance", "Error");
                post(MenuEvent.errorTrasation, databaseError.getMessage());
            }
        });

    }



    @Override
    public void seeExtract(int codChip) {
        DatabaseReference extractoUserReference = firebaseHelper.getUserTransaction(codChip, "NamePruebaseeExtract");
        extractoUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /*Log.e("DataSna",dataSnapshot.getValue()+"");
                Transaction userTransaction = dataSnapshot.getValue(Transaction .class);
                postSeeExtract(MenuEvent.verExxtracto, userTransaction);*/

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                post(MenuEvent.errorTrasation, databaseError.getMessage());
            }
        });
    }

    private void postSeeExtract(int verExxtracto, Transaction userTransaction) {
        /*MenuEvent menuEvent = new MenuEvent();
        menuEvent.setListTransaction(user);
        menuEvent.setTypeEvent(eventType);
        eventBusHelper.post(menuEvent);*/
    }

    //-------------------------------------------------------
    private void postSeeBalance(int eventType, User user) {
        MenuEvent menuEvent = new MenuEvent();
        menuEvent.setUser(user);
        menuEvent.setTypeEvent(eventType);
        eventBusHelper.post(menuEvent);
    }

    private void post(int eventType, String error){
        MenuEvent menuEvent = new MenuEvent();
        menuEvent.setTypeEvent(eventType);
        menuEvent.setErrorMessage(error);
        eventBusHelper.post(menuEvent);
    }
}
