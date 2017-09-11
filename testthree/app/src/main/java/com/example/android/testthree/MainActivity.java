package com.example.android.testthree;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.zhuan);
        // 响应按钮btn事件
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 实例化Intent
                Intent it = new Intent();
                //设置Intent的Action属性
                it.setAction(Intent.ACTION_DIAL);
                it.setData(Uri.parse("tel:18810599896"));
                // 启动Activity
                startActivity(it);
            }
        });
    }
}
