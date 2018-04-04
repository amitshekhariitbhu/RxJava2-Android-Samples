package com.rxjava2.android.samples.model;

/**
 * Created by amitshekhar on 27/08/16.
 */
public class User {
    public long id;
    public String firstname;
    public String lastname;
    public boolean isFollowing;

    public User() {
    }

    public User(ApiUser apiUser) {
        this.id = apiUser.id;
        this.firstname = apiUser.firstname;
        this.lastname = apiUser.lastname;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", isFollowing=" + isFollowing +
                '}';
    }

    @Override
    public int hashCode() {
        return (int) id + firstname.hashCode() + lastname.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof User) {
            User user = (User) obj;

            return this.id == user.id
                    && this.firstname.equals(user.firstname)
                    && this.lastname.equals(user.lastname);
        }

        return false;
    }
}
