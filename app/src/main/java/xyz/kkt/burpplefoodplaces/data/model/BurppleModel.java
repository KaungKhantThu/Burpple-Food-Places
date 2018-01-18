package xyz.kkt.burpplefoodplaces.data.model;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import xyz.kkt.burpplefoodplaces.data.vos.FeaturedVO;
import xyz.kkt.burpplefoodplaces.data.vos.GuideVO;
import xyz.kkt.burpplefoodplaces.data.vos.PromotionVO;
import xyz.kkt.burpplefoodplaces.events.RestApiEvents;
import xyz.kkt.burpplefoodplaces.network.Call.FoodDataAgent;
import xyz.kkt.burpplefoodplaces.network.Call.FoodDataAgentImpl;
import xyz.kkt.burpplefoodplaces.utils.AppConstants;

/**
 * Created by Lenovo on 1/5/2018.
 */

public class BurppleModel {

    private static BurppleModel objInstance;
    private FoodDataAgentImpl mDataAgent;
    private List<PromotionVO> mPromotionList;
    private List<FeaturedVO> mFeaturedList;
    private List<GuideVO> mGuideList;

    private int pageIndexPro = 1;
    private int pageIndexFea = 1;
    private int pageIndexGui = 1;

    private BurppleModel() {
        EventBus.getDefault().register(this);
        mDataAgent = FoodDataAgentImpl.getInstance();
    }

    public static BurppleModel getInstance() {
        if (objInstance == null) {
            objInstance = new BurppleModel();
        }
        return objInstance;
    }

    public void startLoadingFood(Context context) {
        mDataAgent.loadPromotion(AppConstants.ACCESS_TOKEN, pageIndexPro, context);
        mDataAgent.loadFeatured(AppConstants.ACCESS_TOKEN, pageIndexFea, context);
        mDataAgent.loadGuide(AppConstants.ACCESS_TOKEN, pageIndexGui, context);
    }

    public void loadMoreFood(Context context) {
        // int pageIndex = ConfigUtils.getObjInstance().loadPageIndex();
        mDataAgent.loadPromotion(AppConstants.ACCESS_TOKEN, pageIndexPro, context);
        mDataAgent.loadFeatured(AppConstants.ACCESS_TOKEN, pageIndexFea, context);
        mDataAgent.loadGuide(AppConstants.ACCESS_TOKEN, pageIndexGui, context);
    }

    public void forceRefreshFood(Context context) {
        // ConfigUtils.getObjInstance().savePageIndex(1);
        pageIndexPro = 1;
        pageIndexFea = 1;
        pageIndexGui = 1;
        startLoadingFood(context);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onPromotionDataLoaded(RestApiEvents.PromotionDataLoadedEvent event) {
        //ConfigUtils.getObjInstance().savePageIndex(event.getLoadedPageIndex() + 1);
        pageIndexPro++;
        mPromotionList = event.getLoadPromotion();

    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onFeaturedDataLoaded(RestApiEvents.FeaturedDataLoadedEvent event) {
        //ConfigUtils.getObjInstance().savePageIndex(event.getLoadedPageIndex() + 1);
        pageIndexFea++;
        mFeaturedList = event.getLoadFeatured();

    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onGuideDataLoadedl(RestApiEvents.GuideDataLoadedEvent event) {
        //ConfigUtils.getObjInstance().savePageIndex(event.getLoadedPageIndex() + 1);
        pageIndexGui++;
        mGuideList = event.getLoadGuide();

    }

    public List<PromotionVO> getmPromotionList() {
        return mPromotionList;
    }

    public List<FeaturedVO> getmFeaturedList() {
        return mFeaturedList;
    }

    public List<GuideVO> getmGuideList() {
        return mGuideList;
    }
}
