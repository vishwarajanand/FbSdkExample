package com.vishwarajanand.api_build_thirdparty;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.AppEventsLogger;

//import android.widget.TextView;
//import com.facebook.CallbackManager;
//import com.facebook.login.widget.LoginButton;

public class Home extends AppCompatActivity {
    private static final String TAG = "HOME_ACTIVITY";
    public static final String EXTRA_MESSAGE = "com.vishwarajanand.api_build_thirdparty.UserProfileActivity.MESSAGE";
//    private CallbackManager callbackManager;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "Result code is " + resultCode + " and data is " + data.toString());
//        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Register login callback
        registerCallback();
    }

    public void sendMessage(View view) {
        this.logFBEvent(AppEventsConstants.EVENT_NAME_ADDED_TO_CART, "RandomContentType", "RandomContentData", "RandomContentId", "INR", (Math.random() * 100.0));
    }

    private void registerCallback() {
        Log.i(TAG, "Starting callback register!");

//        callbackManager = CallbackManager.Factory.create();

//        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
//        final TextView displayText = (TextView) findViewById(R.id.displayText);
//        loginButton.setReadPermissions("public_profile", "email");
        // If you are using in a fragment, call loginButton.setFragment(this);

//        // Callback registration
//        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                // App code
//                Log.d(TAG, "SUCCESS: " + loginResult.getAccessToken());
//                displayText.setText("Logged in user id: " + loginResult.getAccessToken().getUserId());
//            }
//
//            @Override
//            public void onCancel() {
//                // App code
//                Log.d(TAG, "CANCEL");
//                displayText.setText("Login cancelled, try again!");
//            }
//
//            @Override
//            public void onError(FacebookException exception) {
//                // App code
//                Log.d(TAG, "ERROR: " + exception.getStackTrace());
//                displayText.setText("Failed to login!");
//            }
//        });

        Log.i(TAG, "Callback registered!");
    }

    /**
     * This function assumes logger is an instance of AppEventsLogger and has been
     * created using AppEventsLogger.newLogger() call.
     */
    public void logFBEvent(String eventName, String contentType, String contentData, String contentId, String currency, double price) {
        Log.i(TAG, "Attempting to fire event!");
        Bundle params = new Bundle();
        params.putString(AppEventsConstants.EVENT_PARAM_CONTENT_TYPE, contentType);
        params.putString(AppEventsConstants.EVENT_PARAM_CONTENT, contentData);
        params.putString(AppEventsConstants.EVENT_PARAM_CONTENT_ID, contentId);
        params.putString(AppEventsConstants.EVENT_PARAM_CURRENCY, currency);
        AppEventsLogger logger = AppEventsLogger.newLogger(this);
        logger.logEvent(eventName, price, params);
        Log.i(TAG, "Event Fired Successfully!");
    }
}
