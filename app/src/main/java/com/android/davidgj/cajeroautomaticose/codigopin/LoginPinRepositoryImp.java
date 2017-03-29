package com.android.davidgj.cajeroautomaticose.codigopin;


import com.android.davidgj.cajeroautomaticose.codigopin.events.LoginPinEvent;
import com.android.davidgj.cajeroautomaticose.entities.User;
import com.android.davidgj.cajeroautomaticose.firebase.FirebaseHelper;
import com.android.davidgj.cajeroautomaticose.lib.EventBusHelper;
import com.android.davidgj.cajeroautomaticose.lib.GreenRobotEventBusHelperImple;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by DaviDGJ on 23/3/2017.
 */
public class LoginPinRepositoryImp implements LoginPinRepository{

    private FirebaseHelper firebaseHelper;
    private EventBusHelper eventBusHelper;

    public LoginPinRepositoryImp() {

        this.firebaseHelper= FirebaseHelper.getInstance();
        this.eventBusHelper = GreenRobotEventBusHelperImple.getInstance();
    }

    @Override
    public void doValidateCodPinAndCodChip(final int codPin, final int codChip) {
        DatabaseReference referenceChips = firebaseHelper.getListUserReference();
        referenceChips.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot f : dataSnapshot.getChildren()){
                    User u = f.getValue(User.class);
                    if(u.getCodChip()==codChip && u.getCodPin()==codPin){
                        postEvent(LoginPinEvent.PIN_LOGIN_SUCCESS);
                        break;
                    }else {
                        postEvent(LoginPinEvent.PIN_LOGIN_ERROR, "Pin incorreto");
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                postEvent(LoginPinEvent.PIN_LOGIN_ERROR, databaseError.getMessage());
            }
        });
    }


    private void postEvent(int evenType, String error) {
        LoginPinEvent loginPinEvent = new LoginPinEvent();
        loginPinEvent.setEventType(evenType);
        if(error!=null){
            loginPinEvent.setMessageError(error);
        }

        EventBusHelper eventbus = GreenRobotEventBusHelperImple.getInstance();
        eventbus.post(loginPinEvent);
    }

    private void postEvent(int eventType) {
        postEvent(eventType, null);

    }
}
