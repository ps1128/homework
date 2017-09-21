package com.example.android.musicplayertest.activitys;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.SeekBar;

import java.io.IOException;

public class Media{
    private MediaPlayer mediaPlayer;
    private String path;
    private Uri uri;
    private Context context;
    private SeekBar seekBar;
    public void setUri(){
        uri = Uri.parse(path);
    }
    //MediaPlayer读取器
    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }
    //url修改器
    public void setPath(String path) {
        this.path = path;
    }
    //构造方法
    public Media(MediaPlayer mediaPlayer,String path,Context context) {
        this.mediaPlayer = mediaPlayer;
        this.path = path;
        this.context = context;
    }
    //音乐开始播放
    public void musicstart() {
        Thread thread=new Thread(){
            @Override
            public void run() {
                try {
                    if(!mediaPlayer.isPlaying()){
                        mediaPlayer.reset();
                    }
                    mediaPlayer.setDataSource(context,uri);
                    mediaPlayer.prepareAsync();
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
    //暂停播放
    public void musicpause() {
        mediaPlayer.pause();
    }
    //停止播放
    public void musicstop() {
        mediaPlayer.stop();
    }

}
