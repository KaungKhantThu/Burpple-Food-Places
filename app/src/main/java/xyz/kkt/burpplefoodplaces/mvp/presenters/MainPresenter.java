package xyz.kkt.burpplefoodplaces.mvp.presenters;

import android.content.Context;
import android.database.Cursor;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import xyz.kkt.burpplefoodplaces.data.model.BurppleModel;
import xyz.kkt.burpplefoodplaces.data.vos.FeaturedVO;
import xyz.kkt.burpplefoodplaces.data.vos.GuideVO;
import xyz.kkt.burpplefoodplaces.data.vos.PromotionVO;
import xyz.kkt.burpplefoodplaces.mvp.views.MainView;

/**
 * Created by Lenovo on 1/6/2018.
 */

public class MainPresenter extends BasePresenter<MainView> {

    public MainPresenter() {

    }

    @Override
    public void onCreate(MainView view) {
        super.onCreate(view);
//        SFCNewsApp sfcNewsApp = (SFCNewsApp) mView.getContext();
//        sfcNewsApp.getSFCAppComponent().inject(this);
    }

    @Override
    public void onStart() {
//        EventBus.getDefault().register(this);
        BurppleModel.getInstance().startLoadingFood(mView.getContext());
    }

    @Override
    public void onStop() {
        //EventBus.getDefault().unregister(this);
    }

    public void onProListEndReach(Context context) {
        BurppleModel.getInstance().loadMorePromotion(mView.getContext());
    }

    public void onGuiListEndReach(Context context) {
        BurppleModel.getInstance().loadMoreGuide(mView.getContext());
    }

//    public void onForceRefresh(Context context) {
//        mNewsModel.forceRefreshNews(context);
//    }

    public void onProDataLoaded(Cursor data) {
        if (data != null && data.moveToFirst()) {
            List<PromotionVO> promotionList = new ArrayList<>();
            do {
                PromotionVO promotion = PromotionVO.parseFromCursor(mView.getContext(), data);
                promotionList.add(promotion);
            } while (data.moveToNext());
            {
                mView.displayPromotionList(promotionList);
            }
        }
    }

    public void onGuiDataLoaded(Cursor data) {
        if (data != null && data.moveToFirst()) {
            List<GuideVO> guideList = new ArrayList<>();
            do {
                GuideVO guide = GuideVO.parseFromCursor(data);
                guideList.add(guide);
            } while (data.moveToNext());
            {
                mView.displayGuideList(guideList);
            }
        }
    }

    public void onFeaDataLoaded(Cursor data) {
        if (data != null && data.moveToFirst()) {
            List<FeaturedVO> featuredList = new ArrayList<>();
            do {
                FeaturedVO featured = FeaturedVO.parseFromCursor(data);
                featuredList.add(featured);
            } while (data.moveToNext());
            {
                mView.displayFeaturedImgList(featuredList);
            }
        }
    }

}
