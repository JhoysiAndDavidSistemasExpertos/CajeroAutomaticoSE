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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DaviDGJ on 27/3/2017.
 */

public class MenuRepositoryImpl implements MenuRepository{

    private EventBusHelper eventBusHelper;
    private FirebaseHelper firebaseHelper;

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
                postError(MenuEvent.errorTrasation, databaseError.getMessage());
            }
        });

    }



    @Override
    public void seeExtract(final int codChip) {
       final ArrayList<Transaction> listTransaction = new ArrayList<Transaction>();

        DatabaseReference userReference = firebaseHelper.getUserReference(codChip);
        userReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final User user = dataSnapshot.getValue(User.class);

                DatabaseReference refereceExtract = firebaseHelper.getUserTransaction(codChip, user.getName());
                refereceExtract.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot f: dataSnapshot.getChildren()){
                            Transaction transaction = f.getValue(Transaction.class);
                            listTransaction.add(transaction);
                        }
                        postSeeExtract(MenuEvent.verExxtracto,user, listTransaction);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        postError(MenuEvent.errorTrasation, databaseError.getMessage());

                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                postError(MenuEvent.errorTrasation, databaseError.getMessage());

            }
        });
    }


    //-------------------------------------------------------


    private void  postSeeExtract(int eventtype, User user, ArrayList<Transaction> transactionList){

        MenuEvent event = new MenuEvent();
        event.setUser(user);
        event.setListTransaction(transactionList);
        event.setTypeEvent(eventtype);
        eventBusHelper.post(event);


    }
    private void postSeeBalance(int eventType, User user) {
        MenuEvent menuEvent = new MenuEvent();
        menuEvent.setUser(user);
        menuEvent.setTypeEvent(eventType);
        eventBusHelper.post(menuEvent);
    }

    private void postError(int eventType, String error){
        MenuEvent menuEvent = new MenuEvent();
        menuEvent.setTypeEvent(eventType);
        menuEvent.setErrorMessage(error);
        eventBusHelper.post(menuEvent);
    }
}
