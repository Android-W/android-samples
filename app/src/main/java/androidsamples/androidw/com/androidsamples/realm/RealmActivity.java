package androidsamples.androidw.com.androidsamples.realm;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import androidsamples.androidw.com.androidsamples.Consts;
import androidsamples.androidw.com.androidsamples.R;
import androidsamples.androidw.com.androidsamples.base.view.BaseActivity;
import androidsamples.androidw.com.androidsamples.util.PrefUtil;
import butterknife.BindView;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

public class RealmActivity extends BaseActivity {

    @BindView(R.id.tv_log) TextView mTvLog;

    Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (mRealm == null) mRealm = Realm.getDefaultInstance();

        if (!PrefUtil.getBoolean(this, Consts.SP_REALM_DEMO_INIT)) {
            PrefUtil.put(this, Consts.SP_REALM_DEMO_INIT, true);
            initialize();
        }

        findAllForPerson();
    }

    @OnClick(R.id.bt_init)
    void initialize() {
        mTvLog.setText("Initialize");
        removeAllPerson();
        removeAllPet();
        addPetForPerson("주짓수", Pet.ONE, "멍멍", 1);
        addPetForPerson("유도", Pet.ONE, "컹컹", 1);
        addPetForPerson("합기도", Pet.TWO, "왈왈", 3);
        addPetForPerson("용무도", Pet.TWO, "킁킁", 3);
        findAllForPerson();
    }

    void addPetForPerson(final String name, @Pet.Type final String petType, final String petName, final int petAge) {
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

    @OnClick(R.id.bt_find_all_for_person)
    void findAllForPerson() {
        mTvLog.setText("Find all for person. Query...");
        RealmResults realmResults = mRealm.where(Person.class).findAll();
        String result = parseResult(realmResults);
        mTvLog.setText(result);
    }

    @OnClick(R.id.bt_find_all_for_pet)
    void findAllForPet() {
        mTvLog.setText("Find all for pet. Query...");
        RealmResults realmResults = mRealm.where(Pet.class).findAll();
        String result = parseResult(realmResults);
        mTvLog.setText(result);
    }

    @OnClick(R.id.bt_remove_all_person)
    void removeAllPerson() {
        mTvLog.setText("Remove all person!");
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Person> persons = realm.where(Person.class).findAll();
                persons.deleteAllFromRealm();
            }
        });
    }

    @OnClick(R.id.bt_remove_all_pet)
    void removeAllPet() {
        mTvLog.setText("Remove all pet!");
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Pet> pets = realm.where(Pet.class).findAll();
                pets.deleteAllFromRealm();
            }
        });
    }

    @OnClick({
            R.id.bt_query_for_pet_age_1,
            R.id.bt_query_for_pet_age_2
    })
    void queryForPetAge(View v) {
        switch (v.getId()) {

            // age < 3
            case R.id.bt_query_for_pet_age_1:
                RealmResults realmResults = mRealm.where(Pet.class).lessThan("age", 3).findAll();
                String result = parseResult(realmResults);
                mTvLog.setText(result);

                break;

            // age > 1
            case R.id.bt_query_for_pet_age_2:
                realmResults = mRealm.where(Pet.class).greaterThan("age", 1).findAll();
                result = parseResult(realmResults);
                mTvLog.setText(result);
                break;
        }
    }

    /**
     * 로그용 메소드 추가
     * @param realmObjects
     * @return
     */
    String parseResult(RealmResults realmObjects) {
        String result = "";
        if (realmObjects != null && realmObjects.size() > 0) {
            for (Object object : realmObjects) {
                result += object.toString() + "\n";
            }
        } else {
            result = "is empty";
        }

        return result;
    }

}
