package xyz.kkt.burpplefoodplaces.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.kkt.burpplefoodplaces.R;
import xyz.kkt.burpplefoodplaces.adapters.GuideAdapter;
import xyz.kkt.burpplefoodplaces.adapters.HeroViewPagerAdapter;
import xyz.kkt.burpplefoodplaces.adapters.NewAndTrendingAdapter;
import xyz.kkt.burpplefoodplaces.adapters.PromotionAdapter;
import xyz.kkt.burpplefoodplaces.components.PageIndicatorView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_promotion_list)
    RecyclerView rvPromotionList;

    @BindView(R.id.rv_guide_list)
    RecyclerView rvGuideList;

    @BindView(R.id.rv_new_and_trending_list)
    RecyclerView rvNewAndTrendingList;

    @BindView(R.id.vp_hero_img)
    ViewPager vpHeroImg;

    @BindView(R.id.piv_hero_img)
    PageIndicatorView pivHeroImg;

    private PromotionAdapter mPromotionAdapter;
    private GuideAdapter mGuideAdapter;
    private NewAndTrendingAdapter mNewAndTrendingAdapter;
    private HeroViewPagerAdapter mHeroViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this, this);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);

        mHeroViewPagerAdapter = new HeroViewPagerAdapter(getApplicationContext());
        vpHeroImg.setAdapter(mHeroViewPagerAdapter);

        vpHeroImg.setOffscreenPageLimit(mHeroViewPagerAdapter.getCount());

        pivHeroImg.setNumPage(mHeroViewPagerAdapter.getCount());
        vpHeroImg.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pivHeroImg.setCurrentPage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        rvPromotionList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        mPromotionAdapter = new PromotionAdapter(getApplicationContext());
        rvPromotionList.setAdapter(mPromotionAdapter);

        rvGuideList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        mGuideAdapter = new GuideAdapter(getApplicationContext());
        rvGuideList.setAdapter(mGuideAdapter);

        rvNewAndTrendingList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        mNewAndTrendingAdapter = new NewAndTrendingAdapter(getApplicationContext());
        rvNewAndTrendingList.setAdapter(mNewAndTrendingAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
