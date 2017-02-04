package mycartoon.yangqian.com.mycartoon.videoui.base.video;

import android.content.Context;
import android.util.AttributeSet;

import mycartoon.yangqian.com.mycartoon.internetclient.entity.NewsEntity;
import mycartoon.yangqian.com.mycartoon.internetclient.result.QueryResult;
import mycartoon.yangqian.com.mycartoon.videoui.base.BaseResourceView;
import retrofit2.Call;

/**
 * Created by Administrator on 2017/1/8.
 */

public class VideosListView extends BaseResourceView<NewsEntity, VideosItemView> {
    public VideosListView(Context context) {
        super(context);
    }

    public VideosListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VideosListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected Call<QueryResult<NewsEntity>> queryData(int limit, int skip) {
        return newsApi.getVideoNewsList(limit,skip);
    }

    @Override
    protected int getLimit() {
        return 5;
    }

    @Override
    protected VideosItemView createItemView() {
        return new VideosItemView(getContext());
    }
}
