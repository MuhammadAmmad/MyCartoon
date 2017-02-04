package mycartoon.yangqian.com.mycartoon.service;

import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;

/**
 * Created by Administrator on 2016/11/22.
 */

public class MediaPlayerService extends Service {
    private MediaPlayer mediaPlayer;

    public MediaPlayerService() {
    }

    /**
     * 加载音乐资源
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        AssetManager assetManager = getAssets();
        try {

            AssetFileDescriptor fileDescriptor = assetManager.openFd("the_rootless_oneday.mp3");

            mediaPlayer = new MediaPlayer();
            //设置音乐资源
            mediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(),fileDescriptor.getStartOffset(),fileDescriptor.getLength());
            //设置音乐循环
            mediaPlayer.setLooping(true);
            //准备音乐
            mediaPlayer.prepare();
            //开始音乐
            mediaPlayer.start();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        //停止音乐
        mediaPlayer.stop();
        //释放音乐资源
        mediaPlayer.release();
        mediaPlayer = null;
        super.onDestroy();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");

    }
}
