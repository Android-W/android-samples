package androidsamples.androidw.com.androidsamples.realm;

import android.util.Log;

import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

/**
 * Created by Uno.kim on 2016. 5. 30..
 */
public class Migration implements RealmMigration {

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        Log.i("uno", "migrate: " + oldVersion);

        RealmSchema schema = realm.getSchema();

        if (oldVersion < 1) {

            // name field rename
            schema.get("Person").renameField("personName", "name");

            // String field "age" -> convert int field "age"
            // convert to field "age_tmp"
            // remove legacy field "age"
            // rename field "age_tmp" to "age"
            schema.get("Pet")
                    .addField("age_tmp", int.class)
                    .transform(new RealmObjectSchema.Function() {
                        @Override
                        public void apply(DynamicRealmObject obj) {
                            String age = obj.getString("age");
                            try {
                                obj.setInt("age_tmp", Integer.parseInt(age));
                            } catch (Exception e) {
                                obj.setInt("age_tmp", 0);
                            }
                        }
                    })
                    .removeField("age")
                    .renameField("age_tmp", "age");
        }

    }


}
