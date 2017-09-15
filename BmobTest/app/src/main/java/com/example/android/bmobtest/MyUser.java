package com.example.android.bmobtest;

import cn.bmob.v3.BmobUser;

/**
 * Created by peisu on 2017/9/13.
 */

public class MyUser extends BmobUser {
    private Boolean sex;
    private String name;
    private Integer age;
    private String address;

    public boolean getSex() {
        return this.sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }
}
