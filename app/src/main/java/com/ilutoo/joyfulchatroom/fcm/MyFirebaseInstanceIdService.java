package com.ilutoo.joyfulchatroom.fcm;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;

import static android.service.controls.ControlsProviderService.TAG;

public class MyFirebaseInstanceIdService extends FirebaseMessagingService {

    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);
        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String refreshToken) {
    }
}
