package xyz.kkt.burpplefoodplaces.network.responses;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import xyz.kkt.burpplefoodplaces.data.vos.GuideVO;

/**
 * Created by Lenovo on 1/17/2018.
 */

public class GetGuidesResponse extends BaseResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("apiVersion")
    private String apiVersion;

    @SerializedName("page")
    private int pageNo;

    @SerializedName("featured")
    private List<GuideVO> guideVOList;

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

    public List<GuideVO> getGuideVOList() {
        if (guideVOList == null) {
            guideVOList = new ArrayList<>();
        }
        return guideVOList;
    }
}
