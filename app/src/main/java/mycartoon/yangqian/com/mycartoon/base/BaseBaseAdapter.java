package mycartoon.yangqian.com.mycartoon.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/21.
 */

public abstract  class BaseBaseAdapter<DataType> extends BaseAdapter {
    private ArrayList<DataType> datas;
    protected Context context;
    protected LayoutInflater layoutInflater;

    public BaseBaseAdapter(Context context) {
        this.context = context;
        datas = new ArrayList<DataType>();
        layoutInflater = LayoutInflater.from(context);
    }

    /**
     * 获取布局加载器
     * @return
     */
    protected LayoutInflater getLayoutInflater(){
        return layoutInflater;
    }

    /**
     * 为数据源赋值
     * @param e
     */
    protected void setDatas(DataType e){
        datas.add(e);
    }

    /**
     * 为数据源赋值
     * @param e
     */
    protected void setDatas(ArrayList<DataType> e){
        datas.addAll(e);
    }

    /**
     * 获取数据源
     * @return
     */
    protected ArrayList<DataType> getDatas(){
        return datas;
    }
    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public DataType getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return initGetView(position,convertView,parent);
    }
    public abstract View initGetView(int position, View convertView, ViewGroup parent);
}
