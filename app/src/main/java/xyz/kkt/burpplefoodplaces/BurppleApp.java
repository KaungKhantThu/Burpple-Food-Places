package xyz.kkt.burpplefoodplaces;

import android.app.Application;

import xyz.kkt.burpplefoodplaces.utils.ConfigUtils;

/**
 * Created by Lenovo on 1/5/2018.
 */

public class BurppleApp extends Application {

    public static final String LOG_TAG = "Burpple App";

    @Override
    public void onCreate() {
        super.onCreate();

        ConfigUtils.initConfigUtils(getApplicationContext());

    }
}
