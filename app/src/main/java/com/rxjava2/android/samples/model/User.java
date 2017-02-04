package com.rxjava2.android.samples.model;

/**
 * Created by amitshekhar on 27/08/16.
 */
public class User {
    public long id;
    public String firstName;
    public String lastName;
    public boolean isFollowing;

    public User() {
    }

    public User(ApiUser apiUser) {
        this.id = apiUser.id;
        this.firstName = apiUser.firstname;
        this.lastName = apiUser.lastname;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isFollowing=" + isFollowing +
                '}';
    }
}
