package xyz.kkt.burpplefoodplaces.dagger;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import xyz.kkt.burpplefoodplaces.network.call.FoodDataAgent;
import xyz.kkt.burpplefoodplaces.network.call.FoodDataAgentImpl;

/**
 * Created by Lenovo on 1/10/2018.
 */
@Module
public class NetworkModule {

    @Provides
    @Singleton
    public FoodDataAgent provideFoodDataAgent(Context context) {
        return new FoodDataAgentImpl();
    }

}
