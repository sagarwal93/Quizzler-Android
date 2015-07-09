package com.quizzler.quizzler.http;
import org.json.JSONObject;

/**
 * Created by Samarth on 7/2/15.
 */
public interface AsyncHttpResponseHandler {

    public void onSuccess(int var1, JSONObject var2);

}
