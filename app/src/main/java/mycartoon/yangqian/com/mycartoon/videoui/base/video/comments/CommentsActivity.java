package mycartoon.yangqian.com.mycartoon.videoui.base.video.comments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import movie.yq.com.videoplayer.part.SimpleVideoPlayer;
import mycartoon.yangqian.com.mycartoon.R;
import mycartoon.yangqian.com.mycartoon.UserManager;
import mycartoon.yangqian.com.mycartoon.comment.CommonUtils;
import mycartoon.yangqian.com.mycartoon.comment.ToastUtils;
import mycartoon.yangqian.com.mycartoon.internetclient.entity.NewsEntity;
import mycartoon.yangqian.com.mycartoon.internetclient.result.CollectResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentsActivity extends AppCompatActivity {

        private static final String KEY_NEWS = "KEY_NEWS";

        //对外公开一个跳转进来的方法
    public static void open(Context context, NewsEntity newsEntity){
        Intent intent = new Intent(context,CommentsActivity.class);
        intent.putExtra(KEY_NEWS,newsEntity);
        context.startActivity(intent);
        Log.e("CommentsActivity", "open: "+newsEntity.getNewsTitle() );
    }

    private NewsEntity newsEntity;

    @BindView(R.id.tvTitle)TextView tvTitle;
    @BindView(R.id.toolbar)Toolbar toolbar;
    @BindView(R.id.simpleVideoPlayer)SimpleVideoPlayer simpleVideoPlayer;
    @BindView(R.id.commentsListView)CommentsListView commentsListView;
    private EditCommentFragment editCommentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        Log.e("CommentsActivity", "onCreate: " );
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        //拿到新闻数据
        newsEntity = (NewsEntity) getIntent().getSerializableExtra(KEY_NEWS);
        //设置toolbar
        setSupportActionBar(toolbar);
        //给左上角加返回图标(返回按钮)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //设置标题
        tvTitle.setText(newsEntity.getNewsTitle());
        //初始化simpleVideoPlayer，设置数据源
        String videoPath = CommonUtils.encodeUrl(newsEntity.getVideoUrl());
        simpleVideoPlayer.setVideoPath(videoPath);
        //初始化commentsListView，设置newsid
        commentsListView.setNewsId(newsEntity.getObjectId());
        commentsListView.autoRefresh();
    }

    // #######################   视频相关  start  #####################

    @Override
    protected void onResume() {
        super.onResume();
        simpleVideoPlayer.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        simpleVideoPlayer.onPause();
    }

    // #######################   视频相关  end  #####################

    // #######################   toolbar相关   #####################
    //创建一个菜单栏
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.activity_comments,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //左上角返回按钮
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        //判断是否离线
        if (UserManager.getInstance().isOffline()){
            ToastUtils.showShort(R.string.please_login_first);
            return true;
        }
        //收藏
//        if (item.getItemId() == R.id.menu_item_like){
//            String newsId = newsEntity.getObjectId();
//            String userId = UserManager.getInstance().getObjectId();
//            NewsApi newsApi_cloud = BombClient.getInstance().getNewsApi_cloud();
//            Call<CollectResult> call = newsApi_cloud.collectNews(newsId,userId);
//            call.enqueue(callback);
//        }
        //评论
        if (item.getItemId() == R.id.menu_item_comment){
            if (editCommentFragment == null){
                String newsId = newsEntity.getObjectId();
                editCommentFragment = EditCommentFragment.getInstance(newsId);
                editCommentFragment.setListener(listener);
            }
            editCommentFragment.show(getSupportFragmentManager(),"Edit Comment");
        }
        return super.onOptionsItemSelected(item);
    }

    private Callback<CollectResult> callback = new Callback<CollectResult>() {
        @Override
        public void onResponse(Call<CollectResult> call, Response<CollectResult> response) {
            CollectResult result = response.body();
            if (result.isSuccess()){
                ToastUtils.showShort(R.string.like_success);
            }else{
                ToastUtils.showShort(R.string.like_failure + result.getError());
            }
        }

        @Override
        public void onFailure(Call<CollectResult> call, Throwable t) {
            ToastUtils.showShort(t.getMessage());
        }
    };

    private EditCommentFragment.OnCommentSuccessListener listener = new EditCommentFragment.OnCommentSuccessListener() {
        @Override
        public void onCommentSuccess() {
            editCommentFragment.dismiss();
            //刷新，显示最新评论
            commentsListView.autoRefresh();
        }
    };
}
