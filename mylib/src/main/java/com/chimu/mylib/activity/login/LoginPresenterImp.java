package com.chimu.mylib.activity.login;

import com.chimu.mylib.base.BusBean;

/**
 * Created by Administrator on 2017/12/19 0019.
 */

public class LoginPresenterImp implements LoginContract.LoginPresenter {
    private LoginContract.LoginView loginView;
    private LoginContract.LoginModel loginModel;

    public LoginPresenterImp (LoginContract.LoginView view){
        this.loginView = view;
        loginModel = new LoginModelImp(this);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void login(String name, String pwd) {

    }

    @Override
    public void transData(BusBean bean) {

    }
}
