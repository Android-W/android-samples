package androidsamples.androidw.com.androidsamples.realm;

import android.support.annotation.StringDef;

import io.realm.RealmObject;

public class Pet extends RealmObject {

    @StringDef({
        ONE, TWO, THREE, FOUR
    })

    public @interface Type { }
    public static final String ONE = "개";
    public static final String TWO = "고양이";
    public static final String THREE = "멍멍이";
    public static final String FOUR = "멍청이";

    public String type;
    public String name;
    public int age;

    public Pet() {
    }

    public Pet(@Type String type, String name, int age) {
        this.type = type;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}