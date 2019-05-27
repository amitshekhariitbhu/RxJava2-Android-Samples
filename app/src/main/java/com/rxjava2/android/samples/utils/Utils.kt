package com.rxjava2.android.samples.utils

import android.util.Log
import com.androidnetworking.error.ANError
import com.rxjava2.android.samples.model.ApiUser
import com.rxjava2.android.samples.model.User
import java.util.*

/**
 * Created by amitshekhar on 27/08/16.
 */
object Utils {

    fun getUserList(): List<User> {

        val userList = ArrayList<User>()

        val userOne = User(firstname = "Amit", lastname = "Shekhar")
        userList.add(userOne)

        val userTwo = User(firstname = "Manish", lastname = "Kumar")
        userList.add(userTwo)

        val userThree = User(firstname = "Sumit", lastname = "Kumar")
        userList.add(userThree)

        return userList
    }

    fun getApiUserList(): List<ApiUser> {

        val apiUserList = ArrayList<ApiUser>()

        val apiUserOne = ApiUser(firstname = "Amit", lastname = "Shekhar")
        apiUserList.add(apiUserOne)

        val apiUserTwo = ApiUser(firstname = "Manish", lastname = "Kumar")
        apiUserList.add(apiUserTwo)

        val apiUserThree = ApiUser(firstname = "Sumit", lastname = "Kumar")
        apiUserList.add(apiUserThree)

        return apiUserList
    }


    fun getUserListWhoLovesCricket(): List<User> {

        val userList = ArrayList<User>()

        val userOne = User(id = 1, firstname = "Amit", lastname = "Shehkar")
        userList.add(userOne)

        val userTwo = User(id = 2, firstname = "Manish", lastname = "Kumar")
        userList.add(userTwo)

        return userList
    }


    fun getUserListWhoLovesFootball(): List<User> {

        val userList = ArrayList<User>()

        val userOne = User(id = 1, firstname = "Amit", lastname = "Shehkar")
        userList.add(userOne)

        val userTwo = User(id = 3, firstname = "Sumit", lastname = "Kumar")
        userList.add(userTwo)

        return userList
    }

    fun convertApiUserListToUserList(apiUserList: List<ApiUser>): List<User> {

        val userList = ArrayList<User>()

        for (apiUser in apiUserList) {
            val user = User(apiUser.id, apiUser.firstname, apiUser.lastname)
            userList.add(user)
        }

        return userList
    }

    fun filterUserWhoLovesBoth(cricketFans: List<User>, footballFans: List<User>): List<User> {
        val userWhoLovesBoth = ArrayList<User>()

        for (footballFan in footballFans) {
            if (cricketFans.contains(footballFan)) {
                userWhoLovesBoth.add(footballFan)
            }
        }

        return userWhoLovesBoth
    }

    fun logError(TAG: String, e: Throwable) {
        if (e is ANError) {
            if (e.errorCode != 0) {
                // received ANError from server
                // error.getErrorCode() - the ANError code from server
                // error.getErrorBody() - the ANError body from server
                // error.getErrorDetail() - just a ANError detail
                Log.d(TAG, "onError errorCode : " + e.errorCode)
                Log.d(TAG, "onError errorBody : " + e.errorBody)
                Log.d(TAG, "onError errorDetail : " + e.errorDetail)
            } else {
                // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                Log.d(TAG, "onError errorDetail : " + e.errorDetail)
            }
        } else {
            Log.d(TAG, "onError errorMessage : " + e.message)
        }
    }

}
