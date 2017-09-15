package com.example.android.experimentcalculator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.experimentcalculator.R;
import com.example.android.experimentcalculator.presenter.UnitConvertPresenter;
import com.example.android.experimentcalculator.viewinterface.UnitConversionView;
import com.example.android.experimentcalculator.viewinterface.UnitConversionView_2;

import static com.example.android.experimentcalculator.R.id.btn_unit_conversion_convert1;
import static com.example.android.experimentcalculator.R.id.btn_unit_conversion_convert3;
import static com.example.android.experimentcalculator.R.id.spinner_unit_conversion_unit1;
import static com.example.android.experimentcalculator.R.id.spinner_unit_conversion_unit2;
import static com.example.android.experimentcalculator.R.id.text_unit_conversion_value1;
import static com.example.android.experimentcalculator.R.id.text_unit_conversion_value11;
import static com.example.android.experimentcalculator.R.id.text_unit_conversion_value2;

/**
 * Created by peisu on 2017/9/14.
 */

public class UnitConversionActivity_2 extends AppCompatActivity implements UnitConversionView_2 {

    private Button btn_toCalculator;
    private Button btn_toConversion;
    private Button btn_unit_conversion_convert_3;
    private EditText text_unit_conversion_value3;
    private TextView text_unit_conversion_value22;
    private Spinner spinner_unit_conversion_unit3;
    private Spinner spinner_unit_conversion_unit4;



    private UnitConvertPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_conversion_2);
        initViews();
        presenter = new UnitConvertPresenter(this);
    }
    /**
     * 初始化所有控件
     */
    @Override
    public void initViews() {

        spinner_unit_conversion_unit3 = (Spinner) findViewById(R.id.spinner_unit_conversion_unit3);


        spinner_unit_conversion_unit4 = (Spinner) findViewById(R.id.spinner_unit_conversion_unit4);

        //交互优化

        text_unit_conversion_value3 = (EditText) findViewById(R.id.text_unit_conversion_value3);


        text_unit_conversion_value22 = (TextView) findViewById(R.id.text_unit_conversion_value22);
        text_unit_conversion_value22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_unit_conversion_value22.setText("");
            }
        });



        //跳转按钮监听器
        btn_toCalculator = (Button) findViewById(R.id.btn_conversion_to_calculator);
        btn_toCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(UnitConversionActivity_2.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_toConversion = (Button) findViewById(R.id.btn_calculator_to_unit_convert);
        btn_toConversion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(UnitConversionActivity_2.this, UnitConversionActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //计算按钮监听器
        btn_unit_conversion_convert_3 = (Button) findViewById(btn_unit_conversion_convert3);
        btn_unit_conversion_convert_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.convert_3();
            }
        });
    }


    @Override
    public double getValue111() {
        double ret = 0;
        try {
            ret = Double.parseDouble(text_unit_conversion_value3.getText() + "");
        } catch (Exception e) {
        }
        return ret;
    }


    @Override
    public int getUnit3() {

        return unitToInt(spinner_unit_conversion_unit3.getSelectedItem().toString());
    }

    @Override
    public int getUnit4() {

        return unitToInt(spinner_unit_conversion_unit4.getSelectedItem().toString());
    }

    @Override
    public void setValue3(String value3) {
        text_unit_conversion_value22.setText(value3);
    }

    public int unitToInt(String unit) {
        if (unit.equals("人民币")) return 0;
        if (unit.equals("美元")) return 1;
        if (unit.equals("日元")) return 2;
        if (unit.equals("欧元")) return 3;
        if (unit.equals("英镑")) return 4;


        return -1;
    }

}
