package com.rxjava2.android.samples.model

/**
 * Created by amitshekhar on 27/08/16.
 */

data class User(var id: Long = 0L,
                var firstname: String,
                var lastname: String,
                var isFollowing: Boolean = false)
