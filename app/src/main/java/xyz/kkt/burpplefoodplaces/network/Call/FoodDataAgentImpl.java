package xyz.kkt.burpplefoodplaces.network.Call;

import android.content.Context;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import xyz.kkt.burpplefoodplaces.events.RestApiEvents;
import xyz.kkt.burpplefoodplaces.network.responses.GetFeaturedResponse;
import xyz.kkt.burpplefoodplaces.network.responses.GetGuidesResponse;
import xyz.kkt.burpplefoodplaces.network.responses.GetPromotionsResponse;

/**
 * Created by Lenovo on 1/14/2018.
 */

public class FoodDataAgentImpl implements FoodDataAgent {

    private static FoodDataAgentImpl objInstance;

    private FoodAPI theAPI;

    private FoodDataAgentImpl() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://padcmyanmar.com/padc-3/burpple-food-places/apis/")
                .addConverterFactory(GsonConverterFactory.create(new Gson())) // addding gson converterfactory for retrofit to convert JSON to object
                .client(okHttpClient)
                .build();

        theAPI = retrofit.create(FoodAPI.class);//creating API instance (API instance is interface)

    }


    public static FoodDataAgentImpl getInstance() {
        if (objInstance == null) {
            objInstance = new FoodDataAgentImpl();
        }
        return objInstance;
    }


    @Override
    public void loadPromotion(String accessToken, int pageNo, final Context context) {
        Call<GetPromotionsResponse> loadPromotionsCall = theAPI.loadPromotions(accessToken, pageNo);
        loadPromotionsCall.enqueue(new FoodCallback<GetPromotionsResponse>() {
            @Override
            public void onResponse(Call<GetPromotionsResponse> call, Response<GetPromotionsResponse> response) {
                super.onResponse(call, response);//call parent method since parent is abstract class
                GetPromotionsResponse getPromotionsResponse = response.body();
                if (getPromotionsResponse != null) {
                    RestApiEvents.PromotionDataLoadedEvent promotionDataLoadedEvent = new RestApiEvents.PromotionDataLoadedEvent(
                            getPromotionsResponse.getPageNo(), getPromotionsResponse.getPromotionList(), context);
                    EventBus.getDefault().post(promotionDataLoadedEvent);
                }
            }
        });
    }

    @Override
    public void loadFeatured(String accessToken, int pageNo, final Context context) {
        Call<GetFeaturedResponse> loadFeaturedCall = theAPI.loadFeatured(accessToken, pageNo);
        loadFeaturedCall.enqueue(new FoodCallback<GetFeaturedResponse>() {
            @Override
            public void onResponse(Call<GetFeaturedResponse> call, Response<GetFeaturedResponse> response) {
                super.onResponse(call, response);//call parent method since parent is abstract class
                GetFeaturedResponse getFeaturedResponse = response.body();
                if (getFeaturedResponse != null) {
                    RestApiEvents.FeaturedDataLoadedEvent featuredDataLoadedEvent = new RestApiEvents.FeaturedDataLoadedEvent(
                            getFeaturedResponse.getPageNo(), getFeaturedResponse.getFeaturedList(), context);
                    EventBus.getDefault().post(featuredDataLoadedEvent);
                }
            }
        });
    }

    @Override
    public void loadGuide(String accessToken, int pageNo, final Context context) {
        Call<GetGuidesResponse> loadPromotionShopCall = theAPI.loadGuides(accessToken, pageNo);
        loadPromotionShopCall.enqueue(new FoodCallback<GetGuidesResponse>() {
            @Override
            public void onResponse(Call<GetGuidesResponse> call, Response<GetGuidesResponse> response) {
                super.onResponse(call, response);//call parent method since parent is abstract class
                GetGuidesResponse getGuidesResponse = response.body();
                if (getGuidesResponse != null) {
                    RestApiEvents.GuideDataLoadedEvent promotionDataLoadedEvent = new RestApiEvents.GuideDataLoadedEvent(
                            getGuidesResponse.getPageNo(), getGuidesResponse.getGuideVOList(), context);
                    EventBus.getDefault().post(promotionDataLoadedEvent);
                }
            }
        });
    }
}
