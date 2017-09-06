package com.example.android.testtwo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by peisu on 2017/9/6.
 */

public class SecondActivity extends Activity {
    public final static int RESULT_CODE = 1;
    private TextView secondTxt;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String str = bundle.getString("str");
        secondTxt = (TextView)findViewById(R.id.chuantext);
        secondTxt.setText(str);

        btn = (Button)findViewById(R.id.back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("back", "Back Data");
                setResult(RESULT_CODE, intent);
                finish();
            }
        });

    }
}
