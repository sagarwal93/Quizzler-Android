package com.quizzler.quizzler.http;

/**
 * Created by Samarth on 6/30/15.
 */
public interface AsyncResultHandler {
    public void onSuccess();
    public void onFailure();
}
