package com.chimu.mylib.activity.login;

import com.chimu.mylib.base.BaseBus;

/**
 * Created by Administrator on 2017/12/19 0019.
 */

public class LoginModelImp implements LoginContract.LoginModel {
    private BaseBus baseBus;

    public LoginModelImp(BaseBus baseBus){
        this.baseBus = baseBus;
    }

    @Override
    public void login(String name, String pwd) {

    }
}
