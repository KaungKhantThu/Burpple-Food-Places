package xyz.kkt.burpplefoodplaces.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import net.aungpyaephyo.mmtextview.components.MMTextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.kkt.burpplefoodplaces.R;
import xyz.kkt.burpplefoodplaces.adapters.GuideAdapter;
import xyz.kkt.burpplefoodplaces.adapters.HeroViewPagerAdapter;
import xyz.kkt.burpplefoodplaces.adapters.NewAndTrendingAdapter;
import xyz.kkt.burpplefoodplaces.adapters.PromotionAdapter;
import xyz.kkt.burpplefoodplaces.components.PageIndicatorView;
import xyz.kkt.burpplefoodplaces.components.SmartRecyclerView;
import xyz.kkt.burpplefoodplaces.components.SmartScrollListener;
import xyz.kkt.burpplefoodplaces.data.model.BurppleModel;
import xyz.kkt.burpplefoodplaces.data.vos.GuideVO;
import xyz.kkt.burpplefoodplaces.data.vos.PromotionVO;
import xyz.kkt.burpplefoodplaces.events.RestApiEvents;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_promotion_list)
    SmartRecyclerView rvPromotionList;

    @BindView(R.id.rv_guide_list)
    SmartRecyclerView rvGuideList;

//    @BindView(R.id.rv_new_and_trending_list)
//    RecyclerView rvNewAndTrendingList;

    @BindView(R.id.vp_hero_img)
    ViewPager vpHeroImg;

    @BindView(R.id.piv_hero_img)
    PageIndicatorView pivHeroImg;

    @BindView(R.id.swipe_refreshing_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private SmartScrollListener mProSmartScrollListener;
    private SmartScrollListener mGuiSmartScrollListener;

    private PromotionAdapter mPromotionAdapter;
    private GuideAdapter mGuideAdapter;
    //private NewAndTrendingAdapter mNewAndTrendingAdapter;
    private HeroViewPagerAdapter mHeroViewPagerAdapter;

    @Override
    protected void onStart() {
        super.onStart();

        EventBus.getDefault().register(this);

        BurppleModel.getInstance().startLoadingFood(getApplication());

        if (BurppleModel.getInstance().getmPromotionList() != null) {
            mPromotionAdapter.setNewData(BurppleModel.getInstance().getmPromotionList());
        }
        if (BurppleModel.getInstance().getmGuideList() != null) {
            mGuideAdapter.setNewData(BurppleModel.getInstance().getmGuideList());
        }

    }

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

        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            int NUM_PAGES = mHeroViewPagerAdapter.getCount();
            int currentPage = 0;

            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (currentPage == NUM_PAGES) {
                            currentPage = 0;
                        }
                        vpHeroImg.setCurrentItem(currentPage++, true);
                    }
                });

            }
        }, 1500, 5000);

        rvPromotionList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        mPromotionAdapter = new PromotionAdapter(getApplicationContext());
        rvPromotionList.setAdapter(mPromotionAdapter);

        rvGuideList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        mGuideAdapter = new GuideAdapter(getApplicationContext());
        rvGuideList.setAdapter(mGuideAdapter);

//        rvNewAndTrendingList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
//        mNewAndTrendingAdapter = new NewAndTrendingAdapter(getApplicationContext());
//        rvNewAndTrendingList.setAdapter(mNewAndTrendingAdapter);

        mProSmartScrollListener = new SmartScrollListener(new SmartScrollListener.OnSmartScrollListener() {
            @Override
            public void onListEndReach() {
                Snackbar.make(rvPromotionList, "Loading Promotion data.", Snackbar.LENGTH_LONG).show();
                swipeRefreshLayout.setRefreshing(true);

                BurppleModel.getInstance().loadMorePromotion(getApplicationContext());
            }
        });

//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                BurppleModel.getInstance().forceRefreshPromotion(getApplicationContext());
//            }
//        });

        rvPromotionList.addOnScrollListener(mProSmartScrollListener);

        mGuiSmartScrollListener = new SmartScrollListener(new SmartScrollListener.OnSmartScrollListener() {
            @Override
            public void onListEndReach() {
                Snackbar.make(rvGuideList, "Loading Guide data.", Snackbar.LENGTH_LONG).show();
                swipeRefreshLayout.setRefreshing(true);

                BurppleModel.getInstance().loadMoreGuide(getApplicationContext());
            }
        });

//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                BurppleModel.getInstance().forceRefreshGuide(getApplicationContext());
//            }
//        });

        rvGuideList.addOnScrollListener(mGuiSmartScrollListener);

    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPromotionDataLoaded(RestApiEvents.PromotionDataLoadedEvent event) {
        mPromotionAdapter.appendNewData(event.getLoadPromotion());
        swipeRefreshLayout.setRefreshing(false);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGuideDataLoadedl(RestApiEvents.GuideDataLoadedEvent event) {
        mGuideAdapter.appendNewData(event.getLoadGuide());
        swipeRefreshLayout.setRefreshing(false);
    }

}
