package xyz.kkt.burpplefoodplaces.network.call;

import android.content.Context;

/**
 * Created by Lenovo on 1/14/2018.
 */

public interface FoodDataAgent {

    void loadPromotion(String accessToken, int pageNo, Context context);

    void loadFeatured(String accessToken, int pageNo, Context context);

    void loadGuide(String accessToken, int pageNo, Context context);

}
