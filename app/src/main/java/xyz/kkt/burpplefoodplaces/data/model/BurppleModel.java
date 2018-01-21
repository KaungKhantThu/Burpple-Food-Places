package xyz.kkt.burpplefoodplaces.data.model;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import xyz.kkt.burpplefoodplaces.BurppleApp;
import xyz.kkt.burpplefoodplaces.data.vos.FeaturedVO;
import xyz.kkt.burpplefoodplaces.data.vos.GuideVO;
import xyz.kkt.burpplefoodplaces.data.vos.PromotionShopVO;
import xyz.kkt.burpplefoodplaces.data.vos.PromotionVO;
import xyz.kkt.burpplefoodplaces.events.RestApiEvents;
import xyz.kkt.burpplefoodplaces.network.call.FoodDataAgent;
import xyz.kkt.burpplefoodplaces.network.call.FoodDataAgentImpl;
import xyz.kkt.burpplefoodplaces.persistence.BurppleContract;
import xyz.kkt.burpplefoodplaces.utils.AppConstants;
import xyz.kkt.burpplefoodplaces.utils.ConfigUtils;

/**
 * Created by Lenovo on 1/5/2018.
 */

public class BurppleModel {

    //private static BurppleModel objInstance;
//    private List<PromotionVO> mPromotionList;
//    private List<FeaturedVO> mFeaturedList;
//    private List<GuideVO> mGuideList;

    @Inject
    FoodDataAgent mDataAgent;

    @Inject
    ConfigUtils mConfigUtils;

    public BurppleModel(Context context) {
        EventBus.getDefault().register(this);
        BurppleApp burppleApp = (BurppleApp) context;
        burppleApp.getBurppleAppComponent().inject(this);
//        mPromotionList = new ArrayList<>();
//        mGuideList = new ArrayList<>();
//        mFeaturedList = new ArrayList<>();
    }

//    public static BurppleModel getInstance() {
//        if (objInstance == null) {
//            objInstance = new BurppleModel();
//        }
//        return objInstance;
//    }

    public void startLoadingFood(Context context) {
        startLoadingPromotion(context);
        startLoadingGuide(context);
        startLoadingFeatured(context);
    }

    public void startLoadingPromotion(Context context) {
        mDataAgent.loadPromotion(AppConstants.ACCESS_TOKEN, mConfigUtils.loadProPageIndex(), context);
    }

    public void startLoadingGuide(Context context) {
        mDataAgent.loadGuide(AppConstants.ACCESS_TOKEN, mConfigUtils.loadGuiPageIndex(), context);
    }

    public void startLoadingFeatured(Context context) {
        mDataAgent.loadFeatured(AppConstants.ACCESS_TOKEN, mConfigUtils.loadFeaPageIndex(), context);
    }

    public void loadMorePromotion(Context context) {
        int pageIndex = mConfigUtils.loadProPageIndex();
        mDataAgent.loadPromotion(AppConstants.ACCESS_TOKEN, pageIndex, context);
    }

    public void loadMoreGuide(Context context) {
        int pageIndex = mConfigUtils.loadGuiPageIndex();
        mDataAgent.loadGuide(AppConstants.ACCESS_TOKEN, pageIndex, context);
    }

    public void loadMoreFeatured(Context context) {
        int pageIndex = mConfigUtils.loadFeaPageIndex();
        mDataAgent.loadFeatured(AppConstants.ACCESS_TOKEN, pageIndex, context);
    }

    public void forceRefreshPromotion(Context context) {
        //mPromotionList = new ArrayList<>();
        mConfigUtils.saveProPageIndex(1);
        startLoadingPromotion(context);
    }

    public void forceRefreshGuide(Context context) {
        // mGuideList = new ArrayList<>();
        mConfigUtils.saveGuiPageIndex(1);
        startLoadingGuide(context);
    }


    public void forceRefreshFeatured(Context context) {
        // mFeaturedList = new ArrayList<>();
        mConfigUtils.saveFeaPageIndex(1);
        startLoadingFeatured(context);
    }


    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onPromotionDataLoaded(RestApiEvents.PromotionDataLoadedEvent event) {
        mConfigUtils.saveProPageIndex(event.getLoadedPageIndex() + 1);
        // mPromotionList.addAll(event.getLoadPromotion());

        ContentValues[] promotionCVs = new ContentValues[event.getLoadPromotion().size()];
        List<ContentValues> promotionShopCVList = new ArrayList<>();
        List<ContentValues> promotionTermCVList = new ArrayList<>();

        for (int index = 0; index < promotionCVs.length; index++) {
            PromotionVO promotion = event.getLoadPromotion().get(index);
            promotionCVs[index] = promotion.parseToContentValues();

            PromotionShopVO promotionShop = promotion.getBurpplePromotionShop();
            promotionShopCVList.add(promotionShop.parseToContentValues());

            for (String term : promotion.getBurpplePromotionTerms()) {
                ContentValues termCV = new ContentValues();
                termCV.put(BurppleContract.PromotionTermEntry.COLUMN_TERM_IN_PROMOTION_ID, promotion.getBurpplePromotionId());
                termCV.put(BurppleContract.PromotionTermEntry.COLUMN_TERM, term);
                promotionTermCVList.add(termCV);
            }

        }

        int insertedPromotion = event.getContext().getContentResolver().bulkInsert(BurppleContract.PromotionEntry.CONTENT_URI,
                promotionCVs);
        Log.d(BurppleApp.LOG_TAG, "Inserted Promotion Rows : " + insertedPromotion);

        int insertedPromotionShop = event.getContext().getContentResolver().bulkInsert(BurppleContract.PromotionShopEntry.CONTENT_URI,
                promotionShopCVList.toArray(new ContentValues[0]));
        Log.d(BurppleApp.LOG_TAG, "Inserted Promotion Shop Rows : " + insertedPromotionShop);

        int insertedPromotionTerm = event.getContext().getContentResolver().bulkInsert(BurppleContract.PromotionTermEntry.CONTENT_URI,
                promotionTermCVList.toArray(new ContentValues[0]));
        Log.d(BurppleApp.LOG_TAG, "Inserted Promotion Term Rows : " + insertedPromotionTerm);

    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onFeaturedDataLoaded(RestApiEvents.FeaturedDataLoadedEvent event) {
        mConfigUtils.saveFeaPageIndex(event.getLoadedPageIndex() + 1);
        //mFeaturedList.addAll(event.getLoadFeatured());

        ContentValues[] featuredCVs = new ContentValues[event.getLoadFeatured().size()];

        for (int index = 0; index < featuredCVs.length; index++) {
            FeaturedVO featured = event.getLoadFeatured().get(index);
            featuredCVs[index] = featured.parseToContentValues();

        }

        int insertedFeatured = event.getContext().getContentResolver().bulkInsert(BurppleContract.FeaturedEntry.CONTENT_URI,
                featuredCVs);
        Log.d(BurppleApp.LOG_TAG, "Inserted Featured Rows : " + insertedFeatured);

    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onGuideDataLoaded(RestApiEvents.GuideDataLoadedEvent event) {
        mConfigUtils.saveGuiPageIndex(event.getLoadedPageIndex() + 1);
        //mGuideList.addAll(event.getLoadGuide());

        ContentValues[] guideCVs = new ContentValues[event.getLoadGuide().size()];

        for (int index = 0; index < guideCVs.length; index++) {
            GuideVO guide = event.getLoadGuide().get(index);
            guideCVs[index] = guide.parseToContentValues();
        }

        int insertedGuide = event.getContext().getContentResolver().bulkInsert(BurppleContract.GuideEntry.CONTENT_URI,
                guideCVs);
        Log.d(BurppleApp.LOG_TAG, "Inserted Guide Rows : " + insertedGuide);

    }

//    public List<PromotionVO> getmPromotionList() {
//        return mPromotionList;
//    }
//
//    public List<FeaturedVO> getmFeaturedList() {
//        return mFeaturedList;
//    }
//
//    public List<GuideVO> getmGuideList() {
//        return mGuideList;
//    }
}
