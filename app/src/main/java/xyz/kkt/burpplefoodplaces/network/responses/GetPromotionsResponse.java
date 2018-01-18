package xyz.kkt.burpplefoodplaces.network.responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import xyz.kkt.burpplefoodplaces.data.vos.PromotionVO;

/**
 * Created by Lenovo on 12/3/2017.
 */

public class GetPromotionsResponse extends BaseResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("apiVersion")
    private String apiVersion;

    @SerializedName("page")
    private int pageNo;

    @SerializedName("promotions")
    private List<PromotionVO> promotionVOList;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public int getPageNo() {
        return pageNo;
    }

    public List<PromotionVO> getPromotionList() {
        if (promotionVOList == null) {
            promotionVOList = new ArrayList<>();
        }
        return promotionVOList;
    }
}
