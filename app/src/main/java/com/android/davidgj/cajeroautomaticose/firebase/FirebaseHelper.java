package com.android.davidgj.cajeroautomaticose.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Thanos on 26/3/2017.
 */

public class FirebaseHelper {

    DatabaseReference databaseReference;

    public static final String DATA_BASE_URL = "https://cajero-automatico.firebaseio.com/";
    public static final String USERS_PATH = "users";
    public static final String USERS_SALDO_PATH = "saldo";
    public static final String TRANSCATIONS_PATH = "transaction";
    public static final String CODCHIP_PATH = "codChip";



    private static class SingletonHolder{
        public static final FirebaseHelper INSTANCE = new FirebaseHelper();
    }

    public static FirebaseHelper getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public FirebaseHelper() {
        this.databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl(DATA_BASE_URL);
    }

    //OBTENEMOS LA REFERENCIA A LA RAIZ DE LA BASE DE DATOS
    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }

    //obttener referencia de un user segun su chip
    public DatabaseReference getListUserReference(){

        return getDatabaseReference().child(USERS_PATH);
    }
    //obttener referencia de las transacions de un user segun su chip
    public DatabaseReference getUserTransaction(int codChip, String name){
        //String keyTransaction = codChip+String.valueOf(getUserReference(codChip).child("name").getKey());
        String keyTransaction = codChip+name;

        return getDatabaseReference().child(TRANSCATIONS_PATH).child(keyTransaction);
    }
    //obttener referencia al saldo de un user segun su chip
    public DatabaseReference getUserReference(int codChip){

        return getDatabaseReference().child(USERS_PATH).child(String.valueOf(codChip));
    }


}






