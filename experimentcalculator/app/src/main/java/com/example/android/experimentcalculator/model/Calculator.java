package com.example.android.experimentcalculator.model;

import android.content.Context;
import android.webkit.WebView;

/**
 * Created by peisu on 2017/9/12.
 */

public class Calculator extends WebView {

    public Calculator(Context context) {
        super(context);
        getSettings().setJavaScriptEnabled(true);
        getSettings().setDomStorageEnabled(true);
    }
}