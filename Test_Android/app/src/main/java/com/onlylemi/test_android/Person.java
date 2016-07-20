package com.onlylemi.test_android;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Person
 *
 * @author: onlylemi
 * @time: 2016-07-19 15:29
 */
public class Person implements Parcelable {

    private String name;
    private int age;

    protected Person(Parcel in) {
        name = in.readString();
        age = in.readInt();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
    }
}
