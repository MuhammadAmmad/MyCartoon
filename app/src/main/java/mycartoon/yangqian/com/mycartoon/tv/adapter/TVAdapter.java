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
import mycartoon.yangqian.com.mycartoon.tv.entity.Video_rec;


/**
 * Created by Administrator on 2017/1/6.
 */

public class TVAdapter extends BaseAdapter {
    ArrayList<Video_rec> mVideo_recs=new ArrayList<Video_rec>();
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    public TVAdapter(Context context) {

        mLayoutInflater = LayoutInflater.from(context);
        this.mContext = context;

    }
    public void clearData(){
        mVideo_recs.clear();
    }

    public void addData(ArrayList<Video_rec> mTitle){
        Log.e("MovieAdapter", "MovieAdapter: "+mTitle.size() );
        // TODO: 2017/1/6 应该要先清空集合
//        if (mTitle != null){
//
//        }
        mVideo_recs = mTitle;
    }

    @Override
    public int getCount() {
        return mVideo_recs.size();
    }

    @Override
    public Video_rec getItem(int position) {
        return mVideo_recs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.layout_tv_item,null);
            viewHolder = new ViewHolder();
            viewHolder.iv_tv = (ImageView) convertView.findViewById(R.id.iv_tv);
            viewHolder.tv_item_name = (TextView) convertView.findViewById(R.id.tv_item_name);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        Video_rec video_rec = mVideo_recs.get(position);
        viewHolder.tv_item_name.setText(video_rec.getTitle());
        String cover = video_rec.getCover();
        Picasso.with(mContext).load(cover).into(viewHolder.iv_tv);
        return convertView;
    }

    public class ViewHolder{
        private ImageView iv_tv;
        private TextView tv_item_name;

    }
}
