package xyz.kkt.burpplefoodplaces.data.vos;

import android.content.ContentValues;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import xyz.kkt.burpplefoodplaces.persistence.BurppleContract;

/**
 * Created by Lenovo on 1/14/2018.
 */

public class PromotionVO {

    @SerializedName("burpple-promotion-id")
    private String burpplePromotionId;

    @SerializedName("burpple-promotion-image")
    private String burpplePromotionImage;

    @SerializedName("burpple-promotion-title")
    private String burpplePromotionTitle;

    @SerializedName("burpple-promotion-until")
    private String burpplePromotionUntil;

    @SerializedName("burpple-promotion-shop")
    private PromotionShopVO burpplePromotionShop;

    @SerializedName("is-burpple-exclusive")
    private Boolean isBurppleExculsive;

    @SerializedName("burpple-promotion-terms")
    private List<String> burpplePromotionTerms;

    public String getBurpplePromotionId() {
        return burpplePromotionId;
    }

    public String getBurpplePromotionImage() {
        return burpplePromotionImage;
    }

    public String getBurpplePromotionTitle() {
        return burpplePromotionTitle;
    }

    public String getBurpplePromotionUntil() {
        return burpplePromotionUntil;
    }

    public PromotionShopVO getBurpplePromotionShop() {
        return burpplePromotionShop;
    }

    public Boolean getBurppleExculsive() {
        return isBurppleExculsive;
    }

    public List<String> getBurpplePromotionTerms() {
        return burpplePromotionTerms;
    }

    public ContentValues parseToContentValues() {

        ContentValues contentValues = new ContentValues();

        contentValues.put(BurppleContract.PromotionEntry.COLUMN_PROMOTION_ID, burpplePromotionId);
        contentValues.put(BurppleContract.PromotionEntry.COLUMN_PROMOTION_IMAGE, burpplePromotionImage);
        contentValues.put(BurppleContract.PromotionEntry.COLUMN_TITLE, burpplePromotionTitle);
        contentValues.put(BurppleContract.PromotionEntry.COLUMN_UNTIL, burpplePromotionUntil);
        contentValues.put(BurppleContract.PromotionEntry.COLUMN_IS_EXCLUSIVE, isBurppleExculsive);
        contentValues.put(BurppleContract.PromotionEntry.COLUMN_PROMOTION_SHOP_ID, burpplePromotionShop.getBurppleShopId());

        return contentValues;

    }
}
