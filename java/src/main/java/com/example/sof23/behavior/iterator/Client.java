package com.example.sof23.behavior.iterator;


/**

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
