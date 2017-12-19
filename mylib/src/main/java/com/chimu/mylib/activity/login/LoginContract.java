package com.chimu.mylib.activity.login;

import com.chimu.mylib.base.BaseModel;
import com.chimu.mylib.base.BasePresenter;
import com.chimu.mylib.base.BaseView;

/**
 * Created by Administrator on 2017/12/19 0019.
 */

public class LoginContract {

    interface LoginView extends BaseView{

        /**
         * 登录操作
         * @param name
         * @param pwd
         */
        void login(String name,String pwd);
    }
    interface LoginPresenter extends BasePresenter {
        void login(String name,String pwd);
    }
    interface LoginModel extends BaseModel{
        void login(String name,String pwd);
    }
}
