package xyz.kkt.burpplefoodplaces.events;

import android.content.Context;

import java.util.List;

import xyz.kkt.burpplefoodplaces.data.vos.FeaturedVO;
import xyz.kkt.burpplefoodplaces.data.vos.GuideVO;
import xyz.kkt.burpplefoodplaces.data.vos.PromotionShopVO;
import xyz.kkt.burpplefoodplaces.data.vos.PromotionVO;


/**
 * Created by Lenovo on 12/3/2017.
 */

public class RestApiEvents {

    public static class EmptyResponseEvent {

    }

    public static class ErrorInvokingAPIEvent {
        private String errorMsg;

        public ErrorInvokingAPIEvent(String errorMsg) {
            this.errorMsg = errorMsg;
        }

        public String getErrorMsg() {
            return errorMsg;
        }
    }

    public static class PromotionDataLoadedEvent {
        private int loadedPageIndex;
        private List<PromotionVO> loadPromotion;
        private Context context;

        public PromotionDataLoadedEvent(int loadedPageIndex, List<PromotionVO> loadPromotion, Context context) {
            this.loadedPageIndex = loadedPageIndex;
            this.loadPromotion = loadPromotion;
            this.context = context;
        }

        public int getLoadedPageIndex() {
            return loadedPageIndex;
        }

        public List<PromotionVO> getLoadPromotion() {
            return loadPromotion;
        }

        public Context getContext() {
            return context;
        }
    }

    public static class FeaturedDataLoadedEvent {
        private int loadedPageIndex;
        private List<FeaturedVO> loadFeatured;
        private Context context;

        public FeaturedDataLoadedEvent(int loadedPageIndex, List<FeaturedVO> loadFeatured, Context context) {
            this.loadedPageIndex = loadedPageIndex;
            this.loadFeatured = loadFeatured;
            this.context = context;
        }

        public int getLoadedPageIndex() {
            return loadedPageIndex;
        }

        public List<FeaturedVO> getLoadFeatured() {
            return loadFeatured;
        }

        public Context getContext() {
            return context;
        }
    }

    public static class GuideDataLoadedEvent {
        private int loadedPageIndex;
        private List<GuideVO> loadGuide;
        private Context context;

        public GuideDataLoadedEvent(int loadedPageIndex, List<GuideVO> loadGuide, Context context) {
            this.loadedPageIndex = loadedPageIndex;
            this.loadGuide = loadGuide;
            this.context = context;
        }

        public int getLoadedPageIndex() {
            return loadedPageIndex;
        }

        public List<GuideVO> getLoadGuide() {
            return loadGuide;
        }

        public Context getContext() {
            return context;
        }
    }

}
