package com.example.android.testfour;

import android.content.Context;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import static android.R.attr.data;
import static android.R.attr.maxLength;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String filename = "information";

        final EditText in = (EditText) findViewById(R.id.input);

        final File file = new File(getFilesDir(),filename);

        Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    FileOutputStream outputStream;
                    outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                    outputStream.write(in.getText().toString().getBytes());
                    outputStream.close();
//                    Toast.makeText(MainActivity.this,in.getText().toString(),Toast.LENGTH_LONG).show();
                    Toast.makeText(MainActivity.this, "input information into "+ filename + " successfully", Toast.LENGTH_SHORT).show();
                } catch(Exception e) {
                    Toast.makeText(MainActivity.this, "failed to input " + filename, Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
        Button btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(deleteFile(filename)) {
                    Toast.makeText(MainActivity.this, "delete file "+ filename + " successfully", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "failed to deletefile " + filename, Toast.LENGTH_SHORT).show();
                }
            }
        });

        final TextView showtext = (TextView)findViewById(R.id.show);

        Button btn3 = (Button)findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    InputStream in = null;

                    FileInputStream inputStream = openFileInput(filename);

                    in = new BufferedInputStream(inputStream);

                    int c;
                    StringBuilder stringBuilder = new StringBuilder("");
                    try {
                        while ((c = in.read()) != -1) {
                            stringBuilder.append((char) c);
                        }
                        String str = stringBuilder.toString();
                        showtext.setText(str);
//                        Toast.makeText(MainActivity.this,stringBuilder.toString(),Toast.LENGTH_LONG).show();
                        Toast.makeText(MainActivity.this, "output information into " + filename + " successfully", Toast.LENGTH_SHORT).show();
                    }
                    finally {
                        if (in != null)
                            in.close();
                    }
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "failed to output " + filename, Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
        });


    }
}
