package com.rxjava2.android.samples.ui.cache.model;

public class Data {

    public String source;

    @SuppressWarnings("CloneDoesntDeclareCloneNotSupportedException")
    @Override
    public Data clone() {
        return new Data();
    }
}
