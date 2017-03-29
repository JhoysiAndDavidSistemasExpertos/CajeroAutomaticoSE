package com.android.davidgj.cajeroautomaticose.codigochip;


import com.android.davidgj.cajeroautomaticose.codigochip.events.LoginChipEvent;
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
public class LoginChipRepositoryImpl implements LoginChipRepository{

   private FirebaseHelper firebaseHelper;
    private EventBusHelper eventBusHelper;

    public LoginChipRepositoryImpl() {

        this.firebaseHelper= FirebaseHelper.getInstance();
        this.eventBusHelper = GreenRobotEventBusHelperImple.getInstance();

    }

    @Override
    public void doValidateCodChip(final int codChip) {
        DatabaseReference referenceChips = firebaseHelper.getListUserReference();
        referenceChips.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot f : dataSnapshot.getChildren()){
                    User u = f.getValue(User.class);
                    if(u.getCodChip()==codChip){
                        postEvent(LoginChipEvent.onCodChipSuccess);
                        break;
                    }else {
                        postEvent(LoginChipEvent.onCodChipError, "Tarjeta invalida");
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                postEvent(LoginChipEvent.onCodChipError, databaseError.getMessage());
            }
        });

    }

    private void postEvent(int evenType, String error) {
        LoginChipEvent loginChipEvent = new LoginChipEvent();
        loginChipEvent.setEventType(evenType);

        if(error!=null){
        loginChipEvent.setErrorMessage(error);
        }

        EventBusHelper eventbus = GreenRobotEventBusHelperImple.getInstance();
        eventbus.post(loginChipEvent);
    }

    private void postEvent(int eventType) {
        postEvent(eventType, null);

    }
}
