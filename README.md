<img src=https://raw.githubusercontent.com/amitshekhariitbhu/RxJava2-Android-Samples/master/assets/rxjava2.png >

# Learning RxJava 2 for Android by example

[![Mindorks](https://img.shields.io/badge/mindorks-opensource-blue.svg)](https://mindorks.com/open-source-projects)
[![Mindorks Community](https://img.shields.io/badge/join-community-blue.svg)](https://mindorks.com/join-community)
[![Mindorks Android Store](https://img.shields.io/badge/Mindorks%20Android%20Store-RxJava2%20Android%20Samples-blue.svg?style=flat)](https://mindorks.com/android/store)
[![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.svg?v=102)](https://opensource.org/licenses/Apache-2.0)
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/LICENSE)

### Get the complete RxJava Course [here](https://mindorks.com/course/learn-rxjava)

## How to use RxJava 2 in Android Application
## How to migrate from RxJava 1.0 to RxJava 2.0

### This project is for : 
* who is migrating to RxJava 2 
* or just started with RxJava.

### Just Build the project and start learning RxJava by examples.

RxJava 2.0 has been completely rewritten from scratch on top of the Reactive-Streams specification. The specification itself has evolved out of RxJava 1.x and provides a common baseline for reactive systems and libraries.

Because Reactive-Streams has a different architecture, it mandates changes to some well known RxJava types.


# Migration From RxJava 1.0 to RxJava 2.0

To allow having RxJava 1 and RxJava 2 side-by-side, RxJava 2 is under the maven coordinates 
io.reactivex.rxjava2:rxjava:2.x.y and classes are accessible below io.reactivex.

Users switching from 1.x to 2.x have to re-organize their imports, but carefully.

### Using RxJava 2.0 Library in your application

Add this in your build.gradle
```groovy
compile 'io.reactivex.rxjava2:rxjava:2.2.2'
```
If you are using RxAndroid also, then add the following
```groovy
compile 'io.reactivex.rxjava2:rxandroid:2.1.0'
```

# RxJava 2 Examples present in this sample project

* RxJava 2.0 Example using `CompositeDisposable` as `CompositeSubscription` and `Subscription` have
been removed.

* RxJava 2 Example using `Flowable`.

* RxJava 2 Example using `SingleObserver`, `CompletableObserver`.

* RxJava 2 Example using RxJava2 operators such as `map, zip, take, reduce, flatMap, filter, buffer, skip, merge, concat, replay`, and much more:

* RxJava 2 Android Samples using `Function` as `Func1` has been removed.

* RxJava 2 Android Samples  using `BiFunction` as `Func2` has been removed.

* And many others android examples

# Quick Look on few changes done in RxJava2 over RxJava1

RxJava1 -> RxJava2

* `onCompleted` -> `onComplete` - without the trailing d
* `Func1` -> `Function`
* `Func2` -> `BiFunction`
* `CompositeSubscription` -> `CompositeDisposable`
* `limit` operator has been removed - Use `take` in RxJava2
* and much more.

# Operators :
* `Map` -> transform the items emitted by an Observable by applying a function to each item
* `Zip` -> combine the emissions of multiple Observables together via a specified function and emit single items for each combination based on the results of this function
* `Filter` -> emit only those items from an Observable that pass a predicate test
* `FlatMap` -> transform the items emitted by an Observable into Observables, then flatten the emissions from those into a single Observable
* `Take` -> emit only the first n items emitted by an Observable
* `Reduce` -> apply a function to each item emitted by an Observable, sequentially, and emit the final value
* `Skip` -> suppress the first n items emitted by an Observable
* `Buffer` -> periodically gather items emitted by an Observable into bundles and emit these bundles rather than emitting the items one at a time
* `Concat` -> emit the emissions from two or more Observables without interleaving them
* `Replay` -> ensure that all observers see the same sequence of emitted items, even if they subscribe after the Observable has begun emitting items
* `Merge` -> combine multiple Observables into one by merging their emissions
* `SwitchMap` -> ransform the items emitted by an Observable into Observables, and mirror those items emitted by the most-recently transformed Observable


# Highlights of the examples :

* [DisposableExampleActivity](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/operators/DisposableExampleActivity.java) - Using `CompositeDisposable`
* [FlowableExampleActivity](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/operators/FlowableExampleActivity.java) - Using `Flowable` and `reduce` operator
* [SingleObserverExampleActivity](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/operators/SingleObserverExampleActivity.java) - Using `SingleObserver`
* [CompletableObserverActivity](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/operators/CompletableObserverExampleActivity.java) - Using `CompletableObserver`
* [MapExampleActivity](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/operators/MapExampleActivity.java) - Using `map` Operator
* [ZipExampleActivity](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/operators/ZipExampleActivity.java) - Using `zip` Operator
* [BufferExampleActivity](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/operators/BufferExampleActivity.java) - Using `buffer` Operator
* [TakeExampleActivity](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/operators/TakeExampleActivity.java) - Using `take` Operator
* [ReduceExampleActivity](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/operators/ReduceExampleActivity.java) - Using `reduce` Operator
* [FilterExampleActivity](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/operators/FilterExampleActivity.java) - Using `filter` Operator
* [SkipExampleActivity](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/operators/SkipExampleActivity.java) - Using `skip` Operator
* [ReplayExampleActivity](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/operators/ReplayExampleActivity.java) - Using `replay` Operator
* [ConcatExampleActivity](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/operators/ConcatExampleActivity.java) - Using `concat` Operator
* [MergeExampleActivity](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/operators/MergeExampleActivity.java) - Using `merge` Operator
* [DeferExampleActivity](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/operators/DeferExampleActivity.java) - Using `defer` Observable
* [SwitchMapExampleActivity](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/operators/SwitchMapExampleActivity.java) - Using `switchMap` Observable
* [IntervalExampleActivity](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/operators/IntervalExampleActivity.java) - Using `Interval`
* [RxBusActivity](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/rxbus/RxBusActivity.java) - RxBus, RxJava2Bus, EventBus, RxEventBus, [Blog for reference](https://blog.mindorks.com/implementing-eventbus-with-rxjava-rxbus-e6c940a94bd8)
* [PaginationActivity](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/pagination/PaginationActivity.java) - Pagination for loadMore in RecyclerView
* [ComposeOperatorExampleActivity](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/compose/ComposeOperatorExampleActivity.java) - Compose operator for reusable
* [Search Implementation](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/search/SearchActivity.java) - Using `debounce`, `switchMap`, `distinctUntilChanged`, [Blog for reference](https://blog.mindorks.com/implement-search-using-rxjava-operators-c8882b64fe1d)
* [PublishSubjectExampleActivity](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/operators/PublishSubjectExampleActivity.java)

### TODO 

* Many examples are to be added 

### Find this project useful ? :heart:
* Support it by clicking the :star: button on the upper right of this page. :v:

### Check out an awesome MVP architecture based project which uses RxJava2, Dagger2.
* [Android-MVP-Architecture](https://github.com/MindorksOpenSource/android-mvp-architecture)

### Check out an awesome Kotlin MVP architecture based project which uses RxJava2, Dagger2.
* [Android-Kotlin-MVP-Architecture](https://github.com/MindorksOpenSource/android-kotlin-mvp-architecture)

### Check out an awesome library for fast and simple networking in Android.
* [Fast Android Networking Library](https://github.com/amitshekhariitbhu/Fast-Android-Networking)

### Another awesome library for debugging databases and shared preferences.
* [Android Debug Database](https://github.com/amitshekhariitbhu/Android-Debug-Database)

### [Check out Mindorks awesome open source projects here](https://mindorks.com/open-source-projects)

### Contact - Let's become friend
- [Twitter](https://twitter.com/amitiitbhu)
- [Github](https://github.com/amitshekhariitbhu)
- [Medium](https://medium.com/@amitshekhar)
- [Facebook](https://www.facebook.com/amit.shekhar.iitbhu)

### License
```
   Copyright (C) 2016 Amit Shekhar
   Copyright (C) 2011 Android Open Source Project

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```

### Contributing to RxJava 2 Android Samples
Just make pull request. You are in!

 
