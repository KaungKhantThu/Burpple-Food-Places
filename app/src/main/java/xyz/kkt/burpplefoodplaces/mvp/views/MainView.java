package xyz.kkt.burpplefoodplaces.mvp.views;

import android.content.Context;

import java.util.List;

import xyz.kkt.burpplefoodplaces.data.vos.FeaturedVO;
import xyz.kkt.burpplefoodplaces.data.vos.GuideVO;
import xyz.kkt.burpplefoodplaces.data.vos.PromotionVO;

/**
 * Created by Lenovo on 1/6/2018.
 */

public interface MainView {

    void displayPromotionList(List<PromotionVO> promotionList);

    void displayGuideList(List<GuideVO> guideList);

    void displayFeaturedImgList(List<FeaturedVO> featuredImgList);

    void setTrueSwipeRefreshLayout();

    Context getContext();

}
