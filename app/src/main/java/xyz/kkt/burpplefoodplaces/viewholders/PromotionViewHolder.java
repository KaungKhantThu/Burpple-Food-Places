package xyz.kkt.burpplefoodplaces.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


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

    @BindView(R.id.tv_promotion_title)
    TextView tvPromotionTitle;

    @BindView(R.id.tv_promotion_duration)
    TextView tvPromotionDuration;

    @BindView(R.id.tv_shop_name)
    TextView tvShopName;

    @BindView(R.id.tv_shop_area)
    TextView tvShopArea;

    public PromotionViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(PromotionVO data) {
        if (data != null) {

            if (data.getBurpplePromotionImage() != null) {
                Glide
                        .with(ivPromotionHero.getContext())
                        .load(data.getBurpplePromotionImage())
                        .into(ivPromotionHero);
            }

            if (data.getBurpplePromotionTitle() != null) {
                tvPromotionTitle.setText(data.getBurpplePromotionTitle());
            }

            if (data.getBurpplePromotionUtil() != null) {
                tvPromotionDuration.setText(data.getBurpplePromotionUtil());
            }

            if (data.getBurpplePromotionShop() != null) {
                if (data.getBurpplePromotionShop().getBurbbleShopName() != null) {
                    tvShopName.setText(data.getBurpplePromotionShop().getBurbbleShopName());
                }
                if (data.getBurpplePromotionShop().getBurppleShopArea() != null) {
                    tvShopArea.setText(data.getBurpplePromotionShop().getBurppleShopArea());
                }
            }
        }
    }

    @Override
    public void onClick(View view) {

    }
}
