package com.example.android.experimentcalculator.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.example.android.experimentcalculator.activity.MainActivity;
import com.example.android.experimentcalculator.model.Calculator;
import com.example.android.experimentcalculator.model.Result;
import com.example.android.experimentcalculator.viewinterface.MainView;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.text.NumberFormat;

/**
 * Created by peisu on 2017/9/12.
 */

public class MainPresenter {
    public static final String TAG = "MainPresenter";

    private MainView mainView;
    private Handler handler;
    private Calculator calculator;
    private Result ret;

    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
        handler = new Handler();

        calculator = new Calculator((Context) mainView);




//        //增加js接口方便调用js计算
//        calculator.addJavascriptInterface(new JSInterface(), "calculator");
}

    /**
     * 计算方法，接收表达式并计算
     */

    public static double calculator(String s){

        // 计算内容分割
        List<String> numList = new ArrayList<String>();
        int splitIndex = 0;
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(c == '+'||c == '-'||c=='*'||c=='/'){
                numList.add(s.substring(splitIndex, i));
                numList.add(c+"");
                splitIndex = i+1;
            }
        }
        // 因为使用符号做判断，增加前一位和符号，所以最后一位数字不会在循环里处理
        numList.add(s.substring(splitIndex, s.length()));


        // 先做乘除计算
        List<String> list = new ArrayList<String>();
        Double temp = null; // 用于做乘除计算临时变量
        for(int i=1;i<numList.size();i+=2){ // 这里只循环运算符号
            if("+".equals(numList.get(i))||"-".equals(numList.get(i))){
                if(null != temp){ // 存在临时变量，说明前面进行过乘除计算
                    list.add(temp.toString());
                    temp = null;
                } else {
                    list.add(numList.get(i-1));
                }
                list.add(numList.get(i)); // 把符号加进去
                if(i==numList.size()-2) { // 处理到最后时遇到直接处理

                    list.add(numList.get(i+1));


                }
            }else if("*".equals(numList.get(i))){
                if(null == temp){
                    temp = Double.parseDouble(numList.get(i-1)) * Double.parseDouble(numList.get(i+1));
                }else{
                    temp = temp * Double.parseDouble(numList.get(i+1));
                }
                if(i==numList.size()-2) { // 处理到最后时遇到直接处理
                    list.add(temp.toString());
                    temp = null;
                }
            }else if("/".equals(numList.get(i))){
                if(null == temp){
                    temp = Double.parseDouble(numList.get(i-1)) / Double.parseDouble(numList.get(i+1));
                }else{
                    temp = temp / Double.parseDouble(numList.get(i+1));
                }
                if(i==numList.size()-2) { // 处理到最后时遇到直接处理
                    list.add(temp.toString());
                    temp = null;
                }
            }
        }

        // 再做加减计算
        Double sum = Double.parseDouble(list.get(0)); // 第一位不会在循环里处理
        for(int i=1;i<list.size();i+=2){ // 这里只循环运算符号
            if("+".equals(list.get(i))){
                sum += Integer.parseInt(list.get(i+1));
            }else if("-".equals(list.get(i))){
                sum -= Integer.parseInt(list.get(i+1));
            }
        }

        return sum;
    }
    
    public void calcu(String expression) {
        //如果不合法则显示错误
        if (expression.matches("[^a-zA-Z]*?[a-zA-z]+[^a-zA-Z]*?")) {
            mainView.update("error");
            return;
        }
        String result ;
        Double d = new Double(calculator(expression));
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        result = nf.format(d);
        handler.post(new resultThread(result));
    }
    /**
     * 结果更新线程
     */
    class resultThread implements Runnable {
        String data;

        public resultThread(String data) {
            this.data = data;
        }

        @Override
        public void run() {
            try {
                //如果结果有问题，调用view层显示错误。
                Double.parseDouble(data);
            } catch (Exception e) {
                mainView.update("error");
                return;
            }
            //否则更新结果
            mainView.update(data);
        }
    }
}
