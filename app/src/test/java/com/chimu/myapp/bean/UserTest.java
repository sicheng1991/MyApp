package com.chimu.myapp.bean;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

/**
 * Created by Longwj on 2017/11/8.
 */
public class UserTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void login() throws Exception {
        UserManager manager = Mockito.mock(UserManager.class);    //Mock，返回一个对象
        User user = new User();
        user.setmUserManager(manager);

        //传指定值时，返回true
        Mockito.when(manager.verifyPassword("sicheng123456")).thenReturn(true);
        //不管传值，返回true
//        Mockito.when(manager.verifyPassword(anyString())).thenReturn(true);
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                //这里可以获得传给performLogin的参数
                Object[] arguments = invocation.getArguments();

                //callback是第三个参数
                UserManager.NetworkCallback callback = (UserManager.NetworkCallback) arguments[2];

                callback.onFailure(500, "Server error");
                return 500;
            }
        }).when(manager).performLogin(anyString(), anyString(), any(UserManager.NetworkCallback.class));

        user.login("sicheng", "sicheng123456");
//        UserManager userManager = user.getmUserManager();  //<==
        //验证userManager的performLogin()方法得到了调用，参数分别是“sicheng”、“sicheng123456”



//        Mockito.verify(manager).performLogin("sicheng", "sicheng123456");
        //方法调次数
//        Mockito.verify(manager,Mockito.times(1)).performLogin("sicheng", "sicheng123456");
    }
    @Test
    public void verifyPassword(){
//        UserManager manager = Mockito.mock(UserManager.class);    //Mock，返回一个对象
        UserManager manager = Mockito.spy(UserManager.class);    //Mock，返回一个对象
        User user = new User();
        user.setmUserManager(manager);

        boolean  p = user.verifyPassword("1234444444");
        System.out.println(p);
    }

}