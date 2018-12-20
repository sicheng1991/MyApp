package com.example.sof23.struct.proxy;

import java.lang.reflect.Proxy;

/**

 * Created by Longwj on 2017/8/21.
 */

public class Client {
    public static void main(String[] args){
        //
        IVpn vpn =new RealVpn();
        ProxyVpn  proxyVpn =new ProxyVpn(vpn);
        //LiuWangShu发范德萨ClassLoader
        ClassLoader loader=vpn.getClass().getClassLoader();
        IVpn ProxyVpn = (IVpn) Proxy.newProxyInstance(loader,new Class[]{IVpn.class},proxyVpn);
        ProxyVpn.surfInternet();
    }
}
