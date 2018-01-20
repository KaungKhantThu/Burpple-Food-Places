package xyz.kkt.burpplefoodplaces.data.vos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
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

    public static PromotionVO parseFromCursor(Context context, Cursor cursor) {

        PromotionVO promotions = new PromotionVO();

        promotions.burpplePromotionId = cursor.getString(cursor.getColumnIndex(BurppleContract.PromotionEntry.COLUMN_PROMOTION_ID));
        promotions.burpplePromotionImage = cursor.getString(cursor.getColumnIndex(BurppleContract.PromotionEntry.COLUMN_PROMOTION_IMAGE));
        promotions.burpplePromotionTitle = cursor.getString(cursor.getColumnIndex(BurppleContract.PromotionEntry.COLUMN_TITLE));
        promotions.burpplePromotionUntil = cursor.getString(cursor.getColumnIndex(BurppleContract.PromotionEntry.COLUMN_UNTIL));
        promotions.isBurppleExculsive = Boolean.valueOf(cursor.getString(cursor.getColumnIndex(BurppleContract.PromotionEntry.COLUMN_IS_EXCLUSIVE)));

        promotions.burpplePromotionShop = PromotionShopVO.parseFromCursor(cursor);
        promotions.burpplePromotionTerms = loadPromotionTerms(context, promotions.burpplePromotionId);

        return promotions;
    }

    private static List<String> loadPromotionTerms(Context context, String promotionId) {
        Cursor promotionTermsCursor = context.getContentResolver().query(BurppleContract.PromotionTermEntry.CONTENT_URI,
                null,
                BurppleContract.PromotionTermEntry.COLUMN_TERM_IN_PROMOTION_ID + " = ?", new String[]{promotionId},
                null);

        if (promotionTermsCursor != null && promotionTermsCursor.moveToFirst()) {
            List<String> promotionTerms = new ArrayList<>();
            do {
                promotionTerms.add(
                        promotionTermsCursor.getString(
                                promotionTermsCursor.getColumnIndex(BurppleContract.PromotionTermEntry.COLUMN_TERM)));
            } while (promotionTermsCursor.moveToNext());
            promotionTermsCursor.close();
            return promotionTerms;
        }

        return null;
    }

}
