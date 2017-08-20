package com.example.administrator.cm.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.example.administrator.cm.R;
import java.io.IOException;

/**
 * Created by Administrator on 2017/5/13.
 * 音乐服务类实现背景音乐的播放
 */

public class musicService extends Service{
    private MediaPlayer mp;

    @Override
    public void onStart(Intent intent, int startId) {
        //开始播放音乐
        mp.start();
        //音乐播放完毕的处理事件
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //循环播放
                try{
                    mp.start();
                }catch (IllegalStateException e){
                    e.printStackTrace();
                }
            }
        });
        //播放音乐时候发生错误的处理事件
        mp.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                //释放资源
                try{
                    mp.release();
                }catch (Exception e){
                    e.printStackTrace();
                }
                return false;
            }
        });
        super.onStart(intent,startId);
    }

    @Override
    public void onCreate() {
        //初始化音乐资源
        try{
            //创建音乐对象
            mp = new MediaPlayer();
            //设置音乐位置
            mp = MediaPlayer.create(musicService.this, R.raw.hotel);
            //在MediaPlayer取得播放资源与stop()之后要准备PlayBack的状态前一定要使用MediaPlayer.prepeare()
            mp.prepare();
        } catch (IllegalStateException  e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onCreate();
    }


    @Override
    public void onDestroy() {
        //服务停止时停止播放音乐并释放资源
        mp.stop();
        mp.release();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
