package com.example.sof23.struct.proxy;

import java.lang.reflect.Proxy;

/**
 * ����ģʽ
 * Created by Longwj on 2017/8/21.
 */

public class Client {
    public static void main(String[] args){
        //
        IVpn vpn =new RealVpn();
        //������̬����
        ProxyVpn  proxyVpn =new ProxyVpn(vpn);
        //����LiuWangShu��ClassLoader
        ClassLoader loader=vpn.getClass().getClassLoader();
        //��̬����������
        IVpn ProxyVpn = (IVpn) Proxy.newProxyInstance(loader,new Class[]{IVpn.class},proxyVpn);
        ProxyVpn.surfInternet();
    }
}
