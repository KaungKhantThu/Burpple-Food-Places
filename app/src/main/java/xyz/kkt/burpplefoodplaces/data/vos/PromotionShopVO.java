package xyz.kkt.burpplefoodplaces.data.vos;

import android.content.ContentValues;

import com.google.gson.annotations.SerializedName;

import xyz.kkt.burpplefoodplaces.persistence.BurppleContract;

/**
 * Created by Lenovo on 1/14/2018.
 */

public class PromotionShopVO {

    @SerializedName("burpple-shop-id")
    private String burppleShopId;

    @SerializedName("burpple-shop-name")
    private String burbbleShopName;

    @SerializedName("burpple-shop-area")
    private String burppleShopArea;

    public String getBurppleShopId() {
        return burppleShopId;
    }

    public String getBurbbleShopName() {
        return burbbleShopName;
    }

    public String getBurppleShopArea() {
        return burppleShopArea;
    }

    public ContentValues parseToContentValues() {
        ContentValues contentValues = new ContentValues();

        contentValues.put(BurppleContract.PromotionShopEntry.COLUMN_SHOP_ID, burppleShopId);
        contentValues.put(BurppleContract.PromotionShopEntry.COLUMN_SHOP_NAME, burbbleShopName);
        contentValues.put(BurppleContract.PromotionShopEntry.COLUMN_AREA, burppleShopArea);

        return contentValues;
    }
}
