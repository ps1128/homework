package com.example.android.experimentcalculator.presenter;

import com.example.android.experimentcalculator.viewinterface.UnitConversionView;

import java.math.*;
/**
 * Created by peisu on 2017/9/12.
 */

public class UnitConvertPresenter {
    private UnitConversionView view;

    private double a;

    private double Pi = Math.PI;

    private double[] convert = {Math.sin(a) ,Math.cos(a),Math.tan(a),a*a,a*a*a};

    public UnitConvertPresenter(UnitConversionView view) {
        this.view = view;
    }

    public void convert() {
        double value1 = view.getValue1();
        a = view.getValue1();
        double value2 = convert[view.getUnit1()];
        view.setValue2(value2 + "");
    }
}
