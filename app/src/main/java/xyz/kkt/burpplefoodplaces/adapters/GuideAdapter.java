package xyz.kkt.burpplefoodplaces.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import xyz.kkt.burpplefoodplaces.R;
import xyz.kkt.burpplefoodplaces.data.vos.FoodVO;
import xyz.kkt.burpplefoodplaces.data.vos.GuideVO;
import xyz.kkt.burpplefoodplaces.viewholders.GuideViewHolder;

/**
 * Created by Lenovo on 1/5/2018.
 */

public class GuideAdapter extends BaseRecycleAdapter<GuideViewHolder, GuideVO> {

    public GuideAdapter(Context context) {
        super(context);
    }

    @Override
    public GuideViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View guideItemView = mLayoutInflater.inflate(R.layout.view_item_guide, parent, false);
        return new GuideViewHolder(guideItemView);
    }

}
