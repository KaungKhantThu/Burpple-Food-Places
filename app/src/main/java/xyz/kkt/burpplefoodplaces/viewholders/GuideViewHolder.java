package xyz.kkt.burpplefoodplaces.viewholders;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.kkt.burpplefoodplaces.R;
import xyz.kkt.burpplefoodplaces.data.vos.GuideVO;

/**
 * Created by Lenovo on 1/5/2018.
 */

public class GuideViewHolder extends BaseViewHolder<GuideVO> {

    @BindView(R.id.iv_guide_hero)
    ImageView ivGuideHero;

    public GuideViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(GuideVO data) {
        Glide
                .with(ivGuideHero.getContext())
                .load(data.getBurppleGuideImage())
                .into(ivGuideHero);
    }

    @Override
    public void onClick(View view) {

    }
}
