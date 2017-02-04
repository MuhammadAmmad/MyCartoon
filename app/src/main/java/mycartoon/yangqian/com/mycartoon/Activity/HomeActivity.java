package mycartoon.yangqian.com.mycartoon.Activity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mycartoon.yangqian.com.mycartoon.R;
import mycartoon.yangqian.com.mycartoon.UserManager;
import mycartoon.yangqian.com.mycartoon.base.BaseActivity;
import mycartoon.yangqian.com.mycartoon.fragment.MoviesFragment;
import mycartoon.yangqian.com.mycartoon.fragment.TVFragment;
import mycartoon.yangqian.com.mycartoon.user.FragmentForgetPassWord;
import mycartoon.yangqian.com.mycartoon.user.LoginFragment;
import mycartoon.yangqian.com.mycartoon.user.RegisterFragment;
import mycartoon.yangqian.com.mycartoon.videoui.base.video.VideoFragment;

public class HomeActivity extends BaseActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener, RegisterFragment.OnRegisterSuccessListener, LoginFragment.OnLoginSuccessListener, FragmentForgetPassWord.FragmentForgetPassWordListener {

    private LinearLayout ll_home_shouye_normal, ll_home_shugui_normal,
            ll_home_wode_normal;

    private MoviesFragment mMoviesFragment;
    private TVFragment mTVFragment;

    private VideoFragment mVideoFragment;

    private TextView tv_honme_shouye, tv_honme_shugui, tv_honme_wode;
    private ImageView iv_honme_shouye, iv_honme_shugui, iv_honme_wode;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.navigation_start)
    NavigationView mNavigationStart;
    @BindView(R.id.drawerLayout)
    DrawerLayout mDrawerLayout;
    private RegisterFragment mRegisterFragment;
    private LoginFragment mLoginFragment;
    private FragmentForgetPassWord mFragmentForgetPassWord;
    private TextView mTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

//        String title = getResources().getString(R.string.app_name);
//        initActionBar(title,-1,-1,this);
        fragmentManager = getSupportFragmentManager();
        initUser();
        initData();


    }

    private void initUser() {

//        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
//        mNavigationStart = (NavigationView) findViewById(R.id.navigation_start);
//        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        UserManager userManager = UserManager.getInstance();
        if (!userManager.isOffline()) {
            userOnLine(userManager.getUsername(), userManager.getObjectId());
        }
        // toolbar的处理
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        // drawerLayout设置监听
        ActionBarDrawerToggle actionBarDrawerToggle =
                new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        Log.e("HomeActivity", "actionBarDrawerToggle: " + actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();// 同步状态
        Log.e("HomeActivity", "mDrawerLayout: " + mDrawerLayout);
        Log.e("HomeActivity", "mToolbar: " + mToolbar);
        Log.e("HomeActivity", "mNavigationStart: " + mNavigationStart);

        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        mNavigationStart.setNavigationItemSelectedListener(this);
        mNavigationStart.getMenu().getItem(5).setVisible(false);


        mTextView = (TextView) mNavigationStart.getHeaderView(0).findViewById(R.id.tv_username);

        ImageView imageView = (ImageView) mNavigationStart.getHeaderView(0).findViewById(R.id.iv_usericon);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 2017/1/4 更换头像
            }
        });

        int[][] states = new int[][]{

                new int[]{android.R.attr.state_activated}, // enabled
                new int[]{android.R.attr.state_active}, // disabled
                new int[]{android.R.attr.state_checked}, // unchecked
                new int[]{android.R.attr.state_focused}, // pressed
                new int[]{android.R.attr.state_enabled}, // enabled
                new int[]{-android.R.attr.state_enabled}, // disabled
                new int[]{-android.R.attr.state_checked}, // unchecked
                new int[]{android.R.attr.state_pressed}  // pressed
        };
        int[] colors = new int[]{

                ContextCompat.getColor(this,R.color.black),
                ContextCompat.getColor(this,R.color.login_suc),
                ContextCompat.getColor(this,R.color.login_suc),
                ContextCompat.getColor(this,R.color.login_suc),
                ContextCompat.getColor(this,R.color.black),
                ContextCompat.getColor(this,R.color.black),
                ContextCompat.getColor(this,R.color.login_suc),
                ContextCompat.getColor(this,R.color.black)
//              ContextCompat.getColor(this,R.color.colorPrimary),

        };
        ColorStateList colorStateList = new ColorStateList(states, colors);
        mNavigationStart.setItemTextColor(colorStateList);
        mNavigationStart.setItemIconTintList(colorStateList);
    }

    private void initData() {

        tv_honme_shouye = (TextView) findViewById(R.id.tv_honme_shouye);
        tv_honme_shugui = (TextView) findViewById(R.id.tv_honme_shugui);
        tv_honme_wode = (TextView) findViewById(R.id.tv_honme_wode);

        iv_honme_shouye = (ImageView) findViewById(R.id.iv_honme_shouye);
        iv_honme_shugui = (ImageView) findViewById(R.id.iv_honme_shugui);
        iv_honme_wode = (ImageView) findViewById(R.id.iv_honme_wode);

        ll_home_shouye_normal = (LinearLayout) findViewById(R.id.ll_home_shouye_normal);
        ll_home_shugui_normal = (LinearLayout) findViewById(R.id.ll_home_shugui_normal);
        ll_home_wode_normal = (LinearLayout) findViewById(R.id.ll_home_wode_normal);

        ll_home_shouye_normal.setOnClickListener(this);
        ll_home_shugui_normal.setOnClickListener(this);
        ll_home_wode_normal.setOnClickListener(this);

        tv_honme_shouye.setTextColor(Color.rgb(251, 198, 41));
        iv_honme_shouye.setImageResource(R.drawable.jiapian_y);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (mMoviesFragment == null) {
            mMoviesFragment = new MoviesFragment();
            fragmentTransaction.add(R.id.fl_home_fragment, mMoviesFragment);
        } else {
            fragmentTransaction.show(mMoviesFragment);
        }
        fragmentTransaction.commit();


    }


    @Override
    public void onClick(View v) {
        fragmentTransaction = fragmentManager.beginTransaction();
        hideFragment(fragmentTransaction);
        switch (v.getId()) {
//
            case R.id.ll_home_shouye_normal:
                tv_honme_shouye.setTextColor(Color.rgb(251, 198, 41));
                iv_honme_shouye.setImageResource(R.drawable.jiapian_y);
                if (mMoviesFragment == null) {
                    mMoviesFragment = new MoviesFragment();
                    fragmentTransaction.add(R.id.fl_home_fragment, mMoviesFragment);
                } else {
                    fragmentTransaction.show(mMoviesFragment);
                }
                break;
            case R.id.ll_home_wode_normal:
                tv_honme_wode.setTextColor(Color.rgb(251, 198, 41));
                iv_honme_wode.setImageResource(R.drawable.sousuo_y);

                if (mTVFragment == null) {
                    mTVFragment = new TVFragment();
                    fragmentTransaction.add(R.id.fl_home_fragment, mTVFragment);
                } else {
                    fragmentTransaction.show(mTVFragment);
                }
                break;
            case R.id.ll_home_shugui_normal:
                tv_honme_shugui.setTextColor(Color.rgb(251, 198, 41));
                iv_honme_shugui.setImageResource(R.drawable.yingxun_y);
                if (mVideoFragment == null) {
                    mVideoFragment = new VideoFragment();
                    fragmentTransaction.add(R.id.fl_home_fragment, mVideoFragment);
                } else {
                    fragmentTransaction.show(mVideoFragment);
                }
                break;
        }
        fragmentTransaction.commit();

    }

    private void hideFragment(FragmentTransaction fragmentTransaction) {
        if (mMoviesFragment != null) {
            fragmentTransaction.hide(mMoviesFragment);
            tv_honme_shouye.setTextColor(Color.rgb(0, 0, 0));
            iv_honme_shouye.setImageResource(R.drawable.jiapian);
        }

        if (mTVFragment != null) {
            fragmentTransaction.hide(mTVFragment);
            tv_honme_wode.setTextColor(Color.rgb(0, 0, 0));
            iv_honme_wode.setImageResource(R.drawable.sousuo_home);

        }
        if (mVideoFragment != null) {
            fragmentTransaction.hide(mVideoFragment);
            tv_honme_shugui.setTextColor(Color.rgb(0, 0, 0));
            iv_honme_shugui.setImageResource(R.drawable.yingxun);
        }



    }

    // Navigation每一条的选中监听
    @Override
    public boolean onNavigationItemSelected(final MenuItem item) {

        int id = item.getItemId();


        Menu menu = mNavigationStart.getMenu();
//        menu.getItem(3).setEnabled(false);
//        menu.getItem(4).setEnabled(false);
        for (int i = 0; i < menu.size(); i++) {
            if (menu.getItem(i).isChecked()) {
                menu.getItem(i).setChecked(false);
                continue;
            }
        }
        item.setChecked(true);

        switch (item.getItemId()) {
            //登录............
            case R.id.menu_hide:

                if (mLoginFragment == null) {
                    mLoginFragment = new LoginFragment();
                    mLoginFragment.setListener(this);
                }
                mLoginFragment.show(getSupportFragmentManager(), "Login Dialog");
                break;
            //注册.........
            case R.id.menu_list:

                if (mRegisterFragment == null) {
                    mRegisterFragment = new RegisterFragment();
                    //添加注册成功的监听
                    mRegisterFragment.setListener(this);
                }
                mRegisterFragment.show(getSupportFragmentManager(), "Register Dialog");
                break;
            //忘记密码....................
            case R.id.menu_help:

                if (mFragmentForgetPassWord == null){
                    mFragmentForgetPassWord = new FragmentForgetPassWord();
                    mFragmentForgetPassWord.setFragmentForgetPassWordListener(this);
                }
                mFragmentForgetPassWord.show(getSupportFragmentManager(),"fp");

                break;
            //退出登录............
            case R.id.menu_loginout:

                //用户下线
                userOffline();
                mDrawerLayout.closeDrawer(GravityCompat.START);
                break;
        }


        return true;
    }

    private void userOffline() {
        //清除用户相关信息
        UserManager.getInstance().clear();
        mTextView.setText("游客");
//        Resources resource = (Resources) getBaseContext().getResources();
//        ColorStateList csl = (ColorStateList) resource.getColorStateList(R.color.black);
//        mNavigationStart.setItemTextColor(csl);
//        mNavigationStart.getMenu().getItem(5).setChecked(false);
        mNavigationStart.getMenu().getItem(5).setVisible(false);

    }

    // 处理back返回键
    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public void registerSuccess(String username, String objectId) {

        //关闭注册的对话框
        mRegisterFragment.dismiss();
        //用户上线
        userOnLine(username, objectId);
        mNavigationStart.getMenu().getItem(1).setChecked(false);
    }

    @Override
    public void sucess() {
        mFragmentForgetPassWord.dismiss();
        mNavigationStart.getMenu().getItem(2).setChecked(false);
    }


    @Override
    public void outMenu() {

        mDrawerLayout.closeDrawer(GravityCompat.START);


    }

    @Override
    public void loginSuccess(String username, String objectId) {

        mLoginFragment.dismiss();
        //用户上线
        userOnLine(username, objectId);
        mNavigationStart.getMenu().getItem(0).setChecked(false);
    }

    private void userOnLine(String username, String objectId) {
//        Resources resource = (Resources) getBaseContext().getResources();
//        ColorStateList csl = (ColorStateList) resource.getColorStateList(R.color.login_suc);
//        mNavigationStart.setItemTextColor(csl);
//        mNavigationStart.getMenu().getItem(5).setChecked(true);
        mNavigationStart.getMenu().getItem(5).setVisible(true);
        mTextView.setText(username);
        // 存储用户信息
        UserManager.getInstance().setUsername(username);
        UserManager.getInstance().setObjectId(objectId);
    }


    public void cancelClick(){
        if (mNavigationStart.isClickable()){
            mNavigationStart.setClickable(false);

        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        cancelClick();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cancelClick();
    }
}
