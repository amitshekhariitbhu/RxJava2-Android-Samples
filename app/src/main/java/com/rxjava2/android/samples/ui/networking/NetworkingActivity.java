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
                .map(new Function<ApiUser, User>() {
                    @Override
                    public User apply(ApiUser apiUser) {
                        // here we get ApiUser from server
                        User user = new User(apiUser);
                        // then by converting, we are returning user
                        return user;
                    }
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
                new BiFunction<List<User>, List<User>, List<User>>() {
                    @Override
                    public List<User> apply(List<User> cricketFans, List<User> footballFans) {
                        List<User> userWhoLovesBoth =
                                filterUserWhoLovesBoth(cricketFans, footballFans);
                        return userWhoLovesBoth;
                    }
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
        getAllMyFriendsObservable()
                .flatMap(new Function<List<User>, ObservableSource<User>>() { // flatMap - to return users one by one
                    @Override
                    public ObservableSource<User> apply(List<User> usersList) {
                        return Observable.fromIterable(usersList); // returning user one by one from usersList.
                    }
                })
                .filter(new Predicate<User>() {
                    @Override
                    public boolean test(User user) {
                        // filtering user who follows me.
                        return user.isFollowing;
                    }
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
        getUserListObservable()
                .flatMap(new Function<List<User>, ObservableSource<User>>() { // flatMap - to return users one by one
                    @Override
                    public ObservableSource<User> apply(List<User> usersList) {
                        return Observable.fromIterable(usersList); // returning user one by one from usersList.
                    }
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
        getUserListObservable()
                .flatMap(new Function<List<User>, ObservableSource<User>>() { // flatMap - to return users one by one
                    @Override
                    public ObservableSource<User> apply(List<User> usersList) {
                        return Observable.fromIterable(usersList); // returning user one by one from usersList.
                    }
                })
                .flatMap(new Function<User, ObservableSource<UserDetail>>() {
                    @Override
                    public ObservableSource<UserDetail> apply(User user) {
                        // here we get the user one by one
                        // and returns corresponding getUserDetailObservable
                        // for that userId
                        return getUserDetailObservable(user.id);
                    }
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
        getUserListObservable()
                .flatMap(new Function<List<User>, ObservableSource<User>>() { // flatMap - to return users one by one
                    @Override
                    public ObservableSource<User> apply(List<User> usersList) {
                        return Observable.fromIterable(usersList); // returning user one by one from usersList.
                    }
                })
                .flatMap(new Function<User, ObservableSource<Pair<UserDetail, User>>>() {
                    @Override
                    public ObservableSource<Pair<UserDetail, User>> apply(User user) {
                        // here we get the user one by one and then we are zipping
                        // two observable - one getUserDetailObservable (network call to get userDetail)
                        // and another Observable.just(user) - just to emit user
                        return Observable.zip(getUserDetailObservable(user.id),
                                Observable.just(user),
                                new BiFunction<UserDetail, User, Pair<UserDetail, User>>() {
                                    @Override
                                    public Pair<UserDetail, User> apply(UserDetail userDetail, User user) {
                                        // runs when network call completes
                                        // we get here userDetail for the corresponding user
                                        return new Pair<>(userDetail, user); // returning the pair(userDetail, user)
                                    }
                                });
                    }
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
