package mycartoon.yangqian.com.mycartoon.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import mycartoon.yangqian.com.mycartoon.R;
import mycartoon.yangqian.com.mycartoon.base.BaseActivity;
import mycartoon.yangqian.com.mycartoon.base.BaseViewPagerAdapter;
import mycartoon.yangqian.com.mycartoon.service.MediaPlayerService;
import mycartoon.yangqian.com.mycartoon.utill.LogUtil;

public class LeadActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    ImageView[] imageViews = new ImageView[3];
    ViewPager vp_lead;
    TextView tv_lead_b;
    BaseViewPagerAdapter adapter;
    LayoutInflater layoutInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.e("LeadActivity", "onCreate: " );
        SharedPreferences lead_config = getSharedPreferences("lead_config", MODE_PRIVATE);
        boolean isFirstRun = lead_config.getBoolean("isFirstRun", true);
        if (isFirstRun){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_lead);
            Log.e("LeadActivity", "setContentView: " );
            initView();
            initViewPager();
            initDataOfViewPager();

            Intent intentService = new Intent(this, MediaPlayerService.class);
            startService(intentService);

        }else {
            startActivity(LogoActivity.class);
            LeadActivity.this.finish();
        }
    }

    private void initDataOfViewPager() {
        layoutInflater = getLayoutInflater();
        ImageView imageView = null;
        imageView = (ImageView) layoutInflater.inflate(R.layout.layout_lead_image,null);
        imageView.setImageResource(R.drawable.qybs);
        adapter.addView(imageView);
        adapter.notifyDataSetChanged();
        imageView = (ImageView) layoutInflater.inflate(R.layout.layout_lead_image,null);
        imageView.setImageResource(R.drawable.lhz);
        adapter.addView(imageView);

        adapter.notifyDataSetChanged();
        imageView = (ImageView) layoutInflater.inflate(R.layout.layout_lead_image,null);
        imageView.setImageResource(R.drawable.qyz);
        adapter.addView(imageView);

        adapter.notifyDataSetChanged();
    }

    /**
     * 初始化Viewpager
     */
    private void initViewPager() {
        vp_lead = (ViewPager) findViewById(R.id.vp_lead);
        adapter = new BaseViewPagerAdapter(this);
        vp_lead.setAdapter(adapter);
        vp_lead.addOnPageChangeListener(this);
    }

    /**
     * 初始化部分View
     */
    private void initView() {
        imageViews[0] = (ImageView) findViewById(R.id.iv_lead_icon0);
        imageViews[1] = (ImageView) findViewById(R.id.iv_lead_icon1);
        imageViews[2] = (ImageView) findViewById(R.id.iv_lead_icon2);

        tv_lead_b = (TextView) findViewById(R.id.tv_lead_b);
        tv_lead_b.setVisibility(View.INVISIBLE);
        tv_lead_b.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        savaNotFirstRun();
        Intent intentService = new Intent(this,MediaPlayerService.class);
        stopService(intentService);
        startActivity(LogoActivity.class);
        finish();
    }

    private void savaNotFirstRun() {
        SharedPreferences lead_config = getSharedPreferences("lead_config", MODE_PRIVATE);
        SharedPreferences.Editor editor = lead_config.edit();
        editor.putBoolean("isFirstRun",false);
        editor.commit();

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tv_lead_b.setVisibility(View.INVISIBLE);
        if (position == 2){
            tv_lead_b.setVisibility(View.VISIBLE);
        }
        for (ImageView imageView : imageViews){
            imageView.setImageResource(R.drawable.adware_style_default);
        }
        imageViews[position].setImageResource(R.drawable.adware_style_selected);
        LogUtil.e("onPageSelected","运行。。。。。。。。。。。。。");
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onStop() {
        Intent intentService = new Intent(this,MediaPlayerService.class);
        stopService(intentService);
        super.onStop();
        Log.e("LeadActivity", "onStop: " );
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("LeadActivity", "onStart: " );

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("LeadActivity", "onResume: " );

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("LeadActivity", "onPause: " );

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("LeadActivity", "onDestroy: " );

    }
}
