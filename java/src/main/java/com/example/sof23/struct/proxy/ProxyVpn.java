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
        System.out.println("呵呵");
        Object result =  method.invoke( proxied, objects);

        System.out.println("0000");
        return result;
    }
}
