package com.example.android.testfive;

import android.os.Looper;
import android.support.v7.app.AppCompatActivity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.testfive.R;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.x;
import static com.example.android.testfive.R.id.textView;


public class MainActivity extends AppCompatActivity {
    private boolean isoncl = true;


    class newThread extends Thread {

        public int a;


        public void setMsg(int i)
        {
            a = i;
        }

        public int getMsg()
        {
            return a;
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.textView);

        final EditText editText = (EditText)findViewById(R.id.editView);


        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                textView.setText(msg.arg1+"");
            }
        };



        final newThread myWorker = new newThread() {
        @Override
        public void run() {

            int t = Integer.parseInt(editText.getText().toString());
            setMsg(t);
            int x = this.getMsg();
          //  int x = 7;
            int foctor = x;
            int a = 0;
            while (foctor >= 2) {
                Message msg = new Message();
                a = x;
                foctor--;
                x = a * foctor;
//                x = foctor;
                msg.arg1 = x;
                handler.sendMessage(msg);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

//            Message msg = handler.obtainMessage();//同 new Message();
//            msg.arg1 = -1;
//            handler.sendMessage(msg);
        }
    };



        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isoncl){
      //              Toast.makeText(MainActivity.this,msg.arg1,Toast.LENGTH_SHORT);
        //            Toast.makeText(MainActivity.this,Integer.parseInt((editText.getText().toString())),Toast.LENGTH_SHORT);



//                    final newThread newthread = new newThread();
//                    newthread.getMessage(msg);
//                    newthread.start();
//
//                    handler.sendMessage(msg);
//                    Thread workThread = new Thread(null, myWorker, "WorkThread");
//                    workThread.start();

                    Thread workThread = new Thread(null, myWorker, "WorkThread");
                    workThread.start();
                    isoncl=false;
                }

            }
        });
    }


}