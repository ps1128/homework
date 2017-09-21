package com.example.android.musicplayer;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.IBinder;


import android.app.Service;
import android.widget.EditText;

/**
 * Created by peisu on 2017/9/21.
 */

public class Music extends Service {
    private MediaPlayer player;
    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        try {
            AssetManager am = getAssets();
            AssetFileDescriptor afd = am.openFd("张碧晨 - 渡红尘.mp3");
            player.setDataSource(afd.getFileDescriptor());
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        try {
            player.prepare();
            player.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flags;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
    }
}
