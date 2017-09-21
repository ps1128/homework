package com.example.android.bmobtest;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.system.ErrnoException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static android.os.Build.VERSION_CODES.M;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button regist;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        final Thread myWorker1 = new Thread() {
            @Override
            public void run() {

                Bmob.initialize(getApplicationContext(), "805195fa074f9f330e45a3ecdc1647fa");
                BmobUser user = new BmobUser();
                user.setUsername(username.getText().toString());
                user.setPassword(password.getText().toString());

                user.signUp(new SaveListener<BmobUser>() {
                    @Override
                    public void done(BmobUser s, BmobException e) {
                        try {
                            if (e == null)
                                Toast.makeText(MainActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(MainActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                            Thread.sleep(1000);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }

                    }

                });

            }
        };
        final Thread myWorker2 = new Thread() {
            @Override
            public void run() {
                Bmob.initialize(getApplicationContext(), "805195fa074f9f330e45a3ecdc1647fa");
                try {
                    BmobUser.loginByAccount(username.getText().toString(),password.getText().toString(), new LogInListener<MyUser>() {
                        @Override
                        public void done(MyUser user, BmobException e) {
                            if(user!=null){
                                Toast.makeText(MainActivity.this, "登录成功" , Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent();
                                intent.setClass(MainActivity.this,SecondActivity.class);
                                startActivity(intent);
                            }
                        }
                    });
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };




        username = (EditText) findViewById(R.id.un);
        password = (EditText) findViewById(R.id.pw);

        regist = (Button) findViewById(R.id.regist);
        login = (Button) findViewById(R.id.login);



        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Thread workThread = new Thread(null, myWorker1, "WorkThread");
                workThread.start();


            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread workThread = new Thread(null, myWorker2, "WorkThread");
                workThread.start();
            }
        });


    }
}
