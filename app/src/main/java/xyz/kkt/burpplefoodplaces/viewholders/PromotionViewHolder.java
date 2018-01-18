package xyz.kkt.burpplefoodplaces.viewholders;

import android.view.View;
import android.widget.ImageView;


import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.kkt.burpplefoodplaces.R;
import xyz.kkt.burpplefoodplaces.data.vos.PromotionVO;

/**
 * Created by Lenovo on 1/5/2018.
 */

public class PromotionViewHolder extends BaseViewHolder<PromotionVO> {

    @BindView(R.id.iv_promotion_hero)
    ImageView ivPromotionHero;

    public PromotionViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(PromotionVO data) {
        Glide
                .with(ivPromotionHero.getContext())
                .load(data.getBurpplePromotionImage())
                .into(ivPromotionHero);
    }

    @Override
    public void onClick(View view) {

    }
}
