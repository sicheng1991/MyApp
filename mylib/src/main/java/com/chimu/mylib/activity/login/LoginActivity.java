package com.chimu.mylib.activity.login;

import com.chimu.mylib.R;
import com.chimu.mylib.base.BaseActivity;

/**
 * Created by Administrator on 2017/12/19 0019.
 */

public class LoginActivity extends BaseActivity implements LoginContract.LoginView{
    private LoginContract.LoginPresenter loginPresenter;

    @Override
    public int contentView() {
        return R.layout.activity_login;
    }

    @Override
    public void  init() {
        loginPresenter = new LoginPresenterImp(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void showProgress(String s) {

    }

    @Override
    public void hintProgress() {

    }

    @Override
    public void showToast(String s) {

    }

    @Override
    public void finishView() {

    }

    @Override
    public void login(String name, String pwd) {

    }
}
