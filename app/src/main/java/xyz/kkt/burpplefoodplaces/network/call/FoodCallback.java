package xyz.kkt.burpplefoodplaces.network.call;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.kkt.burpplefoodplaces.events.RestApiEvents;
import xyz.kkt.burpplefoodplaces.network.responses.BaseResponse;


/**
 * Created by Lenovo on 12/9/2017.
 */

public abstract class FoodCallback<T extends BaseResponse> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {

        BaseResponse baseResponse = response.body();
        if (baseResponse == null) {
            RestApiEvents.ErrorInvokingAPIEvent errorEvent = new RestApiEvents.ErrorInvokingAPIEvent(
                    "No data can be loaded for now. Please try again later.");
            EventBus.getDefault().post((errorEvent));
        }

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        RestApiEvents.ErrorInvokingAPIEvent errorEvent = new RestApiEvents.ErrorInvokingAPIEvent(
                "No data can be loaded for now. Please try again later.");
        EventBus.getDefault().post((errorEvent));
    }
}
