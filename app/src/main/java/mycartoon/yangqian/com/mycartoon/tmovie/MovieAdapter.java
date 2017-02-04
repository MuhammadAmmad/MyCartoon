package mycartoon.yangqian.com.mycartoon.tmovie;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import mycartoon.yangqian.com.mycartoon.R;
import mycartoon.yangqian.com.mycartoon.entity.Result;


/**
 * Created by Administrator on 2017/1/6.
 */

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//    public static enum ITEM_TYPE {
//        ITEM_TYPE_IMAGE, ITEM_TYPE_TEXT
//    }

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private ArrayList<Result> mTitle;

    private int positionText = 0;
    private int positionImage;

    public MovieAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;


    }
    public void clearData(){
        mTitle.clear();
    }

    public void addData(ArrayList<Result> titles){
        Log.e("MovieAdapter", "MovieAdapter: "+titles.size() );
        // TODO: 2017/1/6 应该要先清空集合
        if (mTitle != null){

        }
        mTitle = titles;
    }

//    @Override
//    public int getItemViewType(int position) {
//
//        return position % 2 == 0 ? ITEM_TYPE.ITEM_TYPE_TEXT.ordinal() : ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal();
//    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        if (viewType == ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal()) {
            return new ImageViewHolder(mLayoutInflater.inflate(R.layout.item_image, parent, false));
//        } else {
//            return new TextViewHolder(mLayoutInflater.inflate(R.layout.item_text, parent, false));
//        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        if(holder instanceof TextViewHolder){
////            mUpdateHomeUI.updateItemText(position);
//            if (position != 0){
//                positionText = position - 1;
//            }
//            ((TextViewHolder) holder).mTextView.setText("排名第"+mTitle.get(positionText).getRid()+"名："+
//                    mTitle.get(positionText).getName());
//
//        }else if(holder instanceof ImageViewHolder){
////            mUpdateHomeUI.updateItemImage(position);
//            // TODO: 2017/1/6 怎么双数item中加入多个item

            Log.e("MovieAdapter", "onBindViewHolder: "+mTitle.get(position).getName() );
            ((ImageViewHolder) holder).text1.setText(mTitle.get(position).getName());
            ((ImageViewHolder) holder).text2.setText(mTitle.get(position).getWk());
            ((ImageViewHolder) holder).text3.setText(mTitle.get(position).getWboxoffice());
            ((ImageViewHolder) holder).text4.setText(mTitle.get(position).getTboxoffice());
            ((ImageViewHolder) holder).text5.setText(mTitle.get(position).getRid());
//        }

    }

    @Override
    public int getItemCount() {
        return mTitle == null ? 0 : mTitle.size();
    }
    public static class TextViewHolder extends RecyclerView.ViewHolder {

        protected static final String TAG = "TextViewHolder";

        TextView mTextView;

        public TextViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.textView);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Log.d(TAG, "TextViewHolder"+getPosition());
                }
            });
        }
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {

        protected static final String TAG = "ImageViewHolder";


        TextView text1,text2,text3,text4,text5;

        public ImageViewHolder(View itemView) {
            super(itemView);


            text1=(TextView) itemView.findViewById(R.id.tv_name_som);
            text2=(TextView) itemView.findViewById(R.id.tv_week_som);
            text3=(TextView) itemView.findViewById(R.id.tv_week_mon_som);
            text4=(TextView) itemView.findViewById(R.id.tv_month_mon_som);
            text5=(TextView) itemView.findViewById(R.id.tv_array_som);
            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Log.d(TAG, "ImageViewHolder"+getPosition());
                }
            });
        }
    }

}
