package xyz.kkt.burpplefoodplaces.persistence;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import xyz.kkt.burpplefoodplaces.BurppleApp;

/**
 * Created by Lenovo on 12/16/2017.
 */

public class BurppleContract {

    public static final String CONTENT_AUTHORITY = BurppleApp.class.getPackage().getName();
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_PROMOTION = "promotion";
    public static final String PATH_PROMOTION_SHOP = "promotion_shop";
    public static final String PATH_PROMOTION_TERM = "promotion_term";
    public static final String PATH_GUIDE = "guide";
    public static final String PATH_FEATURED = "featured";

    public static class PromotionEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_PROMOTION).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PROMOTION;//for retriving multiple type

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PROMOTION;//for retriving a single row from the table


        public static final String TABLE_NAME = PATH_PROMOTION;
        public static final String COLUMN_PROMOTION_ID = "promotion_id";
        public static final String COLUMN_PROMOTION_IMAGE = "promotion_image";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_UNTIL = "until";
        public static final String COLUMN_PROMOTION_SHOP_ID = "shop_id";
        public static final String COLUMN_IS_EXCLUSIVE = "is_exclusive";

        public static Uri buildAttractionUri(long id) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attractions/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }

    public static class PromotionShopEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_PROMOTION_SHOP).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PROMOTION_SHOP;//for retriving multiple type

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PROMOTION_SHOP;//for retriving a single row from the table

        public static final String TABLE_NAME = PATH_PROMOTION_SHOP;

        public static final String COLUMN_SHOP_ID = "shop_id";
        public static final String COLUMN_SHOP_NAME = "shop_name";
        public static final String COLUMN_AREA = "area";

        public static Uri buildAttractionUri(long id) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attractions/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }

    public static class PromotionTermEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_PROMOTION_TERM).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PROMOTION_TERM;//for retriving multiple type

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PROMOTION_TERM;//for retriving a single row from the table

        public static final String TABLE_NAME = PATH_PROMOTION_TERM;

        public static final String COLUMN_TERM_IN_PROMOTION_ID = "term_in_promotion_id";
        public static final String COLUMN_TERM = "term";

        public static Uri buildAttractionUri(long id) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attractions/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }

    public static class GuideEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_GUIDE).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_GUIDE;//for retriving multiple type

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_GUIDE;//for retriving a single row from the table

        public static final String TABLE_NAME = PATH_GUIDE;

        public static final String COLUMN_GUIDE_ID = "guide_id";
        public static final String COLUMN_GUIDE_IMAGE = "guide_img";
        public static final String COLUMN_GUIDE_TITLE = "guide_title";
        public static final String COLUMN_GUIDE_DESC = "guide_desc";

        public static Uri buildAttractionUri(long id) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attractions/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }

    public static class FeaturedEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_FEATURED).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FEATURED;//for retriving multiple type

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_FEATURED;//for retriving a single row from the table

        public static final String TABLE_NAME = PATH_FEATURED;

        public static final String COLUMN_FEATURED_ID = "featured_id";
        public static final String COLUMN_FEATURED_IMAGE = "featured_img";
        public static final String COLUMN_FEATURED_TITLE = "featured_title";
        public static final String COLUMN_FEATURED_DESC = "featured_desc";
        public static final String COLUMN_FEATURED_TAG = "featured_tag";

        public static Uri buildAttractionUri(long id) {
            //content://xyz.aungpyaephyo.padc.myanmarattractions/attractions/1
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }
}
