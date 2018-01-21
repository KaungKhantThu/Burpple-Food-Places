package xyz.kkt.burpplefoodplaces.dagger;

import javax.inject.Singleton;

import dagger.Component;
import xyz.kkt.burpplefoodplaces.BurppleApp;
import xyz.kkt.burpplefoodplaces.activities.MainActivity;
import xyz.kkt.burpplefoodplaces.data.model.BurppleModel;
import xyz.kkt.burpplefoodplaces.mvp.presenters.MainPresenter;

/**
 * Created by Lenovo on 1/6/2018.
 */

@Component(modules = {AppModule.class, UtilsModule.class, NetworkModule.class})
@Singleton
public interface AppComponent {

    void inject(BurppleApp app);

    void inject(BurppleModel burppleModel);

    void inject(MainPresenter mainPresenter);

    void inject(MainActivity mainActivity);

}
