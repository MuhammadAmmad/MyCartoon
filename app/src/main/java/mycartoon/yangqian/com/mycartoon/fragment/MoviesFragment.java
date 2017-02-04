package mycartoon.yangqian.com.mycartoon.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mycartoon.yangqian.com.mycartoon.Adapter.ImagePaperAdapter;
import mycartoon.yangqian.com.mycartoon.R;
import mycartoon.yangqian.com.mycartoon.entity.DTResult;
import mycartoon.yangqian.com.mycartoon.entity.Result;
import mycartoon.yangqian.com.mycartoon.entity.Sort;
import mycartoon.yangqian.com.mycartoon.internetclient.MovieResultApi;
import mycartoon.yangqian.com.mycartoon.internetclient.TVClient;
import mycartoon.yangqian.com.mycartoon.tmovie.MovieAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/11/23.
 */

public class MoviesFragment extends Fragment {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;


    @BindView(R.id.area_c)
    LinearLayout mAreaC;
    @BindView(R.id.area_u)
    LinearLayout mAreaU;
    @BindView(R.id.area_h)
    LinearLayout mAreaH;
    @BindView(R.id.dalu_view)
    LinearLayout mDaluView;
    @BindView(R.id.oumei_view)
    LinearLayout mOumeiView;
    @BindView(R.id.xianggang_view)
    LinearLayout mXianggangView;
    @BindView(R.id.pb_movie)
    ProgressBar mPbMovie;
    private MovieResultApi mResultApi;
    private ArrayList<Result> mList;
    private MovieAdapter mAdapter;
    private Sort mSort;
    private String mString;
    private LayoutInflater mInflater;
    private ImagePaperAdapter imagePaperAdapter;
    //
//    private ViewPager mviewPager;
//    /**/*//**用于小圆点图片*//**//**/
//    private List<ImageView> dotViewList;
//    *//**用于存放轮播效果图片*//*
//    private List<ImageView> list;
//
//    LinearLayout dotLayout;
//
    private int currentItem = 0;//当前页面
    //
    boolean isAutoPlay = true;//是否自动轮播
    //
    private ScheduledExecutorService scheduledExecutorService;
    //
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            if (msg.what == 100) {
                vp_shouye.setCurrentItem(currentItem);
            }

        }

    };

    ViewPager vp_shouye;
    ImageView[] icons = new ImageView[4];
    ArrayList<ImageView> imageViews = new ArrayList<ImageView>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mInflater = inflater;
        View view = mInflater.inflate(R.layout.activity_movie, container, false);

        ButterKnife.bind(this, view);


        initImageAndIcons(view);
        setIcon(0);
        vp_shouye = (ViewPager) view.findViewById(R.id.vp_shouye);
        vp_shouye.setAdapter(imagePaperAdapter);
        vp_shouye.setCurrentItem(0);
        vp_shouye.addOnPageChangeListener(onPageChangeListener);
        if (isAutoPlay) {
            startPlay();
        }

        initShow(view);
        return view;
    }

    private void initShow(View view) {


        sign(View.VISIBLE, View.INVISIBLE, View.INVISIBLE);
        mString = "CN";
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new MovieAdapter(getContext());
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setFocusable(false);
        mSort = new Sort(mString);
        mResultApi = TVClient.getInstance().getResultApi();

        loadData();
    }

    private void loadData() {
        Call<DTResult> dType = mResultApi.getDType(mSort.getSort());
        Log.e("MoviesFragment", "dType: " + dType);
        dType.enqueue(new Callback<DTResult>() {
            @Override
            public void onResponse(Call<DTResult> call, Response<DTResult> response) {
                mRecyclerView.setVisibility(View.VISIBLE);
                String string = response.body().toString();
                Log.e("MoviesFragment", "onResponse: " + string);
                mList = response.body().getResult();
                Log.e("MoviesFragment", "onResponse: " + mList.size());
                mAdapter.addData(mList);
                mAdapter.notifyDataSetChanged();
                mPbMovie.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<DTResult> call, Throwable t) {



            }
        });
    }

    @OnClick({R.id.area_c, R.id.area_u, R.id.area_h})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.area_c:
                //标题下的黄色标记
                sign(View.VISIBLE, View.INVISIBLE, View.INVISIBLE);

                mString = "CN";
                mSort.setSort(mString);
                loadData();
                break;
            case R.id.area_u:
                //标题下的黄色标记
                sign(View.INVISIBLE, View.VISIBLE, View.INVISIBLE);

                mString = "US";
                Log.e("onClick", "area_u:: " + mString);
                mSort.setSort(mString);
                Log.e("onClick", "area_u:: " + mSort.getSort());
                loadData();
                break;
            case R.id.area_h:
                //标题下的黄色标记
                sign(View.INVISIBLE, View.INVISIBLE, View.VISIBLE);
                mString = "HK";
                mSort.setSort(mString);
                loadData();
                break;
        }
    }

    private void sign(int dVisibility, int oVisibility, int xVisibility) {
        mDaluView.setVisibility(dVisibility);
        mOumeiView.setVisibility(oVisibility);
        mXianggangView.setVisibility(xVisibility);
    }

    /**
     * 开始轮播图切换
     */
    private void startPlay() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new SlideShowTask(), 1, 4, TimeUnit.SECONDS);
        //根据他的参数说明，第一个参数是执行的任务，第二个参数是第一次执行的间隔，第三个参数是执行任务的周期；
    }


    /**
     * 执行轮播图切换任务
     */
    private class SlideShowTask implements Runnable {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            synchronized (vp_shouye) {
                currentItem = (currentItem + 1) % imageViews.size();
                handler.sendEmptyMessage(100);
            }
        }
    }

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            setIcon(position);
//            if (position >=0)
        }

        @Override
        public void onPageScrollStateChanged(int state) {

            switch (state) {
                case 1:// 手势滑动，空闲中
                    isAutoPlay = false;
                    System.out.println(" 手势滑动，空闲中");
                    break;
                case 2:// 界面切换中
                    isAutoPlay = true;
                    System.out.println(" 界面切换中");
                    break;
                case 0:// 滑动结束，即切换完毕或者加载完毕
                    // 当前为最后一张，此时从右向左滑，则切换到第一张
                    if (vp_shouye.getCurrentItem() == vp_shouye.getAdapter().getCount() - 1 && !isAutoPlay) {
                        vp_shouye.setCurrentItem(0);
                        System.out.println(" 滑动到最后一张");
                    }
                    // 当前为第一张，此时从左向右滑，则切换到最后一张
                    else if (vp_shouye.getCurrentItem() == 0 && !isAutoPlay) {
                        vp_shouye.setCurrentItem(vp_shouye.getAdapter().getCount() - 1);
                        System.out.println(" 滑动到第一张");
                    }
                    break;
            }
        }
    };

    private void setIcon(int position) {
        for (int i = 0; i < icons.length; i++) {
            if (i == position) {
                icons[i].setImageResource(R.drawable.dianhong);
            } else {
                icons[i].setImageResource(R.drawable.dianbai);
            }
        }
    }

    private void initImageAndIcons(View view) {
        icons[0] = (ImageView) view.findViewById(R.id.iv_dot0);
        icons[1] = (ImageView) view.findViewById(R.id.iv_dot1);
        icons[2] = (ImageView) view.findViewById(R.id.iv_dot2);
        icons[3] = (ImageView) view.findViewById(R.id.iv_dot3);
        ImageView imageView0 = (ImageView) mInflater.inflate(R.layout.layout_lead_image, null);
        imageView0.setImageResource(R.drawable.main_img1);
        imageViews.add(imageView0);
        ImageView imageView1 = (ImageView) mInflater.inflate(R.layout.layout_lead_image, null);
        imageView1.setImageResource(R.drawable.main_img2);
        imageViews.add(imageView1);
        ImageView imageView2 = (ImageView) mInflater.inflate(R.layout.layout_lead_image, null);
        imageView2.setImageResource(R.drawable.main_img3);
        imageViews.add(imageView2);
        ImageView imageView3 = (ImageView) mInflater.inflate(R.layout.layout_lead_image, null);
        imageView3.setImageResource(R.drawable.main_img4);
        imageViews.add(imageView3);

        imagePaperAdapter = new ImagePaperAdapter(imageViews);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*mviewPager = (ViewPager) findViewById(R.id.myviewPager);
        dotLayout = (LinearLayout)findViewById(R.id.dotLayout);
        dotLayout.removeAllViews();

        initView();

        if(isAutoPlay){
            startPlay();
        }*/
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
