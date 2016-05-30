package androidsamples.androidw.com.androidsamples.firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public abstract class SimpleValueListener implements ValueEventListener {

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        onComplete(dataSnapshot, null);
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        onComplete(null, databaseError);
    }

    public abstract void onComplete(DataSnapshot dataSnapshot, DatabaseError databaseError);

}
