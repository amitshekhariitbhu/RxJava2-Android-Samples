package com.rxjava2.android.samples.model;

import androidx.annotation.NonNull;

/**
 * Created by amitshekhar on 04/02/17.
 */

public class UserDetail {

    public long id;
    public String firstname;
    public String lastname;

    @NonNull
    @Override
    public String toString() {
        return "UserDetail{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
