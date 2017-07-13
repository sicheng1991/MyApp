package com.example.chimu.myormlite;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by longwj on 2017/3/6.
 */
@DatabaseTable(tableName = "Test.db")
class TestBean {
    @DatabaseField(columnName = "age")
    private int age;
    @DatabaseField(columnName = "name")
    private String name;
    @DatabaseField(columnName = "sex")
    private boolean sex;
    @DatabaseField(columnName = "high")
    private String high;

    public TestBean(int age, String name, boolean sex) {
        this.age = age;
        this.name = name;
        this.sex = sex;
    }
    public TestBean(int age, String name, boolean sex,String high) {
        this.age = age;
        this.name = name;
        this.sex = sex;
        this.high = high;
    }
    public TestBean(){}

    @Override
    public String toString() {
        return "TestBean{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", high='" + high + '\'' +
                '}';
    }
}
