package com.example.android.experimentcalculator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.android.experimentcalculator.R;
import com.example.android.experimentcalculator.presenter.UnitConvertPresenter;
import com.example.android.experimentcalculator.viewinterface.UnitConversionView;

import static com.example.android.experimentcalculator.R.id.btn_unit_conversion_convert1;
import static com.example.android.experimentcalculator.R.id.btn_unit_conversion_convert2;

// import static com.example.android.experimentcalculator.R.id.btn_unit_conversion_swap;
// import static com.example.android.experimentcalculator.R.id.spinner_unit_conversion_unit2;

/**
 * Created by peisu on 2017/9/12.
 */

public class UnitConversionActivity extends AppCompatActivity implements UnitConversionView {
    //控件数据
    private Button btn_toCalculator;
    private Button btn_toConversion_2;
    private Button btn_unit_conversion_convert_1;
    private Button btn_unit_conversion_convert_2;
    private EditText text_unit_conversion_value1;
    private EditText text_unit_conversion_value11;
    private TextView text_unit_conversion_value2;
    private Spinner spinner_unit_conversion_unit1;
    private Spinner spinner_unit_conversion_unit2;
    //presenter
    private UnitConvertPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_conversion);
        initViews();
        presenter = new UnitConvertPresenter(this);
    }

    /**
     * 初始化所有控件
     */
    @Override
    public void initViews() {
        spinner_unit_conversion_unit1 = (Spinner) findViewById(R.id.spinner_unit_conversion_unit1);

        spinner_unit_conversion_unit2 = (Spinner) findViewById(R.id.spinner_unit_conversion_unit2);

        //交互优化

        text_unit_conversion_value1 = (EditText) findViewById(R.id.text_unit_conversion_value1);

        text_unit_conversion_value11 = (EditText) findViewById(R.id.text_unit_conversion_value11);


        text_unit_conversion_value2 = (TextView) findViewById(R.id.text_unit_conversion_value2);
        text_unit_conversion_value2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_unit_conversion_value2.setText("");
            }
        });


        //跳转按钮监听器
        btn_toCalculator = (Button) findViewById(R.id.btn_conversion_to_calculator);
        btn_toCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(UnitConversionActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_toConversion_2 = (Button) findViewById(R.id.btn_calculator_to_unit_convert_2);
        btn_toConversion_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(UnitConversionActivity.this, UnitConversionActivity_2.class);
                startActivity(intent);
                finish();
            }
        });

        //计算按钮监听器
        btn_unit_conversion_convert_1 = (Button) findViewById(btn_unit_conversion_convert1);
        btn_unit_conversion_convert_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.convert_1();
            }
        });

        btn_unit_conversion_convert_2 = (Button) findViewById(btn_unit_conversion_convert2);
        btn_unit_conversion_convert_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.convert_2();
            }
        });
    }

    @Override
    public double getValue1() {
        double ret = 0;
        try {
            ret = Double.parseDouble(text_unit_conversion_value1.getText() + "");
        } catch (Exception e) {
        }
        return ret;
    }

    @Override
    public String getValue11() {
        String ret = null;
        try {
            ret = text_unit_conversion_value11.getText() + "";
        } catch (Exception e) {
        }
        return ret;
    }

    @Override
    public int getUnit1() {

        return unitToInt(spinner_unit_conversion_unit1.getSelectedItem().toString());
    }

    @Override
    public int getUnit2() {

        return unitToInt(spinner_unit_conversion_unit2.getSelectedItem().toString());
    }


    @Override
    public void setValue2(String value2) {
        text_unit_conversion_value2.setText(value2);
    }

    public int unitToInt(String unit) {
        if (unit.equals("sin")) return 0;
        if (unit.equals("cos")) return 1;
        if (unit.equals("tan")) return 2;
        if (unit.equals("square")) return 3;
        if (unit.equals("cube")) return 4;
        if (unit.equals("ln")) return 5;
        if (unit.equals("lg")) return 6;
        if (unit.equals("ln(x+1)")) return 7;
        if (unit.equals("sqrt")) return 8;
        if (unit.equals("asin")) return 9;
        if (unit.equals("acos")) return 10;
        if (unit.equals("atan")) return 11;
        if (unit.equals("e^x")) return 12;
        if (unit.equals("binary")) return 13;
        if (unit.equals("octal")) return 14;
        if (unit.equals("hexadecimal")) return 15;
        return -1;
    }
}