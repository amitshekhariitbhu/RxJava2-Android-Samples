package com.rxjava2.android.samples.ui.networking

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.rx2androidnetworking.Rx2AndroidNetworking
import com.rxjava2.android.samples.R
import com.rxjava2.android.samples.model.ApiUser
import com.rxjava2.android.samples.model.User
import com.rxjava2.android.samples.model.UserDetail
import com.rxjava2.android.samples.utils.Utils
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

/**
 * Created by amitshekhar on 04/02/17.
 */

class NetworkingActivity : AppCompatActivity() {

    companion object {
        const val TAG = "NetworkingActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_networking)
    }

    /**
     * zip Operator Example
     */

    /**
     * This observable return the list of User who loves cricket
     */
    private fun getCricketFansObservable(): Observable<List<User>> {
        return Rx2AndroidNetworking.get("https://fierce-cove-29863.herokuapp.com/getAllCricketFans")
                .build()
                .getObjectListObservable(User::class.java)
                .subscribeOn(Schedulers.io())
    }

    /**
     * This observable return the list of User who loves Football
     */
    private fun getFootballFansObservable(): Observable<List<User>> {
        return Rx2AndroidNetworking.get("https://fierce-cove-29863.herokuapp.com/getAllFootballFans")
                .build()
                .getObjectListObservable(User::class.java)
                .subscribeOn(Schedulers.io())
    }

    /**
     * flatMap and filter Operators Example
     */
    private fun getAllMyFriendsObservable(): Observable<List<User>> {
        return Rx2AndroidNetworking.get("https://fierce-cove-29863.herokuapp.com/getAllFriends/{userId}")
                .addPathParameter("userId", "1")
                .build()
                .getObjectListObservable(User::class.java)
    }

    /**
     * flatMapWithZip Operator Example
     */
    private fun getUserListObservable(): Observable<List<User>> {
        return Rx2AndroidNetworking.get("https://fierce-cove-29863.herokuapp.com/getAllUsers/{pageNumber}")
                .addPathParameter("pageNumber", "0")
                .addQueryParameter("limit", "10")
                .build()
                .getObjectListObservable(User::class.java)
    }

    /**
     * Map Operator Example
     */
    fun map(view: View) {
        Rx2AndroidNetworking.get("https://fierce-cove-29863.herokuapp.com/getAnUser/{userId}")
                .addPathParameter("userId", "1")
                .build()
                .getObjectObservable(ApiUser::class.java)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { apiUser ->
                    // here we get ApiUser from server
                    // then by converting, we are returning user
                    User(apiUser.id, apiUser.firstname, apiUser.lastname)
                }
                .subscribe(object : Observer<User> {

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(user: User) {
                        Log.d(TAG, "user : $user")
                    }

                    override fun onError(e: Throwable) {
                        Utils.logError(TAG, e)
                    }

                    override fun onComplete() {
                        Log.d(TAG, "onComplete")
                    }
                })
    }

    /**
     * This do the complete magic, make both network call
     * and then returns the list of user who loves both
     * Using zip operator to get both response at a time
     */
    private fun findUsersWhoLovesBoth() {
        // here we are using zip operator to combine both request
        Observable.zip(getCricketFansObservable(), getFootballFansObservable(),
                BiFunction<List<User>, List<User>, List<User>> { cricketFans, footballFans ->
                    return@BiFunction Utils.filterUserWhoLovesBoth(cricketFans, footballFans)
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<List<User>> {

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(users: List<User>) {
                        // do anything with user who loves both
                        Log.d(TAG, "userList size : " + users.size)
                        for (user in users) {
                            Log.d(TAG, "user : $user")
                        }
                    }

                    override fun onError(e: Throwable) {
                        Utils.logError(TAG, e)
                    }

                    override fun onComplete() {
                        Log.d(TAG, "onComplete")
                    }
                })
    }

    fun zip(view: View) {
        findUsersWhoLovesBoth()
    }

    fun flatMapAndFilter(view: View) {
        getAllMyFriendsObservable()
                .flatMap { usersList ->
                    // flatMap - to return users one by one
                    Observable.fromIterable(usersList) // returning user one by one from usersList.
                }
                .filter { user ->
                    // filtering user who follows me.
                    return@filter user.isFollowing
                }
                .toList()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<List<User>> {

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(users: List<User>) {
                        // only the user who is following will be in the list
                        Log.d(TAG, "userList size : " + users.size)
                        for (user in users) {
                            Log.d(TAG, "user : $user")
                        }
                    }

                    override fun onError(e: Throwable) {
                        Utils.logError(TAG, e)
                    }

                    override fun onComplete() {
                        Log.d(TAG, "onComplete")
                    }
                })
    }

    /**
     * flatMap Operator Example
     */
    fun flatMap(view: View) {
        getUserListObservable()
                .flatMap { usersList ->
                    // flatMap - to return users one by one
                    Observable.fromIterable(usersList) // returning user one by one from usersList.
                }
                .flatMap { user ->
                    // here we get the user one by one
                    // and returns corresponding getUserDetailObservable
                    // for that userId
                    getUserDetailObservable(user.id)
                }
                .toList()
                .toObservable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<List<UserDetail>> {

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onError(e: Throwable) {
                        Utils.logError(TAG, e)
                    }

                    override fun onNext(userDetailList: List<UserDetail>) {
                        // do anything with userDetail list

                        Log.d(TAG, "userDetailList size : " + userDetailList.size)
                        for (userDetail in userDetailList) {
                            Log.d(TAG, "userDetail : $userDetail")
                        }
                    }

                    override fun onComplete() {
                        Log.d(TAG, "onComplete")
                    }
                })
    }

    private fun getUserDetailObservable(id: Long): Observable<UserDetail> {
        return Rx2AndroidNetworking.get("https://fierce-cove-29863.herokuapp.com/getAnUserDetail/{userId}")
                .addPathParameter("userId", id.toString())
                .build()
                .getObjectObservable(UserDetail::class.java)
    }

}
