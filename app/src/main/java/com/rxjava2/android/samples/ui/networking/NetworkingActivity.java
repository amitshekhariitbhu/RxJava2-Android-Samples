package com.rxjava2.android.samples.ui.networking;

import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.rx2androidnetworking.Rx2AndroidNetworking;
import com.rxjava2.android.samples.R;
import com.rxjava2.android.samples.model.ApiUser;
import com.rxjava2.android.samples.model.User;
import com.rxjava2.android.samples.model.UserDetail;
import com.rxjava2.android.samples.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by amitshekhar on 04/02/17.
 */

public class NetworkingActivity extends AppCompatActivity {

    public static final String TAG = NetworkingActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_networking);
    }

    /**
     * Map Operator Example
     */
    public void map(View view) {
        Rx2AndroidNetworking.get("https://fierce-cove-29863.herokuapp.com/getAnUser/{userId}")
                .addPathParameter("userId", "1")
                .build()
                .getObjectObservable(ApiUser.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(apiUser -> {
                    // here we get ApiUser from server
                    User user = new User(apiUser);
                    // then by converting, we are returning user
                    return user;
                })
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(User user) {
                        Log.d(TAG, "user : " + user.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.logError(TAG, e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });
    }


    /**
     * zip Operator Example
     */

    /**
     * This observable return the list of User who loves cricket
     */
    private Observable<List<User>> getCricketFansObservable() {
        return Rx2AndroidNetworking.get("https://fierce-cove-29863.herokuapp.com/getAllCricketFans")
                .build()
                .getObjectListObservable(User.class)
                .subscribeOn(Schedulers.io());
    }

    /*
     * This observable return the list of User who loves Football
     */
    private Observable<List<User>> getFootballFansObservable() {
        return Rx2AndroidNetworking.get("https://fierce-cove-29863.herokuapp.com/getAllFootballFans")
                .build()
                .getObjectListObservable(User.class)
                .subscribeOn(Schedulers.io());
    }

    /*
     * This do the complete magic, make both network call
     * and then returns the list of user who loves both
     * Using zip operator to get both response at a time
     */
    private void findUsersWhoLovesBoth() {
        // here we are using zip operator to combine both request
        Observable.zip(getCricketFansObservable(), getFootballFansObservable(),
                (cricketFans, footballFans) -> {
                    List<User> userWhoLovesBoth =
                            filterUserWhoLovesBoth(cricketFans, footballFans);
                    return userWhoLovesBoth;
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<User>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<User> users) {
                        // do anything with user who loves both
                        Log.d(TAG, "userList size : " + users.size());
                        for (User user : users) {
                            Log.d(TAG, "user : " + user.toString());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.logError(TAG, e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });
    }

    private List<User> filterUserWhoLovesBoth(List<User> cricketFans, List<User> footballFans) {
        List<User> userWhoLovesBoth = new ArrayList<>();

        for (User footballFan : footballFans) {
            if (cricketFans.contains(footballFan)) {
                userWhoLovesBoth.add(footballFan);
            }
        }

        return userWhoLovesBoth;
    }


    public void zip(View view) {
        findUsersWhoLovesBoth();
    }


    /**
     * flatMap and filter Operators Example
     */

    private Observable<List<User>> getAllMyFriendsObservable() {
        return Rx2AndroidNetworking.get("https://fierce-cove-29863.herokuapp.com/getAllFriends/{userId}")
                .addPathParameter("userId", "1")
                .build()
                .getObjectListObservable(User.class);
    }

    public void flatMapAndFilter(View view) {
        // flatMap - to return users one by one
        getAllMyFriendsObservable()
                .flatMap((Function<List<User>, ObservableSource<User>>) usersList -> {
                    return Observable.fromIterable(usersList); // returning user one by one from usersList.
                })
                .filter(user -> {
                    // filtering user who follows me.
                    return user.isFollowing;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(User user) {
                        // only the user who is following me comes here one by one
                        Log.d(TAG, "user : " + user.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.logError(TAG, e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });
    }


    /**
     * take Operator Example
     */

    public void take(View view) {
        // flatMap - to return users one by one
        getUserListObservable()
                .flatMap((Function<List<User>, ObservableSource<User>>) usersList -> {
                    return Observable.fromIterable(usersList); // returning user one by one from usersList.
                })
                .take(4) // it will only emit first 4 users out of all
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(User user) {
                        // // only four user comes here one by one
                        Log.d(TAG, "user : " + user.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.logError(TAG, e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });
    }


    /**
     * flatMap Operator Example
     */

    public void flatMap(View view) {
        // flatMap - to return users one by one
        getUserListObservable()
                .flatMap((Function<List<User>, ObservableSource<User>>) usersList -> {
                    return Observable.fromIterable(usersList); // returning user one by one from usersList.
                })
                .flatMap((Function<User, ObservableSource<UserDetail>>) user -> {
                    // here we get the user one by one
                    // and returns corresponding getUserDetailObservable
                    // for that userId
                    return getUserDetailObservable(user.id);
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserDetail>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Utils.logError(TAG, e);
                    }

                    @Override
                    public void onNext(UserDetail userDetail) {
                        // do anything with userDetail
                        Log.d(TAG, "userDetail : " + userDetail.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete");
                    }
                });
    }

    /**
     * flatMapWithZip Operator Example
     */

    private Observable<List<User>> getUserListObservable() {
        return Rx2AndroidNetworking.get("https://fierce-cove-29863.herokuapp.com/getAllUsers/{pageNumber}")
                .addPathParameter("pageNumber", "0")
                .addQueryParameter("limit", "10")
                .build()
                .getObjectListObservable(User.class);
    }

    private Observable<UserDetail> getUserDetailObservable(long id) {
        return Rx2AndroidNetworking.get("https://fierce-cove-29863.herokuapp.com/getAnUserDetail/{userId}")
                .addPathParameter("userId", String.valueOf(id))
                .build()
                .getObjectObservable(UserDetail.class);
    }

    public void flatMapWithZip(View view) {
        // flatMap - to return users one by one
        getUserListObservable()
                .flatMap((Function<List<User>, ObservableSource<User>>) usersList -> {
                    return Observable.fromIterable(usersList); // returning user one by one from usersList.
                })
                .flatMap((Function<User, ObservableSource<Pair<UserDetail, User>>>) user -> {
                    // here we get the user one by one and then we are zipping
                    // two observable - one getUserDetailObservable (network call to get userDetail)
                    // and another Observable.just(user) - just to emit user
                    return Observable.zip(getUserDetailObservable(user.id),
                            Observable.just(user),
                            (userDetail, user1) -> {
                                // runs when network call completes
                                // we get here userDetail for the corresponding user
                                return new Pair<>(userDetail, user1); // returning the pair(userDetail, user)
                            });
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Pair<UserDetail, User>>() {
                    @Override
                    public void onComplete() {
                        // do something onCompleted
                        Log.d(TAG, "onComplete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        // handle error
                        Utils.logError(TAG, e);
                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Pair<UserDetail, User> pair) {
                        // here we are getting the userDetail for the corresponding user one by one
                        UserDetail userDetail = pair.first;
                        User user = pair.second;
                        Log.d(TAG, "user : " + user.toString());
                        Log.d(TAG, "userDetail : " + userDetail.toString());
                    }
                });
    }
}
