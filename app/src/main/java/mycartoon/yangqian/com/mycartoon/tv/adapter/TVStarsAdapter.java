package mycartoon.yangqian.com.mycartoon.tv.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import mycartoon.yangqian.com.mycartoon.R;
import mycartoon.yangqian.com.mycartoon.tv.entity.Act_s;


/**
 * Created by Administrator on 2017/1/6.
 */

public class TVStarsAdapter extends BaseAdapter {
    ArrayList<Act_s> mAct_ses=new ArrayList<Act_s>();
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    public TVStarsAdapter(Context context) {

        mLayoutInflater = LayoutInflater.from(context);
        this.mContext = context;

    }
    public void clearData(){
        mAct_ses.clear();
    }

    public void addData(ArrayList<Act_s> mTitle){
        Log.e("MovieAdapter", "MovieAdapter: "+mTitle.size() );
        // TODO: 2017/1/6 应该要先清空集合
//        if (mTitle != null){
//
//        }
        mAct_ses = mTitle;
    }

    @Override
    public int getCount() {
        return mAct_ses.size();
    }

    @Override
    public Act_s getItem(int position) {
        return mAct_ses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.layout_tv_stars_item,null);
            viewHolder = new ViewHolder();
            viewHolder.iv_tv_stars = (ImageView) convertView.findViewById(R.id.iv_tv_stars);
            viewHolder.tv_stars_item_name = (TextView) convertView.findViewById(R.id.tv__stars_item_name);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        Act_s act_s = mAct_ses.get(position);
        viewHolder.tv_stars_item_name.setText(act_s.getName());
        String cover = act_s.getImage();
        Picasso.with(mContext).load(cover).into(viewHolder.iv_tv_stars);
        return convertView;
    }

    public class ViewHolder{
        private ImageView iv_tv_stars;
        private TextView tv_stars_item_name;

    }
}
