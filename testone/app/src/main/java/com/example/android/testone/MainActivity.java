package com.example.android.testone;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.AlertDialog;

import java.lang.reflect.Field;

import static android.os.Build.VERSION_CODES.M;

public class MainActivity extends AppCompatActivity {

    private Button loginButton;
    private Button whyButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        whyButton = (Button)findViewById(R.id.why);
        whyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "这个按钮是干什么的", Toast.LENGTH_LONG).show();
            }
        });

        loginButton = (Button)findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater factory = LayoutInflater.from(MainActivity.this);
                final View textEntryView = factory.inflate(R.layout.login, null);
                new AlertDialog.Builder(MainActivity.this)
                        //对话框的标题
                        .setTitle("登陆")
                        //设定显示的View
                        .setView(textEntryView)
                        //对话框中的“登陆”按钮的点击事件
                        .setPositiveButton("登陆", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                                final EditText etUserName = (EditText)textEntryView.findViewById(R.id.user);
                                final EditText etPassword = (EditText)textEntryView.findViewById(R.id.password);

                                String userName = etUserName.getText().toString().trim();
                                String password = etPassword.getText().toString().trim();

                                if(userName.equals("abc") && password.equals("123")){
                                    Intent intent = new Intent();
                                    intent.setClass(MainActivity.this, OperationActivity.class);
                                    startActivity(intent);
                                    MainActivity.this.finish();

                                }else{
                                    Toast.makeText(MainActivity.this, "密码或用户名错误", Toast.LENGTH_SHORT).show();
                                    try {
                                        Field field = dialog.getClass().getSuperclass()
                                                .getDeclaredField("mShowing");
                                        field.setAccessible(true);
                                        field.set(dialog, false);
                                        dialog.dismiss();

                                    } catch (Exception e) {

                                    }
                                }
                            }
                        })
                        .setNegativeButton("退出", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                MainActivity.this.finish();
                            }
                        })
                        .create().show();
            }
        });
    }

}
