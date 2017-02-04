package mycartoon.yangqian.com.mycartoon.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Iterator;

import mycartoon.yangqian.com.mycartoon.utill.LogUtil;
import mycartoon.yangqian.com.mycartoon.view.ActionBarView;

/**
 * Created by Administrator on 2016/11/21.
 */

public class BaseActivity extends AppCompatActivity {
    public static final String TAG = BaseActivity.class.getSimpleName();
    protected ArrayList<BaseActivity> activities = new ArrayList<BaseActivity>();
    protected ActionBarView actionBarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activities.add(this);
    }

    /**
     * 加载ActionBar的方法
     * @param title
     * @param leftId
     * @param rightId
     * @param listener
     */
//    protected void initActionBar(String title, int leftId, int rightId, View.OnClickListener listener){
//        actionBarView = (ActionBarView) findViewById(R.id.actionBar);
//        actionBarView.iniiActionBarView(title,leftId,rightId,listener);
//    }
    /**
     * finish所有Activity
     */
    protected void finishAll(){
        Iterator<BaseActivity> iterator = activities.iterator();
        while (iterator.hasNext()){
            iterator.next().finish();
        }
    }

    /**
     * 跳转
     * @param target
     */
    protected void startActivity(Class<?> target){
        Intent intent = new Intent(this,target);
        startActivity(intent);
    }

    /**
     * 带数据的跳转
     * @param target
     * @param bundle
     */
    protected void startActivity(Class<?> target,Bundle bundle){
        Intent intent = new Intent(this,target);
        intent.putExtras(bundle);
        startActivity(intent);
    }



    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.d(TAG,":onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.d(TAG,":onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.d(TAG,":onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.d(TAG,":onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.d(TAG,":onDestroy()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.d(TAG,":onRestart()");
    }
}
