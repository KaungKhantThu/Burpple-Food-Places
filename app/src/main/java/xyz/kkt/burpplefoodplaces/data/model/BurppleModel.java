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
import xyz.kkt.burpplefoodplaces.utils.ConfigUtils;

/**
 * Created by Lenovo on 1/5/2018.
 */

public class BurppleModel {

    private static BurppleModel objInstance;
    private FoodDataAgentImpl mDataAgent;
    private List<PromotionVO> mPromotionList;
    private List<FeaturedVO> mFeaturedList;
    private List<GuideVO> mGuideList;

    private BurppleModel() {
        EventBus.getDefault().register(this);
        mDataAgent = FoodDataAgentImpl.getInstance();
        mPromotionList = new ArrayList<>();
        mGuideList = new ArrayList<>();
        mFeaturedList = new ArrayList<>();
    }

    public static BurppleModel getInstance() {
        if (objInstance == null) {
            objInstance = new BurppleModel();
        }
        return objInstance;
    }

    public void startLoadingFood(Context context) {
        startLoadingPromotion(context);
        startLoadingGuide(context);
        startLoadingFeatured(context);
    }

    public void startLoadingPromotion(Context context) {
        mDataAgent.loadPromotion(AppConstants.ACCESS_TOKEN, ConfigUtils.getObjInstance().loadProPageIndex(), context);
    }

    public void startLoadingGuide(Context context) {
        mDataAgent.loadGuide(AppConstants.ACCESS_TOKEN, ConfigUtils.getObjInstance().loadGuiPageIndex(), context);
    }

    public void startLoadingFeatured(Context context) {
        mDataAgent.loadFeatured(AppConstants.ACCESS_TOKEN, ConfigUtils.getObjInstance().loadFeaPageIndex(), context);
    }

    public void loadMorePromotion(Context context) {
        int pageIndex = ConfigUtils.getObjInstance().loadProPageIndex();
        mDataAgent.loadPromotion(AppConstants.ACCESS_TOKEN, pageIndex, context);
    }

    public void loadMoreGuide(Context context) {
        int pageIndex = ConfigUtils.getObjInstance().loadGuiPageIndex();
        mDataAgent.loadGuide(AppConstants.ACCESS_TOKEN, pageIndex, context);
    }

    public void loadMoreFeatured(Context context) {
        int pageIndex = ConfigUtils.getObjInstance().loadFeaPageIndex();
        mDataAgent.loadFeatured(AppConstants.ACCESS_TOKEN, pageIndex, context);
    }

    public void forceRefreshPromotion(Context context) {
        mPromotionList = new ArrayList<>();
        ConfigUtils.getObjInstance().saveProPageIndex(1);
        startLoadingPromotion(context);
    }

    public void forceRefreshGuide(Context context) {
        mGuideList = new ArrayList<>();
        ConfigUtils.getObjInstance().saveGuiPageIndex(1);
        startLoadingGuide(context);
    }


    public void forceRefreshFeatured(Context context) {
        mFeaturedList = new ArrayList<>();
        ConfigUtils.getObjInstance().saveFeaPageIndex(1);
        startLoadingFeatured(context);
    }


    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onPromotionDataLoaded(RestApiEvents.PromotionDataLoadedEvent event) {
        ConfigUtils.getObjInstance().saveProPageIndex(event.getLoadedPageIndex() + 1);
        mPromotionList = event.getLoadPromotion();

    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onFeaturedDataLoaded(RestApiEvents.FeaturedDataLoadedEvent event) {
        ConfigUtils.getObjInstance().saveFeaPageIndex(event.getLoadedPageIndex() + 1);
        mFeaturedList = event.getLoadFeatured();

    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onGuideDataLoadedl(RestApiEvents.GuideDataLoadedEvent event) {
        ConfigUtils.getObjInstance().saveGuiPageIndex(event.getLoadedPageIndex() + 1);
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
