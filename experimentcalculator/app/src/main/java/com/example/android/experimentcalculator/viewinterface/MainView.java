package com.example.android.experimentcalculator.viewinterface;

/**
 * Created by peisu on 2017/9/12.
 */

public interface MainView extends TopView{
    /**
     * 更新界面
     * @param op
     */
    void update(char op);
    void update(int digit);
    void update(String result);

    /**
     * 删除一位
     */
    void clear();

    /**
     * 设置所有符号输入
     */
    void setOPInputEnable(boolean isEnable);
    /**
     * 设置小数点输入
     */
    void setDotInputEnable(boolean isEnable);

}
