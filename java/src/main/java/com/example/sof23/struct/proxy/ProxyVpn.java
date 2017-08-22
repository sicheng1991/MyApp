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
        //��ת������Ŀ�����֮ǰ������ִ��һЩ���ܴ���
        System.out.println("��ʼ����");
        //ת������Ŀ�����ķ���
        Object result =  method.invoke( proxied, objects);

        System.out.println("����ɹ���");
        //��ת������Ŀ�����֮�󣬿���ִ��һЩ���ܴ���
        return result;
    }
}
