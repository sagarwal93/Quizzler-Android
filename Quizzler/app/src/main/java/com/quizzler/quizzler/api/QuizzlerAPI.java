package com.quizzler.quizzler.api;
import android.util.Log;

import com.quizzler.quizzler.BuildConfig;
import com.quizzler.quizzler.http.*;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Samarth on 6/30/15.
 */
public class QuizzlerAPI {

    private static final String BASE_URL = BuildConfig.api_url;

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void login(final String username, final String password, final AsyncResultHandler handler) {
        try {
            JSONObject data = new JSONObject();
            data.put("username", username);
            data.put("password", password);
            client.post(BASE_URL + "/login", data, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, JSONObject result) {
                    if (statusCode == 200) {
                        Log.d("JSONResult: ", result.toString());
                        handler.onSuccess();
                    } else {
                        handler.onFailure();
                    }
                }
            });
        }
        catch (JSONException e) {

        }
    }

}
