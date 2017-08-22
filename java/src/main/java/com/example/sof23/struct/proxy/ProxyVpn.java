package com.example.sof23.struct.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Longwj on 2017/8/21.
 */

public class ProxyVpn implements InvocationHandler {
    private Object proxied;

    public ProxyVpn( Object proxied )
    {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        //在转调具体目标对象之前，可以执行一些功能处理
        System.out.println("开始代理");
        //转调具体目标对象的方法
        Object result =  method.invoke( proxied, objects);

        System.out.println("代理成功了");
        //在转调具体目标对象之后，可以执行一些功能处理
        return result;
    }
}
