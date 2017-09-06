package com.example.android.testtwo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.data;

public class MainActivity extends AppCompatActivity {

    private Button btn = null;
    private TextView firstTxt;
    private final static int REQUEST_CODE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.chuan);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this, SecondActivity.class);
                intent.putExtra("str", "Intent Demo");
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode==REQUEST_CODE)
        {
            if (resultCode==SecondActivity.RESULT_CODE)
            {
                Bundle bundle = data.getExtras();
                String str=bundle.getString("back");
                Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
            }
        }
    }
}
