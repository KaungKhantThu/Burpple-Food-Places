package xyz.kkt.burpplefoodplaces;

import android.app.Application;

import xyz.kkt.burpplefoodplaces.utils.ConfigUtils;

/**
 * Created by Lenovo on 1/5/2018.
 */

public class BurppleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ConfigUtils.initConfigUtils(getApplicationContext());

    }
}
