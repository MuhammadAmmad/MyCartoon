package mycartoon.yangqian.com.mycartoon;

import android.app.Application;

import mycartoon.yangqian.com.mycartoon.comment.ToastUtils;


/**
 * Created by Administrator on 2016/12/21 0021.
 */

public class VideoNewsApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化吐丝工具类
        ToastUtils.init(this);
    }
}
