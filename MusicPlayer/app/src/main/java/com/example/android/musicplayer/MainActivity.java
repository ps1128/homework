package com.example.android.musicplayer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewDebug;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import android.os.Handler;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static android.R.drawable.btn_star;
import static com.example.android.musicplayer.R.id.search_src_text;
import static com.example.android.musicplayer.R.id.seekbar;
import static com.example.android.musicplayer.R.layout.item;

public class MainActivity extends AppCompatActivity {


    // 当前播放的歌曲索引
    private int playingIndex = 0;


    private EditText search;

    private Button btn_search;

    private ListView listView;

    private TextView music_song;

    private TextView music_text;

    private MediaPlayer player;

    private Button btn_start;

    private Button btn_pre;

    private Button btn_next;

    private SeekBar seekbar;

    private String durationTimeStr;

    private TextView timeText;

    private Handler handler;

    private boolean flag = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        music_song = (TextView) findViewById(R.id.music_name);
        music_text = (TextView) findViewById(R.id.music_text);


        search = (EditText) findViewById(R.id.search_name);
        btn_search = (Button) findViewById(R.id.btn_search);

        btn_start = (Button) findViewById(R.id.play_btn);
        btn_next = (Button) findViewById(R.id.next_btn);
        btn_pre = (Button) findViewById(R.id.pre_btn);

        seekbar = (SeekBar) findViewById(R.id.seekbar);
        timeText = (TextView) findViewById(R.id.time_text);

        player = new MediaPlayer();


        Thread startSong = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    MediaPlayer mediaPlayer = null;
                    AssetManager am = getAssets();
                    AssetFileDescriptor afd = am.openFd("张碧晨 - 渡红尘.mp3");
                    mediaPlayer = new MediaPlayer();
                    // 使用MediaPlayer加载指定的声音文件
                    mediaPlayer.setDataSource(afd.getFileDescriptor());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        Thread bar = new Thread(new Runnable() {
            @Override
            public void run() {
                while (flag) {
                    try {
                        Thread.sleep(1000);

                        // 取得当前的播放时间位置,设置到拖动条里
                        if (player.isPlaying()) {
                            // 传递一个空消息,不需要有具体的消息内容,因为消息通道中只有一个固定的操作,而且不需要参数.
                            handler.sendEmptyMessage(0);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });



        listView = (ListView) findViewById(R.id.list);

        final ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();/*在数组中存放数据*/
        for (int i = 0; i < 1; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("歌名", "渡红尘");
            map.put("歌手","张碧晨");
            map.put("歌词","荒草枯木雪纷纷");
            listItem.add(map);
        }

        final SimpleAdapter mSimpleAdapter = new SimpleAdapter(this, listItem, item,
                new String[]{"歌名", "歌手", "歌词"}, new int[]{R.id.SongName, R.id.Singer, R.id.SongText});

        listView.setAdapter(mSimpleAdapter);//为ListView绑定适配器
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                music_song.setText("第" + arg2 + "首歌");
                Object s = (Object) mSimpleAdapter.getItem(arg2);
                music_text.setText(s + "");
                Toast.makeText(MainActivity.this,"你点击了第" + arg2 + "行",Toast.LENGTH_SHORT).show();//设置标题栏显示点击的行
            }
        });


        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                music_song.setText("");
                for (int i = 0;i < mSimpleAdapter.getCount();i++) {
                    Object s = (Object) mSimpleAdapter.getItem(i);
                    if (s.equals(search.getText().toString())) {
                        music_song.setText(s.toString());
                        music_text.setText(s.toString());
                        startService(new Intent(MainActivity.this, Music.class));
                    }
                }
            }
        });


        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player.isPlaying()) {
                    stopService(new Intent(MainActivity.this,Music.class));
//                    player.pause();
                    btn_start.setText("开始");
                } else {
                    try {
                        startService(new Intent(MainActivity.this, Music.class));
//                        AssetManager am = getAssets();
//                        AssetFileDescriptor afd = am.openFd("张碧晨 - 渡红尘.mp3");
//                        player.setDataSource(afd.getFileDescriptor());
//                        player.prepare();
//                        player.start();
                        btn_start.setText("暂停");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        btn_pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 判断当前是否在播放第一首
                if (playingIndex == 0) {
                    Toast.makeText(MainActivity.this, "当前已经播放的是第一首歌,没有前一首!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    listView.performItemClick((View) mSimpleAdapter.getItem(playingIndex - 1), playingIndex - 1,
                            ((View) (mSimpleAdapter.getItem(playingIndex - 1))).getId());
                }
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 判断当前是否在播放最后一首
                if (playingIndex == listItem.size()-1) {
                    Toast.makeText(MainActivity.this, "当前已经播放的是最后一首歌,没有后一首!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    listView.performItemClick((View) mSimpleAdapter.getItem(playingIndex + 1), playingIndex + 1,
                            ((View) (mSimpleAdapter.getItem(playingIndex + 1))).getId());
                }
            }
    });




        // 加入拖动条的监听
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // 开始播放, 同时要倒到当前拖动条的位置
                player.seekTo(seekbar.getProgress());
                player.start();
                // 修改按钮的图片
                btn_start.setText("暂停");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // 暂停播放
                player.pause();
                btn_start.setText("开始");
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // 如果是因为人为拖动造成的值改变,则时间文本需要一起修改,如果是自动改变的拖动条的值,则不需要修改
                if (fromUser) {
                    // 修改显示时间的数据
                    timeText.setText(getText(progress) + "/"
                            + durationTimeStr);
                }
            }
        });





        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sound:

                Toast.makeText(this,"声音",Toast.LENGTH_SHORT).show();
                break;
            case R.id.theme:
                Toast.makeText(this,"主题",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
