package xyz.kkt.burpplefoodplaces.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import xyz.kkt.burpplefoodplaces.R;
import xyz.kkt.burpplefoodplaces.data.vos.FeaturedVO;


/**
 * Created by Lenovo on 1/5/2018.
 */

public class HeroViewPagerAdapter extends PagerAdapter {

    private LayoutInflater mLayoutInflater;
    private ImageView iv;
    private List<String> images;

    public HeroViewPagerAdapter(Context context) {
        super();
        mLayoutInflater = LayoutInflater.from(context);
        images = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        iv = (ImageView) mLayoutInflater.inflate(R.layout.view_item_hero_view_pager, container, false);
        Glide
                .with(iv.getContext())
                .load(images.get(position))
                .into(iv);
        container.addView(iv);
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public void setFeaturedImg(List<FeaturedVO> featuredList) {
        for (FeaturedVO featured : featuredList) {
            images.add(featured.getBurppleFeaturedImage());
        }
        notifyDataSetChanged();
    }

}