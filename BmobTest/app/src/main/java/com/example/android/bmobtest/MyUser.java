package com.example.android.bmobtest;

import cn.bmob.v3.BmobUser;

/**
 * Created by peisu on 2017/9/13.
 */

public class MyUser extends BmobUser {
    private String name;


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
