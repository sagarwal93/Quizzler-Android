package com.quizzler.quizzler.ui;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.quizzler.quizzler.R;
import com.quizzler.quizzler.api.QuizzlerAPI;
import com.quizzler.quizzler.http.AsyncResultHandler;


public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void loginButtonClick(View view) {
        String username = ((EditText) findViewById(R.id.username_field)).getText().toString();
        String password = ((EditText) findViewById(R.id.password_field)).getText().toString();

        QuizzlerAPI.login(username, password, new AsyncResultHandler() {
            @Override
            public void onSuccess() {
                Log.d("Login: ", "Success");
            }

            @Override
            public void onFailure() {
                Log.d("Login: ", "Failure");
            }
        });
    }
}
