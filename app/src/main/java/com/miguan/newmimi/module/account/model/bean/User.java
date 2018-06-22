package com.miguan.newmimi.module.account.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    public String id;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.id = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

}
