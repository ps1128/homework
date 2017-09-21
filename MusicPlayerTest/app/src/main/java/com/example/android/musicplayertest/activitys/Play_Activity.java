package com.example.android.musicplayertest.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;

import com.example.android.musicplayertest.R;

import java.util.Timer;
import java.util.TimerTask;

public class Play_Activity extends AppCompatActivity {

    private ImageButton btprevious;
    private ImageButton btpause;
    private ImageButton btnext;
    private SeekBar seekBar;
    private Timer timer;
    private TimerTask timerTask;
    boolean ischange=false;
    private final String MUSIC_ACTIVITY_ACTION_PREVIOUS = "ACTIVITY.To.PREVIOUS";
    private final String MUSIC_ACTIVITY_ACTION_NEXT = "ACTIVITY.To.NEXT";
    private final String MUSIC_ACTIVITY_ACTION_PLAY = "ACTIVITY.To.PAUSE";
    private final String MUSIC_SEEKBAR_ACTION_CHANGE = "MEDIAPLAYER.TO.WHICH";
    private final String MUSIC_NOTIFICAION_INTENT_KEY = "type";
    private final int MUSIC_ACTIVITY_VALUE_PREVIOUS = 1001;
    private final int MUSIC_ACTIVITY_VALUE_NEXT = 1002;
    private final int MUSIC_ACTIVITY_VALUE_PLAY =1003;

    @Override
    protected void onDestroy() {
        finish();
        super.onDestroy();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_main);
        btprevious = (ImageButton) findViewById(R.id.shang);
        btpause = (ImageButton) findViewById(R.id.pau);
        btnext = (ImageButton) findViewById(R.id.next);
        seekBar = (SeekBar) findViewById(R.id.seekBar12);
        seekBar.setMax(100);


        /*
        * 上一首歌的监听器
        * */
       btprevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(MUSIC_ACTIVITY_ACTION_PREVIOUS);
                intent.putExtra(MUSIC_NOTIFICAION_INTENT_KEY,MUSIC_ACTIVITY_VALUE_PREVIOUS);
                sendBroadcast(intent);
            }
        });
        /*
        *下一首歌的监听器
        * */
        btnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(MUSIC_ACTIVITY_ACTION_NEXT);
                intent.putExtra(MUSIC_NOTIFICAION_INTENT_KEY,MUSIC_ACTIVITY_VALUE_NEXT);
                sendBroadcast(intent);
            }
        });
        /*
        * 播放音乐的监听器
        * */
        btpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(MUSIC_ACTIVITY_ACTION_PLAY);
                intent.putExtra(MUSIC_NOTIFICAION_INTENT_KEY,MUSIC_ACTIVITY_VALUE_PLAY);
                sendBroadcast(intent);
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                ischange=true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                ischange=false;
            }
        });

    }
}
