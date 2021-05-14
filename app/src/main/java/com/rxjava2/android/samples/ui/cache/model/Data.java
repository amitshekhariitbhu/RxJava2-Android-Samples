package com.rxjava2.android.samples.ui.cache.model;

import androidx.annotation.NonNull;

public class Data {

    public String source;

    @NonNull
    @SuppressWarnings("CloneDoesntDeclareCloneNotSupportedException")
    @Override
    public Data clone() {
        return new Data();
    }
}
