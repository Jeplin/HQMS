package com.jeplin.hackathonqm.Notification;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by jeplin on 23-09-2017.
 */

public class MyFirebaseInstantService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        Log.d("TOkem","Datat Token");

        String RECEIVE_TOKEN= FirebaseInstanceId.getInstance().getToken();
        Log.d("Token","Device Token - "+RECEIVE_TOKEN);

        SharedPreferences sharedPreferences=getSharedPreferences("device_token",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("device",RECEIVE_TOKEN);
        editor.apply();

    }
}
