package mycartoon.yangqian.com.mycartoon.tv.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import mycartoon.yangqian.com.mycartoon.R;
import mycartoon.yangqian.com.mycartoon.base.BaseActivity;
import mycartoon.yangqian.com.mycartoon.comment.CommonUtils;
import mycartoon.yangqian.com.mycartoon.internetclient.TVApi;
import mycartoon.yangqian.com.mycartoon.internetclient.TVClient;
import mycartoon.yangqian.com.mycartoon.tv.adapter.TVAdapter;
import mycartoon.yangqian.com.mycartoon.tv.adapter.TVStarsAdapter;
import mycartoon.yangqian.com.mycartoon.tv.entity.Act_s;
import mycartoon.yangqian.com.mycartoon.tv.entity.Network;
import mycartoon.yangqian.com.mycartoon.tv.entity.TVRes;
import mycartoon.yangqian.com.mycartoon.tv.entity.TVResult;
import mycartoon.yangqian.com.mycartoon.tv.entity.TvSearch;
import mycartoon.yangqian.com.mycartoon.tv.entity.Video_rec;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 两次出错在实体类上！！！
 */
public class ShowActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.iv_cover)
    ImageView mIvCover;

    @BindView(R.id.tv_tag)
    TextView mTvTag;
    @BindView(R.id.tv_act)
    TextView mTvAct;
    @BindView(R.id.tv_year)
    TextView mTvYear;
    @BindView(R.id.tv_area)
    TextView mTvArea;
    @BindView(R.id.tv_dir)
    TextView mTvDir;
    @BindView(R.id.leshi)
    ImageView mLeshi;
    @BindView(R.id.pptv)
    ImageView mPptv;
    @BindView(R.id.qiyi)
    ImageView mQiyi;
    @BindView(R.id.qq)
    ImageView mQq;
    @BindView(R.id.sohu)
    ImageView mSohu;
    @BindView(R.id.youku)
    ImageView mYouku;
    @BindView(R.id.gridview_tv)
    GridView mGridviewTv;
    @BindView(R.id.gridview_stars)
    GridView mGridviewStars;
    @BindView(R.id.activity_show)
    RelativeLayout mActivityShow;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.scrollView)
    ScrollView mScrollView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.desc)
    TextView mDesc;
    @BindView(R.id.changeJiejie1)
    ImageView mChangeJiejie1;
    @BindView(R.id.xiangshang)
    RelativeLayout mXiangshang;
    @BindView(R.id.changeJiejie)
    ImageView mChangeJiejie;
    @BindView(R.id.xiangxia)
    RelativeLayout mXiangxia;
    private TVApi mTvApi;
    private String tvName;
    private String desc;
    private TvSearch mTvSearch;
    private ArrayList<Video_rec> mVideo_recs;
    private ArrayList<Act_s> mAct_ses;
    private TVAdapter mTVAdapter;
    private TVStarsAdapter mTVStarsAdapter;
    private Network mPlaylinks;

    private static final String KEY_SEA = "KEY_Sea";
    private Unbinder mUnbinder;

    //对外公开一个跳转进来的方法,周边剧集
    public static void openWeb(Context context, String search) {
        Intent intent = new Intent(context, ShowActivity.class);
        intent.putExtra(KEY_SEA, search);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        mUnbinder = ButterKnife.bind(this);

//        mTvTitle = (TextView) findViewById(R.id.tv_title);

        tvName = getIntent().getStringExtra(KEY_SEA);
        final String title = "你的" + tvName;
        //设置toolbar
        setSupportActionBar(mToolbar);
        //给左上角加返回图标(返回按钮)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //设置标题
        getSupportActionBar().setTitle(title);


        mTvApi = TVClient.getInstance().getTVApi();

        mTvSearch = new TvSearch(tvName);
        mTVAdapter = new TVAdapter(this);
        mTVStarsAdapter = new TVStarsAdapter(this);

        mGridviewTv.setAdapter(mTVAdapter);
        mGridviewStars.setAdapter(mTVStarsAdapter);
        mGridviewTv.setFocusable(false);
        mGridviewStars.setFocusable(false);
        Log.e("ShowActivity", "mGridviewTv: " + mGridviewTv);
        loadData();
        mGridviewTv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Video_rec video_rec = mVideo_recs.get(position);
                WebActivity.openWeb(ShowActivity.this, video_rec);
            }
        });
        mGridviewStars.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Act_s act_s = mAct_ses.get(position);
                WebActivity.openWeb(ShowActivity.this, act_s);

            }
        });

    }


    private void loadData() {
        final Call<TVResult> tvResult = mTvApi.getTvResult(mTvSearch.getP());
        Log.e("ShowActivity", "tvResult: " + tvResult);
        tvResult.enqueue(new Callback<TVResult>() {
            @Override
            public void onResponse(Call<TVResult> call, Response<TVResult> response) {
                Log.e("ShowActivity", "dType: " + response.body().getResult());
                mScrollView.setVisibility(View.VISIBLE);
                TVResult tvResult1 = response.body();

                if (tvResult1 == null) {
//                    Toast.makeText(ShowActivity.this, "请求数据为空", Toast.LENGTH_SHORT).show();
                    Log.e("ShowActivity", "tvResult1为空");
                } else {
                    Log.e("ShowActivity", "result:" + tvResult1.getResult().getTitle());
                    initSummary(tvResult1.getResult());

                    mPlaylinks = tvResult1.getResult().getPlaylinks();
                    initLink(mPlaylinks);
                    Log.e("ShowActivity", "video_rec:为空 " + tvResult1.getResult().getVideo_rec());
                }
                mVideo_recs = response.body().getResult().getVideo_rec();
                mAct_ses = response.body().getResult().getAct_s();
                desc = response.body().getResult().getDesc();
                if (mVideo_recs == null) {
                    Log.e("ShowActivity", "video_rec:为空 ");
                    return;
                } else {
//                    mVideo_recs.add(new Video_rec());
                    mTVAdapter.addData(mVideo_recs);
                    mTVAdapter.notifyDataSetChanged();
                }
                if (mAct_ses == null) {
                    Log.e("ShowActivity", "act_s:为空 ");
                    return;
                } else {
//                    mAct_ses.add(new Act_s());
                    mTVStarsAdapter.addData(mAct_ses);
                    mTVStarsAdapter.notifyDataSetChanged();
                }
                if (desc == null) {
                    Log.e("ShowActivity", "desc:为空 ");
                    return;
                } else {
                    mDesc.setText(desc);
                }

                mProgressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<TVResult> call, Throwable t) {

//                Toast.makeText(ShowActivity.this, "网络请求有误！Throwable", Toast.LENGTH_SHORT).show();
                Log.e("ShowActivity", "Throwable: " + t);
            }
        });

    }

    private void initLink(Network playlinks) {
        if (playlinks.getLeshi() == null) {
            Log.e("ShowActivity", "initLink: " + playlinks.getLeshi());
            mLeshi.setVisibility(View.GONE);
        }
        if (playlinks.getPptv() == null) {
            mPptv.setVisibility(View.GONE);
        }
        if (playlinks.getQiyi() == null) {
            mQiyi.setVisibility(View.GONE);
        }
        if (playlinks.getQq() == null) {
            mQq.setVisibility(View.GONE);
        }
        if (playlinks.getYouku() == null) {
            mYouku.setVisibility(View.GONE);
        }
        if (playlinks.getSohu() == null) {
            mSohu.setVisibility(View.GONE);
        }

    }

    //
    private void initSummary(TVRes result) {
        Log.e("initSummary", "result: " + result.getTitle());
        mTvTitle.setText("" + result.getTitle());
        mTvTag.setText("类型：" + result.getTag());
        mTvAct.setText("主演：" + result.getAct());
        mTvArea.setText("国家：" + result.getArea());
        mTvDir.setText("导演：" + result.getDir());
        mTvYear.setText("年份：" + result.getYear());
        String url = CommonUtils.encodeUrl(result.getCover());
        Picasso.with(this).load(url).into(mIvCover);


    }

    @OnClick({R.id.leshi, R.id.pptv, R.id.qiyi, R.id.qq, R.id.sohu, R.id.youku,R.id.xiangxia,R.id.xiangshang})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.leshi:
                String leshi = mPlaylinks.getLeshi();
                WebActivity.openWeb(this, leshi);
                break;
            case R.id.pptv:
                String pptv = mPlaylinks.getPptv();
                WebActivity.openWeb(this, pptv);
                break;
            case R.id.qiyi:
                String qiyi = mPlaylinks.getQiyi();
                WebActivity.openWeb(this, qiyi);
                break;
            case R.id.qq:
                String qq = mPlaylinks.getQq();
                WebActivity.openWeb(this, qq);
                break;
            case R.id.sohu:
                String sohu = mPlaylinks.getSohu();
                WebActivity.openWeb(this, sohu);
                break;
            case R.id.youku:
                String youku = mPlaylinks.getYouku();
                WebActivity.openWeb(this, youku);
                break;
            case R.id.xiangxia:
                ViewGroup.LayoutParams param = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
                mDesc.setLayoutParams(param);
//                mDesc.getLayoutParams().height = 200
                mXiangxia.setVisibility(View.GONE);
                mXiangshang.setVisibility(View.VISIBLE);
                Log.e("ShowActivity", "mXiangxia: ");
                break;
            case R.id.xiangshang:
                ViewGroup.LayoutParams param1 = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        89, 1.0f);
                mDesc.setLayoutParams(param1);
//                mDesc.getLayoutParams().height = 80;
                mXiangxia.setVisibility(View.VISIBLE);
                mXiangshang.setVisibility(View.GONE);
                Log.e("ShowActivity", "mXiangshang: ");

                break;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //左上角返回按钮
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onStop() {
        super.onStop();

    }
}
