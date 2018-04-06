package com.example.congdao.dogotinhsau.ulti;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by Cong Dao on 3/18/2018.
 */

public class CheckConnection {
    public static boolean haveNetworkConnection(Context context) {

        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();

        for (NetworkInfo info : networkInfos) {
            if (info.getTypeName().equalsIgnoreCase("WIFI")) {
                if (info.isConnected())
                    haveConnectedWifi = true;
            }

            if (info.getTypeName().equalsIgnoreCase("MOBILE")) {
                if (info.isConnected())
                    haveConnectedMobile = true;
            }
        }

        return haveConnectedWifi || haveConnectedMobile;
    }

    public static void showToast(Context context, String notification) {
        Toast.makeText(context, notification, Toast.LENGTH_SHORT).show();
    }
}
