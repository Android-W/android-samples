package androidsamples.androidw.com.androidsamples;

import android.app.Application;

import androidsamples.androidw.com.androidsamples.realm.Migration;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class GlobalApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .schemaVersion(Consts.REALM_SCHEME_VERSION)
                .migration(new Migration())
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
