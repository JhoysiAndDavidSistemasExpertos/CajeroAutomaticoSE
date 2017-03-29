package com.android.davidgj.cajeroautomaticose.transactiondinero;

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
                if(user.getSaldo()>monto){
                    user.setSaldo(user.getSaldo()-monto);
                    referenceSaldoUser.setValue(user);
                    Transaction transaction = new Transaction();
                    transaction.setMonto(monto);
                    transaction.setTipo("Retiro");
                    transaction.setDate(new Date());
                    DatabaseReference setReferenceTransaction = firebaseHelper.getUserTransaction(codChip, user.getName());
                    setReferenceTransaction.push().setValue(transaction);


                    postTransactionMoneySuccsess(TransactinDineroEvent.TRANSACTION_SUCCESS, user);


                }else {
                    //posteamos saldo insufuciente
                    postTransactionMoneyError(TransactinDineroEvent.TRANSACTION_ERROR, "Error Intente mas tarde porfavor");

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                postTransactionMoneyError(TransactinDineroEvent.TRANSACTION_ERROR, databaseError.getMessage());

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
                transaction.setDate(new Date());
                    transaction.setTipo("Deposito");

                    DatabaseReference setReferenceTransaction = firebaseHelper.getUserTransaction(codChip, user.getName());
                    setReferenceTransaction.push().setValue(transaction);


                    postTransactionMoneySuccsess(TransactinDineroEvent.TRANSACTION_SUCCESS, user);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                postTransactionMoneyError(TransactinDineroEvent.TRANSACTION_ERROR, databaseError.getMessage());

            }
        });

    }
//---------------------------------------------------------------------------
    private void postTransactionMoneyError(int eventType, String errorTransaction) {

        TransactinDineroEvent eventTransaction = new TransactinDineroEvent();
        eventTransaction.setEventType(eventType);
        eventTransaction.setErrorMessage(errorTransaction);
        eventBusHelper.post(eventTransaction);
    }

    private void postTransactionMoneySuccsess(int eventType, User user) {
        TransactinDineroEvent eventTransaction = new TransactinDineroEvent();
        eventTransaction.setEventType(eventType);
        eventTransaction.setUser(user);
        eventBusHelper.post(eventTransaction);

    }
}
