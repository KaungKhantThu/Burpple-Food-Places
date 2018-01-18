package xyz.kkt.burpplefoodplaces.data.vos;

import com.google.gson.annotations.SerializedName;

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
}
