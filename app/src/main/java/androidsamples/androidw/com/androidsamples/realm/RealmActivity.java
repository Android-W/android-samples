package androidsamples.androidw.com.androidsamples.realm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import androidsamples.androidw.com.androidsamples.R;
import io.realm.Realm;
import io.realm.RealmResults;

public class RealmActivity extends AppCompatActivity {

    Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initRealm();

//        addPetForPerson("주짓수", Pet.ONE, "멍멍", 1);
//        addPetForPerson("유도", Pet.ONE, "컹컹", 1);
//        addPetForPerson("합기도", Pet.TWO, "왈왈", 3);
//        addPetForPerson("용무도", Pet.TWO, "킁킁", 3);

//        removeAllForPerson();
//        removeAllForPet();
        findAllForPerson();
//        findAllForPet();
    }

    private void initRealm() {
        mRealm = Realm.getDefaultInstance();
    }

    private void addPetForPerson(final String name, @Pet.Type final String petType, final String petName, final int petAge) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                Pet pet = new Pet(petType, petName, petAge);
                Person result = bgRealm.where(Person.class).equalTo("name", name).findFirst();
                if (result != null) {
                    result.pets.add(pet);
                } else {
                    Person person = bgRealm.createObject(Person.class);
                    person.id = System.currentTimeMillis();
                    person.name = name;
                    person.pets.add(bgRealm.copyToRealm(pet));
                }
            }
        });
    }

    private void findAllForPerson() {
        Log.i("uno", "findAllForPerson");
        RealmResults<Person> persons = mRealm.where(Person.class).findAll();
        if (persons != null && persons.size() > 0) {
            for (Person item : persons) {
                Log.i("uno", item.toString());
            }
        } else {
            Log.i("uno", "person is empty");
        }
    }

    private void findAllForPet() {
        Log.i("uno", "findAllForPet");
        RealmResults<Pet> pets = mRealm.where(Pet.class).findAll();
        if (pets != null && pets.size() > 0) {
            for (Pet item : pets) {
                Log.i("uno", item.toString());
            }
        } else {
            Log.i("uno", "person is empty");
        }
    }

    private void removeAllForPerson() {
        Log.i("uno", "removeAllForPerson");
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Person> persons = realm.where(Person.class).findAll();
                persons.deleteAllFromRealm();
            }
        });
    }

    private void removeAllForPet() {
        Log.i("uno", "removeAllForPet");
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Pet> pets = realm.where(Pet.class).findAll();
                pets.deleteAllFromRealm();
            }
        });
    }

}
