package xyz.kkt.burpplefoodplaces.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import xyz.kkt.burpplefoodplaces.R;
import xyz.kkt.burpplefoodplaces.data.vos.FoodVO;
import xyz.kkt.burpplefoodplaces.viewholders.PromotionViewHolder;

/**
 * Created by Lenovo on 1/5/2018.
 */

public class PromotionAdapter extends BaseRecycleAdapter<PromotionViewHolder, FoodVO> {

    public PromotionAdapter(Context context) {
        super(context);
    }

    @Override
    public void onBindViewHolder(PromotionViewHolder holder, int position) {

    }

    @Override
    public PromotionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View promotionItemView = mLayoutInflater.inflate(R.layout.view_item_promotion, parent, false);
        return new PromotionViewHolder(promotionItemView);
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
