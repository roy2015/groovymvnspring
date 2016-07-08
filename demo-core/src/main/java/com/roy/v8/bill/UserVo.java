package com.roy.v8.bill;

/**
 * Created by BG244210 on 2016/7/7.
 */
public class UserVo {
    private String name;
    private int age;

    public UserVo() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
