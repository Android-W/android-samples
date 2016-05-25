package androidsamples.androidw.com.androidsamples;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        sendRegistrationToServer(FirebaseInstanceId.getInstance().getToken());
    }

    private void sendRegistrationToServer(String token) {
        Log.i("uno", "onTokenRefresh: " + token);
        DatabaseReference tokenReference = FirebaseDatabase.getInstance().getReference().child("token");
        tokenReference.child(FirebaseInstanceId.getInstance().getId()).setValue(token, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                sendBroadcast(new Intent(Consts.ACTION_RECEIVE_TOKEN));
            }
        });
    }

}