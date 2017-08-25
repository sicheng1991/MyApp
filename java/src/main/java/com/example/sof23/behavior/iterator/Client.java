package com.example.sof23.behavior.iterator;

import java.util.ArrayList;

/**
 * 迭代器模式
 * 迭代器模式（Iterator Pattern）是 Java 和 .Net 编程环境中非常常用的设计模式。
 * 这种模式用于顺序访问集合对象的元素，不需要知道集合对象的底层表示。
 *
 * ArrayList.iterator()
 *
 * Created by Longwj on 2017/8/24.
 */

public class Client {
    public static void main(String[] args){
        NameRepository namesRepository = new NameRepository();

        for(Iterator iter = namesRepository.getIterator(); iter.hasNext();){
            String name = (String)iter.next();
            System.out.println("Name : " + name);
        }
    }
}
