package com.example.sof23.struct.proxy;

import java.lang.reflect.Proxy;

/**
 * 代理模式
 * Created by Longwj on 2017/8/21.
 */

public class Client {
    public static void main(String[] args){
        //
        IVpn vpn =new RealVpn();
        //创建动态代理
        ProxyVpn  proxyVpn =new ProxyVpn(vpn);
        //创建LiuWangShu的ClassLoader
        ClassLoader loader=vpn.getClass().getClassLoader();
        //动态创建代理类
        IVpn ProxyVpn = (IVpn) Proxy.newProxyInstance(loader,new Class[]{IVpn.class},proxyVpn);
        ProxyVpn.surfInternet();
    }
}
