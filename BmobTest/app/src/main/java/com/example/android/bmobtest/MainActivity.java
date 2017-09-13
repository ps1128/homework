package com.example.android.bmobtest;

import android.nfc.Tag;
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


        Bmob.initialize(getApplicationContext(),"805195fa074f9f330e45a3ecdc1647fa");


        username = (EditText) findViewById(R.id.un);
        password = (EditText) findViewById(R.id.pw);

        regist = (Button) findViewById(R.id.regist);
        login = (Button) findViewById(R.id.login);

        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BmobUser user = new BmobUser();
                user.setUsername(username.getText().toString());
                user.setPassword(password.getText().toString());

                user.signUp(new SaveListener<BmobUser>() {
                    @Override
                    public void done(BmobUser s, BmobException e) {
                        if(e == null)
                            Toast.makeText(MainActivity.this, "注册成功:" + s.toString(), Toast.LENGTH_LONG);
                        else
                            Toast.makeText(MainActivity.this, "注册失败:" + s.toString(), Toast.LENGTH_LONG);
                    }

                });
            }


//
//                //上传
//                user.signUp(new SaveListener<MyUser>() {
//                    @Override
//                    public void done(MyUser s, BmobException e) {
//                        try {
//                            if (e == null) {
//                                //注册成功提示
//                                Toast.makeText(MainActivity.this, "注册成功:" + s.toString(), Toast.LENGTH_LONG);
//                            }
//                        }catch (Exception e1){
//                            Log.e("error","This is error");
//                        }
//                    }
//                });
//            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BmobUser.loginByAccount(username.getText().toString(),password.getText().toString(), new LogInListener<MyUser>() {
//
                    @Override
                    public void done(MyUser user, BmobException e) {
                        if(user!=null){
                            Toast.makeText(MainActivity.this, "登录成功:"+ user.toString() , Toast.LENGTH_LONG);
                            Log.i("smile","用户登陆成功");
                        }
                    }
                });
            }
        });


    }
}
