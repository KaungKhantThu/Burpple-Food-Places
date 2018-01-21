package xyz.kkt.burpplefoodplaces.network.call;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import xyz.kkt.burpplefoodplaces.network.responses.GetFeaturedResponse;
import xyz.kkt.burpplefoodplaces.network.responses.GetGuidesResponse;
import xyz.kkt.burpplefoodplaces.network.responses.GetPromotionsResponse;

/**
 * Created by Lenovo on 1/14/2018.
 */

public interface FoodAPI {

    @FormUrlEncoded
    @POST("v1/getPromotions.php")
    Call<GetPromotionsResponse> loadPromotions(
            @Field("access_token") String accessToken,
            @Field("page") int pageIndex);

    @FormUrlEncoded
    @POST("v1/getFeatured.php")
    Call<GetFeaturedResponse> loadFeatured(
            @Field("access_token") String accessToken,
            @Field("page") int pageIndex);

    @FormUrlEncoded
    @POST("v1/getGuides.php")
    Call<GetGuidesResponse> loadGuides(
            @Field("access_token") String accessToken,
            @Field("page") int pageIndex);


}
