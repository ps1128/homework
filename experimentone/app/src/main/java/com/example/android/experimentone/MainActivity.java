package com.example.android.experimentone;

import android.app.Activity;

import android.app.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.experimentone.R;

public class MainActivity extends Activity implements View.OnClickListener {/*导入相应声明alt+enter*/
    Button btn_0;
    Button btn_1;
    Button btn_2;
    Button btn_3;
    Button btn_4;
    Button btn_5;
    Button btn_6;
    Button btn_7;
    Button btn_8;
    Button btn_9;
    Button btn_reduce;
    Button btn_equal;
    Button btn_add;
    Button btn_point;
    Button btn_clear;
    Button btn_del;
    Button btn_divide;
    Button btn_mul;
    Button btn_tra;
    EditText et_input;
    boolean clear_flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_0=(Button)findViewById(R.id.btn_0);
        btn_1=(Button)findViewById(R.id.btn_1);
        btn_2=(Button)findViewById(R.id.btn_2);
        btn_3=(Button)findViewById(R.id.btn_3);
        btn_4=(Button)findViewById(R.id.btn_4);
        btn_5=(Button)findViewById(R.id.btn_5);
        btn_6=(Button)findViewById(R.id.btn_6);
        btn_7=(Button)findViewById(R.id.btn_7);
        btn_8=(Button)findViewById(R.id.btn_8);
        btn_9=(Button)findViewById(R.id.btn_9);
        btn_reduce = (Button)findViewById(R.id.btn_reduce);
        btn_equal = (Button)findViewById(R.id.btn_equal);
        btn_add = (Button)findViewById(R.id.btn_add);
        btn_point = (Button)findViewById(R.id.btn_point);
        btn_clear = (Button)findViewById(R.id.btn_clear);
        btn_del = (Button)findViewById(R.id.btn_del);
        btn_divide = (Button)findViewById(R.id.btn_divide);
        btn_mul = (Button)findViewById(R.id.btn_mul);
        btn_tra = (Button)findViewById(R.id.btn_tra);
        et_input = (EditText) findViewById(R.id.et_input);
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_reduce.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
        btn_mul.setOnClickListener(this);
        btn_tra.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
    }
    /*ctrl+o对父类方法进行重写*/
    @Override
    public void onClick(View v) {
        String str=et_input.getText().toString();/*取出显示的内容*/
        switch (v.getId()){
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_point:
                if(clear_flag){
                    clear_flag=false;
                    str="";
                    et_input.setText("");
                }
                et_input.setText(str+((Button)v).getText());
                break;
            case R.id.btn_reduce:
            case R.id.btn_add:
            case R.id. btn_divide:
            case R.id.btn_mul:
                if(clear_flag){
                    clear_flag=false;
                    str="";
                    et_input.setText("");
                }
                et_input.setText(str+" "+((Button)v).getText()+" ");
                break;
            case R.id.btn_clear:

                et_input.setText("");
                break;
            case R.id.btn_del:
                if(clear_flag){
                    clear_flag=false;
                    str="";
                    et_input.setText("");
                }
                if(str!=null&&!str.equals("")) {
                    et_input.setText(str.substring(0,str.length() - 1));
                }
                break;
            case R.id.btn_equal:
                getResult();
                break;
            case R.id.btn_tra:
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                MainActivity.this.finish();
                break;
        }

    }
    private void getResult(){

        String exp=et_input.getText().toString();
        if(exp.equals(" ")&&exp==null){
            return;
        }
        if(!exp.contains(" ")){
            return;
        }
        if(clear_flag){
            clear_flag=false;
            return;
        }
        clear_flag=true;
        String str1=exp.substring(0,exp.indexOf(" "));/*第一个数字*/
        String str3=exp.substring(exp.indexOf(" ")+3);/*第二个数字*/
        String str2=exp.substring(exp.indexOf(" ")+1,exp.indexOf(" ")+2);/*运算符*/
        double result = 0;
        if(!str1.equals("")&&!str3.equals("")){
            double s1=Double.parseDouble(str1);
            double s3=Double.parseDouble(str3);
            if(str2.equals("＋")){
                result=s1+s3;
            }else  if(str2.equals("－")){
                result=s1-s3;
            }else  if(str2.equals("×")){
                result=s1*s3;
            }else  if(str2.equals("÷")){
                if(s3==0){
                    result=0;
                }else{

                    result=s1/s3;
                }

            }
            if(!str1.contains(".")&&!str3.contains(".")&&!str2.equals("÷")){
                int r=(int)result;
                et_input.setText(r+"");
            }else{
                et_input.setText(result+"");
            }
        }
        else if(str1.equals("")&&!str3.equals("")){

            double s3=Double.parseDouble(str3);
            if(str2.equals("＋")){
                result=0+s3;
            }else  if(str2.equals("－")){
                result=0-s3;
            }else  if(str2.equals("×")){
                result=0;
            }else  if(str2.equals("÷")){

                result=0;


            }
            if(!str3.contains(".")){
                int r=(int)result;
                et_input.setText(r+"");
            }else{
                et_input.setText(result+"");
            }
        }
        else if(!str1.equals("")&&str3.equals("")){
            et_input.setText(exp);
        }else {
            et_input.setText("");
        }
    }
}
/*
删除一行ctrl+x*/