package xyz.kkt.burpplefoodplaces;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import javax.inject.Inject;

import xyz.kkt.burpplefoodplaces.dagger.AppComponent;
import xyz.kkt.burpplefoodplaces.dagger.AppModule;
import xyz.kkt.burpplefoodplaces.dagger.DaggerAppComponent;
import xyz.kkt.burpplefoodplaces.dagger.NetworkModule;
import xyz.kkt.burpplefoodplaces.dagger.UtilsModule;
import xyz.kkt.burpplefoodplaces.data.model.BurppleModel;
import xyz.kkt.burpplefoodplaces.utils.ConfigUtils;

/**
 * Created by Lenovo on 1/5/2018.
 */

public class BurppleApp extends Application {

    public static final String LOG_TAG = "Burpple App";

    private AppComponent mAppComponent;

    @Inject
    Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        //ConfigUtils.initConfigUtils(getApplicationContext());

        mAppComponent = initDagger();//dagger init
        mAppComponent.inject(this); //register consumer

        Log.d(LOG_TAG, "mContext : " + mContext);

    }


    private AppComponent initDagger() {
        //return null;
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .utilsModule(new UtilsModule())
                .networkModule(new NetworkModule())
                .build();
    }

    public AppComponent getBurppleAppComponent() {
        return mAppComponent;
    }

}
