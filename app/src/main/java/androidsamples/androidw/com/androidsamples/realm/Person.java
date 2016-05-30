package androidsamples.androidw.com.androidsamples.realm;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Person extends RealmObject {

    public long id;
    public String name;
    public RealmList<Pet> pets;

    @Override
    public String toString() {

        String petArray = "[";
        if (pets != null && pets.size() > 0) {
            for (int i = 0; i < pets.size(); i++) {
                if (i > 0) petArray += ", ";
                petArray += "{" + pets.get(i).toString() + "}";
            }
        }
        petArray += "}]";

        return "Person{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", pets=" + petArray +
                '}';
    }
}
