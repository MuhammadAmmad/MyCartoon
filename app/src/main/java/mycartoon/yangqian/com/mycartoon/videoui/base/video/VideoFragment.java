package mycartoon.yangqian.com.mycartoon.videoui.base.video;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import movie.yq.com.videoplayer.list.MediaPlayerManager;
import mycartoon.yangqian.com.mycartoon.R;
import mycartoon.yangqian.com.mycartoon.UserManager;

/**
 * Created by Administrator on 2017/1/8.
 */

public class VideoFragment extends Fragment {
    @BindView(R.id.newsListView)
    VideosListView mVideosListView;

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null){
            view = inflater.inflate(R.layout.fragment_news,container,false);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        // 首次进来，自动刷新
        mVideosListView.post(new Runnable() {
            @Override
            public void run() {
                mVideosListView.autoRefresh();
            }
        });
    }

    //初始化MediaPlayer
    @Override
    public void onResume() {
        super.onResume();
        //初始化MediaPlayer
        MediaPlayerManager.getsInstance(getContext()).onResume();
    }

    //释放MediaPlayer
    @Override
    public void onPause() {
        super.onPause();
        //释放MediaPlayer
        if (UserManager.getInstance().isPaly()){
            MediaPlayerManager.getsInstance(getContext()).onPause();
            UserManager.getInstance().setPaly(false);
        }
    }

    //移除View
    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        移除View
        ((ViewGroup)view.getParent()).removeView(view);
    }

    //清除所有监听（不再需要Ui交互）
    @Override
    public void onDestroy() {
        super.onDestroy();
        //清除所有监听（不再需要Ui交互）
        MediaPlayerManager.getsInstance(getContext()).removeAllListeners();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!UserManager.getInstance().isPaly()) return;
        //正在播放，并且用户不可见（滑动到第二个fragment）
        if (!isVisibleToUser){
            MediaPlayerManager.getsInstance(getContext()).onPause();
            UserManager.getInstance().setPaly(false);
        }
    }
}
