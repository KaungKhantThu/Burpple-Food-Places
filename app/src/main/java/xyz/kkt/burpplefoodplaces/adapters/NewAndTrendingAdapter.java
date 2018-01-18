package xyz.kkt.burpplefoodplaces.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import xyz.kkt.burpplefoodplaces.R;
import xyz.kkt.burpplefoodplaces.data.vos.FoodVO;
import xyz.kkt.burpplefoodplaces.viewholders.NewAndTrendingViewHolder;

/**
 * Created by Lenovo on 1/5/2018.
 */

public class NewAndTrendingAdapter extends BaseRecycleAdapter<NewAndTrendingViewHolder, FoodVO> {

    public NewAndTrendingAdapter(Context context) {
        super(context);
    }

    @Override
    public void onBindViewHolder(NewAndTrendingViewHolder holder, int position) {

    }

    @Override
    public NewAndTrendingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View newlyOpenedItemView = mLayoutInflater.inflate(R.layout.view_pod_new_and_trending, parent, false);
        return new NewAndTrendingViewHolder(newlyOpenedItemView);
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
