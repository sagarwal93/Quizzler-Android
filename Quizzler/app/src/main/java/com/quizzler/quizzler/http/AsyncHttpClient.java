package com.quizzler.quizzler.http;
import android.os.AsyncTask;
import android.util.Log;

import com.quizzler.quizzler.BuildConfig;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by Samarth on 7/1/15.
 */
public class AsyncHttpClient {

    private DefaultHttpClient client;
    private int timeout;

    public AsyncHttpClient() {

        client = new DefaultHttpClient();
        timeout = 10000;

        BasicHttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, this.timeout);
    }

    public void post(final String url, final JSONObject data, final AsyncHttpResponseHandler handler) {

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    HttpPost post = new HttpPost(url);
                    StringEntity se = new StringEntity(data.toString(), HTTP.UTF_8);
                    post.setEntity(se);

                    HttpResponse responseHttp = client.execute(post);
                    String responseData = EntityUtils.toString(responseHttp.getEntity());
                    JSONObject responseJSON = new JSONObject(responseData);
                    handler.onSuccess(responseHttp.getStatusLine().getStatusCode(), responseJSON);

                }
                catch (UnsupportedEncodingException e) {

                }
                catch (ClientProtocolException e) {

                }
                catch (IOException e) {

                }
                catch (JSONException e) {

                }
                return null;
            }
        }.execute();
    }

    public void setConnectTimeout(int value) {
        this.timeout = value < 1000?10000:value;
        HttpParams httpParams = this.client.getParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, this.timeout);
    }

}
