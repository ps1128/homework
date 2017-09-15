package com.example.android.bmobtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;


/**
 * Created by peisu on 2017/9/14.
 */

public class SecondActivity extends Activity {

    private Button btn_logout;

    private EditText ed_name;
    private EditText ed_address;

    private Button btn_cc;
    private Button btn_xs;
    private Button btn_gx;
    private Button btn_sc;

    private TextView tv_name;
    private TextView tv_address;

    private TextView tv_id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                String id = (String) msg.obj;
                tv_id.setText(id.toString());
                Log.i("info", id);
            }
        };

        final Thread myWorker3 = new Thread() {
            @Override
            public void run() {
                Bmob.initialize(getApplicationContext(), "805195fa074f9f330e45a3ecdc1647fa");
                try {
                    Person p2 = new Person();
                    p2.setName(ed_name.getText().toString());
                    p2.setAddress(ed_address.getText().toString());
                    p2.save(new SaveListener<String>() {
                        @Override
                        public void done(String objectId, BmobException e) {
                            if (e == null) {
                                Toast.makeText(SecondActivity.this, "存储数据成功，返回objectId为：" + objectId, Toast.LENGTH_SHORT).show();
                                String id = "" + objectId;
                                Message msg = Message.obtain();
                                msg.obj = id;
                                handler.sendMessage(msg);
                            } else {
                                Toast.makeText(SecondActivity.this, "存储数据失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        final Thread myWorker4 = new Thread() {
            @Override
            public void run() {
                Bmob.initialize(getApplicationContext(), "805195fa074f9f330e45a3ecdc1647fa");
                try {
                    BmobQuery<Person> bmobQuery = new BmobQuery<Person>();
                    bmobQuery.getObject(tv_id.getText().toString(), new QueryListener<Person>() {
                        @Override
                        public void done(Person person, BmobException e) {
                            if (e == null) {
                                tv_name.setText(person.getName().toString());
                                tv_address.setText(person.getAddress().toString());
                                Toast.makeText(SecondActivity.this, "查询成功", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SecondActivity.this, "查询失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        final Thread myWorker5 = new Thread() {
            @Override
            public void run() {
                Bmob.initialize(getApplicationContext(), "805195fa074f9f330e45a3ecdc1647fa");
                try {
                    Person p2 = new Person();
                    if (ed_name.getText().toString() != null)
                        p2.setName(ed_name.getText().toString());
                    if (ed_address.getText().toString() != null)
                        p2.setAddress(ed_address.getText().toString());
                    p2.update(tv_id.getText().toString(), new UpdateListener() {

                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                Toast.makeText(SecondActivity.this, "更新成功", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SecondActivity.this, "更新失败", Toast.LENGTH_SHORT).show();
                            }
                        }

                    });
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };



        final Thread myWorker6 = new Thread() {
            @Override
            public void run() {
                Bmob.initialize(getApplicationContext(), "805195fa074f9f330e45a3ecdc1647fa");
                try {
                    Person p2 = new Person();
                    p2.setObjectId(tv_id.getText().toString());
                    p2.delete(new UpdateListener() {

                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                tv_id.setText("XXXXXXXXX");
                                Toast.makeText(SecondActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(SecondActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
                            }
                        }

                    });
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };


        btn_logout = (Button) findViewById(R.id.logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BmobUser.logOut();   //清除缓存用户对象
                Toast.makeText(SecondActivity.this, "退出成功", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        ed_name = (EditText) findViewById(R.id.ed_name);
        ed_address = (EditText) findViewById(R.id.ed_address);

        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_address = (TextView) findViewById(R.id.tv_address);

        tv_id = (TextView) findViewById(R.id.tv_id);

        btn_cc = (Button) findViewById(R.id.btn_cc);
        btn_cc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread workThread = new Thread(null, myWorker3, "WorkThread");
                workThread.start();
            }
        });

        btn_xs = (Button) findViewById(R.id.btn_xs);
        btn_xs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread workThread = new Thread(null, myWorker4, "WorkThread");
                workThread.start();
            }
        });

        btn_gx = (Button) findViewById(R.id.btn_gx);
        btn_gx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread workThread = new Thread(null, myWorker5, "WorkThread");
                workThread.start();
            }
        });

        btn_sc = (Button) findViewById(R.id.btn_sc);
        btn_sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread workThread = new Thread(null, myWorker6, "WorkThread");
                workThread.start();
            }
        });
    }
}
