package com.rxjava2.android.samples.model;

/**
 * Created by amitshekhar on 27/08/16.
 */
public class ApiUser {
    public long id;
    public String firstName;
    public String lastName;

    @Override
    public String toString() {
        return "ApiUser{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
