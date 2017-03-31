package com.android.davidgj.cajeroautomaticose.transactiondinero;

import android.util.Log;

import com.android.davidgj.cajeroautomaticose.entities.Transaction;
import com.android.davidgj.cajeroautomaticose.entities.User;
import com.android.davidgj.cajeroautomaticose.firebase.FirebaseHelper;
import com.android.davidgj.cajeroautomaticose.lib.EventBusHelper;
import com.android.davidgj.cajeroautomaticose.lib.GreenRobotEventBusHelperImple;
import com.android.davidgj.cajeroautomaticose.transactiondinero.events.TransactinDineroEvent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by DaviDGJ on 29/3/2017.
 */

public class TransactionDineroRepositoryImpl implements TransactionDineroRepository{


    private FirebaseHelper firebaseHelper;
    private EventBusHelper eventBusHelper;

    public TransactionDineroRepositoryImpl() {
        this.firebaseHelper = FirebaseHelper.getInstance();
        this.eventBusHelper = GreenRobotEventBusHelperImple.getInstance();
    }

    @Override
    public void takeMoney(final int codChip, final double monto) {

        final DatabaseReference referenceSaldoUser = firebaseHelper.getUserReference(codChip);
        referenceSaldoUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                Log.e("TipoUser", user.getTipoUser());

                if(user.getTipoUser().equalsIgnoreCase("debito")){
                    if(user.getSaldo()>monto){
                        user.setSaldo(user.getSaldo()-monto);
                        referenceSaldoUser.setValue(user);
                        Transaction transaction = new Transaction();
                        transaction.setMonto(monto);
                        transaction.setTipo("Retiro");
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MMM d HH:mm a");
                        String tem = simpleDateFormat.format(new Date());
                        transaction.setDate(tem);
                        DatabaseReference setReferenceTransaction = firebaseHelper.getUserTransaction(codChip, user.getName());
                        setReferenceTransaction.push().setValue(transaction);
                        postTransactionMoneySuccsess(TransactinDineroEvent.TRANSACTION_SUCCESS, user);

                    }else {
                        //posteamos saldo insufuciente
                        postTransactionMoneyError(TransactinDineroEvent.TRANSACTION_ERROR, "Saldo insuficiente",user);

                    }
                }else if(user.getTipoUser().equalsIgnoreCase("credito")){

                    if(user.getSaldo()>-30000&&monto<=user.getSaldo()+30000){
                        user.setSaldo(user.getSaldo()-monto);
                        referenceSaldoUser.setValue(user);
                        Transaction transaction = new Transaction();
                        transaction.setMonto(monto);
                        transaction.setTipo("Retiro");
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MMM d HH:mm a");
                        String tem = simpleDateFormat.format(new Date());
                        transaction.setDate(tem);
                        DatabaseReference setReferenceTransaction = firebaseHelper.getUserTransaction(codChip, user.getName());
                        setReferenceTransaction.push().setValue(transaction);
                        postTransactionMoneySuccsess(TransactinDineroEvent.TRANSACTION_SUCCESS, user);

                    }else {
                        //posteamos saldo insufuciente
                        postTransactionMoneyError(TransactinDineroEvent.TRANSACTION_ERROR, "Saldo insuficiente",user);
                    }
                }else {
                    postTransactionMoneyError(TransactinDineroEvent.TRANSACTION_ERROR, "Error en el servidor intente mas tarde",user);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                postTransactionMoneyError(TransactinDineroEvent.TRANSACTION_ERROR, databaseError.getMessage(), null);

            }
        });

    }

    @Override
    public void depositMoney(final int codChip, final double monto) {

        final DatabaseReference referenceSaldoUser = firebaseHelper.getUserReference(codChip);
        referenceSaldoUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                    user.setSaldo(user.getSaldo()+monto);
                    referenceSaldoUser.setValue(user);
                    Transaction transaction = new Transaction();
                    transaction.setMonto(monto);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MMM d HH:mm a");
                String tem = simpleDateFormat.format(new Date());
                transaction.setDate(tem);
                    transaction.setTipo("Deposito");

                    DatabaseReference setReferenceTransaction = firebaseHelper.getUserTransaction(codChip, user.getName());
                    setReferenceTransaction.push().setValue(transaction);


                    postTransactionMoneySuccsess(TransactinDineroEvent.TRANSACTION_SUCCESS, user);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                postTransactionMoneyError(TransactinDineroEvent.TRANSACTION_ERROR, databaseError.getMessage(),null);

            }
        });

    }
//---------------------------------------------------------------------------
    private void postTransactionMoneyError(int eventType, String errorTransaction, User user) {

        TransactinDineroEvent eventTransaction = new TransactinDineroEvent();
        eventTransaction.setEventType(eventType);
        eventTransaction.setErrorMessage(errorTransaction);
        if(user!=null){
            eventTransaction.setUser(user);
        }
        eventBusHelper.post(eventTransaction);
    }

    private void postTransactionMoneySuccsess(int eventType, User user) {
        TransactinDineroEvent eventTransaction = new TransactinDineroEvent();
        eventTransaction.setEventType(eventType);
        eventTransaction.setUser(user);
        eventBusHelper.post(eventTransaction);

    }
}
