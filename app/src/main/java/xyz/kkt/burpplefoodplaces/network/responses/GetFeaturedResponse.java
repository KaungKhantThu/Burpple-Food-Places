package xyz.kkt.burpplefoodplaces.network.responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import xyz.kkt.burpplefoodplaces.data.vos.FeaturedVO;

/**
 * Created by Lenovo on 1/14/2018.
 */

public class GetFeaturedResponse extends BaseResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("apiVersion")
    private String apiVersion;

    @SerializedName("page")
    private int pageNo;

    @SerializedName("featured")
    private List<FeaturedVO> featuredVOList;

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

    public List<FeaturedVO> getFeaturedList() {
        if (featuredVOList == null) {
            featuredVOList = new ArrayList<>();
        }
        return featuredVOList;
    }


}
