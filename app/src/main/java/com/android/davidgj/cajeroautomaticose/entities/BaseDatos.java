package com.android.davidgj.cajeroautomaticose.entities;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by DaviDGJ on 23/3/2017.
 */
public class BaseDatos {

    private ArrayList<User> listUser = new ArrayList<>();
    User user = new User();
    public BaseDatos() {



    }

    public ArrayList<User> getListUser() {
        return listUser;
    }

    public boolean isCodChipSuccess(int codChip){
        boolean isCodChipSuccess = false;
        for(int i =0;i<listUser.size();i++){
            if(codChip==listUser.get(i).getCodChip()){
                isCodChipSuccess = true;
                user.setCodChip(codChip);
                break;
            }else{
                user.setCodChip(000);
            }
        }
        return isCodChipSuccess;
    }

    public boolean isCodPinSuccess(int codPin){
        boolean isCodPinSuccess = false;
        if(listUser.contains(user)){

            int index = listUser.indexOf(user);
            user = listUser.get(index);
            if(user.getCodPin()==codPin){
                isCodPinSuccess = true;
            }else{
                Log.e("Base de Datos","no es igual su pin");
            }
        }else {
            Log.e("Base de Datos","no contienen al user");
        }

        return isCodPinSuccess;

    }


}
