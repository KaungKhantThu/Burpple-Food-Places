package xyz.kkt.burpplefoodplaces.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import xyz.kkt.burpplefoodplaces.BurppleApp;
import xyz.kkt.burpplefoodplaces.data.model.BurppleModel;
import xyz.kkt.burpplefoodplaces.mvp.presenters.MainPresenter;

/**
 * Created by Lenovo on 1/6/2018.
 */
@Module
public class AppModule {

    private BurppleApp mApp;

    public AppModule(BurppleApp application) {
        mApp = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return mApp.getApplicationContext();
    }

    @Provides
    @Singleton
    public BurppleModel provideBurppleModel(Context context) {
        return new BurppleModel(context);
    }

    @Provides
    public MainPresenter provideMainPresenter() {
        return new MainPresenter();
    }

}
