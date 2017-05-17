package com.example.chimu.mygreendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by longwenjiang on 2017/2/24.
 * Greendao实体类
 */

@Entity
public class User {
    @Id         //通过注解来实现的，3之前是用的反射原理    标志：主键
    private Long id;    //用包装类
    @Property(nameInDb = "USERNAME")
    private String userName;
    @Property(nameInDb = "SEX")
    private String sex;
    @Property(nameInDb = "AGE")
    @NotNull
    private int age;
    @Property(nameInDb = "HIGH")
    private  double high;
    @Generated(hash = 1220885718)
    public User(Long id, String userName, String sex, int age, double high) {
        this.id = id;
        this.userName = userName;
        this.sex = sex;
        this.age = age;
        this.high = high;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", high=" + high +
                '}';
    }

    public double getHigh() {
        return this.high;
    }
    public void setHigh(double high) {
        this.high = high;
    }
}
