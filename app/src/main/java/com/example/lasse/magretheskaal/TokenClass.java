package com.example.lasse.magretheskaal;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import static com.google.android.gms.internal.zzs.TAG;

/**
 * Created by Lasse on 07-12-2016.
 */

public class TokenClass extends FirebaseInstanceIdService {


    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        // TODO: Implement this method to send any registration to your app's servers.
        sendRegistrationToServer(refreshedToken);
        DTO_Message dto = new DTO_Message();
        dto.setToken(refreshedToken);
    }

    private void sendRegistrationToServer(String refreshedToken) {
    }

}
