package mycartoon.yangqian.com.mycartoon.base;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import mycartoon.yangqian.com.mycartoon.utill.LogUtil;

/**
 * Created by Administrator on 2016/11/21.
 */

public class BaseViewPagerAdapter extends PagerAdapter {
    protected ArrayList<View> views = new ArrayList<View>();
    protected Context context;
    public BaseViewPagerAdapter(Context context) {
        this.context=context;
    }

    public void addView(View view){
        if (view != null){
            views.add(view);
        }

    }

    public void addView(ArrayList<View> viewArrayList){
        if (viewArrayList != null){
            views.addAll(viewArrayList);
        }

    }

    public ArrayList<View> getViews(){
        return views;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = views.get(position);
        container.removeView(view);
        LogUtil.e("BaseViewPagerAdapter","destroyItem。。。。。。。。。。。。。");
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = views.get(position);
        container.addView(view);
        LogUtil.e("BaseViewPagerAdapter","运行。。。。。。。。。。。。。");
        return view;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
