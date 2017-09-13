package com.example.android.experimentcalculator.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.android.experimentcalculator.R;
import com.example.android.experimentcalculator.presenter.UnitConvertPresenter;
import com.example.android.experimentcalculator.viewinterface.UnitConversionView;

// import static com.example.android.experimentcalculator.R.id.btn_unit_conversion_swap;
// import static com.example.android.experimentcalculator.R.id.spinner_unit_conversion_unit2;

/**
 * Created by peisu on 2017/9/12.
 */

public class UnitConversionActivity extends AppCompatActivity implements UnitConversionView {
    //控件数据
    private Button btn_toCalculator;
    private Button btn_unit_conversion_PI;
    private Button btn_unit_conversion_convert;
    private TextView text_unit_conversion_value1;
    private TextView text_unit_conversion_value2;
    private Spinner spinner_unit_conversion_unit1;

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

        //交互优化

        text_unit_conversion_value1 = (TextView) findViewById(R.id.text_unit_conversion_value1);
        text_unit_conversion_value1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_unit_conversion_value1.setText("");
            }
        });

        text_unit_conversion_value2 = (TextView) findViewById(R.id.text_unit_conversion_value2);
        text_unit_conversion_value2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_unit_conversion_value2.setText("");
            }
        });

        //PI按钮监听器
        btn_unit_conversion_PI = (Button) findViewById(R.id.btn_unit_conversion_pi);
        btn_unit_conversion_PI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text_unit_conversion_value1.setText("" + Math.PI);
            }
        });

        //跳转按钮监听器
        btn_toCalculator = (Button) findViewById(R.id.btn_conversion_to_calculator);
        btn_toCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //计算按钮监听器
        btn_unit_conversion_convert = (Button) findViewById(R.id.btn_unit_conversion_convert);
        btn_unit_conversion_convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.convert();
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
    public int getUnit1() {

        return unitToInt(spinner_unit_conversion_unit1.getSelectedItem().toString());
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
        return -1;
    }
}