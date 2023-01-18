<img src=https://raw.githubusercontent.com/amitshekhariitbhu/RxJava2-Android-Samples/master/assets/rxjava2.png >

# Learning RxJava 2 for Android by example

### This project is for : 
* who is migrating to RxJava 2 
* or just started with RxJava.

## About me

Hi, I am [**Amit Shekhar**](https://amitshekhar.me), I have mentored many developers, and their efforts landed them high-paying tech jobs, helped many tech companies in solving their unique problems, and created many open-source libraries being used by top companies. I am passionate about sharing knowledge through open-source, blogs, and videos.

You can connect with me on:

- [Twitter](https://twitter.com/amitiitbhu)
- [YouTube](https://www.youtube.com/@amitshekhar)
- [LinkedIn](https://www.linkedin.com/in/amit-shekhar-iitbhu)
- [GitHub](https://github.com/amitshekhariitbhu)

## [My Personal Blog - amitshekhar.me](https://amitshekhar.me/blog) - High-quality content to learn Android concepts.

### Just Build the project and start learning RxJava by examples.

RxJava 2.0 has been completely rewritten from scratch on top of the Reactive-Streams specification. The specification itself has evolved out of RxJava 1.x and provides a common baseline for reactive systems and libraries.

### Using RxJava 2.0 Library in your application

Add this in your build.gradle
```groovy
compile 'io.reactivex.rxjava2:rxjava:X.X.X'
```
If you are using RxAndroid also, then add the following
```groovy
compile 'io.reactivex.rxjava2:rxandroid:X.X.X'
```

# RxJava 2 Operators Examples present in this sample project:

* `Map` -> transform the items emitted by an Observable by applying a function to each item. Blog: [RxJava Operator Map vs FlatMap](https://amitshekhar.me/blog/rxjava-map-vs-flatmap)
* `Zip` -> combine the emissions of multiple Observables together via a specified function and emit single items for each combination based on the results of this function
* `Filter` -> emit only those items from an Observable that pass a predicate test
* `FlatMap` -> transform the items emitted by an Observable into Observables, then flatten the emissions from those into a single Observable. Blog: [RxJava Operator Map vs FlatMap](https://amitshekhar.me/blog/rxjava-map-vs-flatmap)
* `Take` -> emit only the first n items emitted by an Observable. [Blog for reference](https://amitshekhar.me/blog/rxjava-interval-operator)
* `Reduce` -> apply a function to each item emitted by an Observable, sequentially, and emit the final value
* `Skip` -> suppress the first n items emitted by an Observable
* `Buffer` -> periodically gather items emitted by an Observable into bundles and emit these bundles rather than emitting the items one at a time
* `Concat` -> emit the emissions from two or more Observables without interleaving them. [Blog for reference](https://amitshekhar.me/blog/rxjava-concat-operator)
* `Replay` -> ensure that all observers see the same sequence of emitted items, even if they subscribe after the Observable has begun emitting items
* `Merge` -> combine multiple Observables into one by merging their emissions
* `SwitchMap` -> transform the items emitted by an Observable into Observables, and mirror those items emitted by the most-recently transformed Observable


# Highlights of the examples :

* [DisposableExampleActivity](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/operators/DisposableExampleActivity.java) - Using `CompositeDisposable`. [Blog for reference](https://amitshekhar.me/blog/dispose-vs-clear-compositedisposable-rxjava)
* [FlowableExampleActivity](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/operators/FlowableExampleActivity.java) - Using `Flowable` and `reduce` operator
* [SingleObserverExampleActivity](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/operators/SingleObserverExampleActivity.java) - Using `SingleObserver`
* [CompletableObserverActivity](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/operators/CompletableObserverExampleActivity.java) - Using `CompletableObserver`
* [MapExampleActivity](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/operators/MapExampleActivity.java) - Using `map` Operator. Blog: [RxJava Operator Map vs FlatMap](https://amitshekhar.me/blog/rxjava-map-vs-flatmap)
* [ZipExampleActivity](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/operators/ZipExampleActivity.java) - Using `zip` Operator
* [BufferExampleActivity](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/operators/BufferExampleActivity.java) - Using `buffer` Operator
* [TakeExampleActivity](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/operators/TakeExampleActivity.java) - Using `take` Operator. [Blog for reference](https://amitshekhar.me/blog/rxjava-interval-operator)
* [ReduceExampleActivity](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/operators/ReduceExampleActivity.java) - Using `reduce` Operator
* [FilterExampleActivity](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/operators/FilterExampleActivity.java) - Using `filter` Operator
* [SkipExampleActivity](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/operators/SkipExampleActivity.java) - Using `skip` Operator
* [ReplayExampleActivity](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/operators/ReplayExampleActivity.java) - Using `replay` Operator
* [ConcatExampleActivity](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/operators/ConcatExampleActivity.java) - Using `concat` Operator. [Blog for reference](https://amitshekhar.me/blog/rxjava-concat-operator)
* [MergeExampleActivity](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/operators/MergeExampleActivity.java) - Using `merge` Operator
* [DeferExampleActivity](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/operators/DeferExampleActivity.java) - Using `defer` Observable
* [SwitchMapExampleActivity](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/operators/SwitchMapExampleActivity.java) - Using `switchMap` Observable
* [IntervalExampleActivity](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/operators/IntervalExampleActivity.java) - Using `Interval`. [Blog for reference](https://amitshekhar.me/blog/rxjava-interval-operator)
* [RxBusActivity](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/rxbus/RxBusActivity.java) - RxBus, RxJava2Bus, EventBus, RxEventBus
* [PaginationActivity](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/pagination/PaginationActivity.java) - Pagination for loadMore in RecyclerView. Blog: [Pagination In RecyclerView Using RxJava Operators](https://amitshekhar.me/blog/pagination-in-recyclerview-using-rxjava-operators)
* [ComposeOperatorExampleActivity](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/compose/ComposeOperatorExampleActivity.java) - Compose operator for reusable
* [Search Implementation](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/search/SearchActivity.java) - Using `debounce`, `switchMap`, `distinctUntilChanged`
* [Implement Caching Using RxJava Operators](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/cache/CacheExampleActivity.java) - Using `concat`, `firstElement`
* [PublishSubjectExampleActivity](https://github.com/amitshekhariitbhu/RxJava2-Android-Samples/blob/master/app/src/main/java/com/rxjava2/android/samples/ui/operators/PublishSubjectExampleActivity.java). Blog: [RxJava Subject - Publish, Replay, Behavior, and Async](https://amitshekhar.me/blog/rxjava-subject-publish-replay-behavior-async)

### Find this project useful ? :heart:
* Support it by clicking the :star: button on the upper right of this page. :v:

Thanks

[**Amit Shekhar**](https://amitshekhar.me)

You can connect with me on:

- [Twitter](https://twitter.com/amitiitbhu)
- [LinkedIn](https://www.linkedin.com/in/amit-shekhar-iitbhu)
- [GitHub](https://github.com/amitshekhariitbhu)
- [Facebook](https://www.facebook.com/amit.shekhar.iitbhu)

[**Read all of my blogs here.**](https://amitshekhar.me/blog)

### License
```
   Copyright (C) 2022 Amit Shekhar

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
 
