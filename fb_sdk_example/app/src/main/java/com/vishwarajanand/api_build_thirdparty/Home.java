package com.vishwarajanand.api_build_thirdparty;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class Home extends AppCompatActivity {
    private static final String TAG = "HOME_ACTIVITY";
    public static final String EXTRA_MESSAGE = "com.vishwarajanand.api_build_thirdparty.UserProfileActivity.MESSAGE";
    private CallbackManager callbackManager;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "Result code is " + resultCode + " and data is " + data.toString());
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Register login callback
        registerCallback();
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, UserProfileActivity.class);
        String message = ((TextView) findViewById(R.id.displayText)).getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    private void registerCallback(){
        Log.d(TAG, "Starting callback register!");

        callbackManager = CallbackManager.Factory.create();

        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        final TextView displayText = (TextView) findViewById(R.id.displayText);
        loginButton.setReadPermissions("public_profile", "email");
        // If you are using in a fragment, call loginButton.setFragment(this);

        // Callback registration
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Log.d(TAG, "SUCCESS: " + loginResult.getAccessToken());
                displayText.setText("Logged in user id: " + loginResult.getAccessToken().getUserId());
            }

            @Override
            public void onCancel() {
                // App code
                Log.d(TAG, "CANCEL");
                displayText.setText("Login cancelled, try again!");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.d(TAG, "ERROR: " + exception.getStackTrace());
                displayText.setText("Failed to login!");
            }
        });

        Log.d(TAG, "Callback registered!");
    }
}
