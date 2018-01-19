package xyz.kkt.burpplefoodplaces.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Lenovo on 1/2/2018.
 */

public class ConfigUtils {

    private static final String PROMOTION_KEY_PAGE_INDEX = "KEY_PAGE_INDEX";
    private static final String GUIDE_KEY_PAGE_INDEX = "KEY_PAGE_INDEX";
    private static final String FEATURED_KEY_PAGE_INDEX = "KEY_PAGE_INDEX";

    private SharedPreferences mSharedPreferences;

    private static ConfigUtils sObjInstance;

    private ConfigUtils(Context context) {
        mSharedPreferences = context.getSharedPreferences("ConfigUtils", Context.MODE_PRIVATE);
    }

    public static void initConfigUtils(Context context) {
        sObjInstance = new ConfigUtils(context);
    }

    public static ConfigUtils getObjInstance() {
        return sObjInstance;
    }

    public void saveProPageIndex(int pageIndex) {
        mSharedPreferences.edit().putInt(PROMOTION_KEY_PAGE_INDEX, pageIndex).apply();
    }

    public void saveGuiPageIndex(int pageIndex) {
        mSharedPreferences.edit().putInt(GUIDE_KEY_PAGE_INDEX, pageIndex).apply();
    }

    public void saveFeaPageIndex(int pageIndex) {
        mSharedPreferences.edit().putInt(FEATURED_KEY_PAGE_INDEX, pageIndex).apply();
    }

    public int loadProPageIndex() {
        return mSharedPreferences.getInt(PROMOTION_KEY_PAGE_INDEX, 1);
    }

    public int loadGuiPageIndex() {
        return mSharedPreferences.getInt(GUIDE_KEY_PAGE_INDEX, 1);
    }

    public int loadFeaPageIndex() {
        return mSharedPreferences.getInt(FEATURED_KEY_PAGE_INDEX, 1);
    }

}