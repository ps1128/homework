package com.example.android.experimentcalculator.presenter;

import android.widget.Toast;

import com.example.android.experimentcalculator.activity.UnitConversionActivity_2;
import com.example.android.experimentcalculator.viewinterface.UnitConversionView;
import com.example.android.experimentcalculator.viewinterface.UnitConversionView_2;

import java.math.*;

import static android.R.attr.data;
import static android.R.attr.value;
import static android.R.attr.x;

import java.lang.Double;
import java.security.UnresolvedPermission;

/**
 * Created by peisu on 2017/9/12.
 */

public class UnitConvertPresenter {
    private UnitConversionView view;
    private UnitConversionView_2 view_2;

    private double[][] conversion = {
            {1.0,0.1526,16.8358,0.1284,0.1141},
            {6.5520,1.0,110.14,0.8397,0.7467},
            {0.0594,0.009079,1.0,0.007624,0.00678},
            {7.8015,1.1909,131.2015,1.0,0.8894},
            {8.7731,1.3389,147.5066,1.1243,1.0}
    };

    public UnitConvertPresenter(UnitConversionView view) {
        this.view = view;
    }
    public UnitConvertPresenter(UnitConversionView_2 view_2) {
        this.view_2 = view_2;
    }


    public void convert_1() {
        double value1 = view.getValue1();
        double value2 = fuction1(view.getUnit1(),value1);
        String str = "" + value2;
        view.setValue2(str + "");
    }
    public void convert_2() {
        String value11 = view.getValue11();
        String values2 = fuction2(view.getUnit2(),value11);
        String str = "" + values2;
        view.setValue2(str + "");
    }

    public void convert_3() {
        double value31 = view_2.getValue111();
        double value32 = value31 * conversion[view_2.getUnit3()][view_2.getUnit4()];
        BigDecimal b = new BigDecimal(value32);
        double f1 = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
        String s = Double.toString(f1);
        String str = "" + s;
        view_2.setValue3(str + "");
    }

    public double fuction1(int n, double s) {
        switch (n) {
            case 0:
                return Math.sin(s);
            case 1:
                return Math.cos(s);
            case 2:
                return Math.tan(s);
            case 3:
                return Math.pow(s, 2);
            case 4:
                return Math.pow(s, 3);
            case 5:
                return Math.log(s);
            case 6:
                return Math.log1p(s);
            case 7:
                return Math.log10(s);
            case 8:
                return Math.sqrt(s);
            case 9:
                return Math.asin(s);
            case 10:
                return Math.acos(s);
            case 11:
                return Math.atan(s);
            case 12:
                return Math.exp(s);
            default:
                return -1;
        }
    }

    public String fuction2(int n,String s) {
        switch (n) {
            case 13: {
                int i = (new Integer(s)).intValue();
                Integer integer = new Integer(i);
                return integer.toBinaryString(i);
            }
            case 14:
            {
                int i = (new Integer(s)).intValue();
                Integer integer = new Integer(i);
                return integer.toOctalString(i);
            }
            case 15:
            {
                int i = (new Integer(s)).intValue();
                Integer integer = new Integer(i);
                return integer.toHexString(i);
            }
            default:
                return null;
        }

    }
}
