package xyz.kkt.burpplefoodplaces.network.call;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;

import xyz.kkt.burpplefoodplaces.BurppleApp;
import xyz.kkt.burpplefoodplaces.events.RestApiEvents;
import xyz.kkt.burpplefoodplaces.network.responses.GetFeaturedResponse;
import xyz.kkt.burpplefoodplaces.network.responses.GetGuidesResponse;
import xyz.kkt.burpplefoodplaces.network.responses.GetPromotionsResponse;

/**
 * Created by Lenovo on 1/10/2018.
 */

public class UITestDataAgentImpl implements FoodDataAgent {

    private static final String PATH_OFFLINE_DATA = "psuedo-data";
    private static final String OFFLINE_PROMOTION = "get_offline_promotion.json";
    private static final String OFFLINE_GUIDE = "get_offline_guide.json";
    private static final String OFFLINE_FEATURED = "get_offline_featured.json";

    /**
     * Read text from assets folder.
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    private byte[] readJsonFile(Context context, String fileName) throws IOException {
        InputStream inStream = context.getAssets().open(fileName);
        int size = inStream.available();
        byte[] buffer = new byte[size];
        inStream.read(buffer);
        inStream.close();
        return buffer;
    }

    /**
     * @param fileName - name of Json File.
     * @return JSONObject from loaded file.
     * @throws IOException
     * @throws JSONException
     */
    public String loadOfflineData(Context context, String fileName) throws IOException, JSONException {
        byte[] buffer = readJsonFile(context, PATH_OFFLINE_DATA + "/" + fileName);
        return new String(buffer, "UTF-8").toString();
    }

    @Override
    public void loadPromotion(String accessToken, int pageNo, Context context) {
        Log.d(BurppleApp.LOG_TAG, "loading promotion from offline json");
        try {
            String offlineData = loadOfflineData(context, OFFLINE_PROMOTION);
            GetPromotionsResponse getPromotionsResponse = new Gson().fromJson(offlineData, GetPromotionsResponse.class);

            if (getPromotionsResponse != null) {
                RestApiEvents.PromotionDataLoadedEvent promotionDataLoadedEvent = new RestApiEvents.PromotionDataLoadedEvent(
                        getPromotionsResponse.getPageNo(), getPromotionsResponse.getPromotionList(), context);
                EventBus.getDefault().post(promotionDataLoadedEvent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadFeatured(String accessToken, int pageNo, Context context) {
        Log.d(BurppleApp.LOG_TAG, "loading featured from offline json");
        try {
            String offlineData = loadOfflineData(context, OFFLINE_FEATURED);
            GetFeaturedResponse getFeaturedResponse = new Gson().fromJson(offlineData, GetFeaturedResponse.class);

            if (getFeaturedResponse != null) {
                RestApiEvents.FeaturedDataLoadedEvent featuredDataLoadedEvent = new RestApiEvents.FeaturedDataLoadedEvent(
                        getFeaturedResponse.getPageNo(), getFeaturedResponse.getFeaturedList(), context);
                EventBus.getDefault().post(featuredDataLoadedEvent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadGuide(String accessToken, int pageNo, Context context) {
        Log.d(BurppleApp.LOG_TAG, "loading guide from offline json");
        try {
            String offlineData = loadOfflineData(context, OFFLINE_GUIDE);
            GetGuidesResponse getGuidesResponse = new Gson().fromJson(offlineData, GetGuidesResponse.class);

            if (getGuidesResponse != null) {
                RestApiEvents.GuideDataLoadedEvent guideDataLoadedEvent = new RestApiEvents.GuideDataLoadedEvent(
                        getGuidesResponse.getPageNo(), getGuidesResponse.getGuideVOList(), context);
                EventBus.getDefault().post(guideDataLoadedEvent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
