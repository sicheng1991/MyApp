package com.example.sof23.behavior.command;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Longwj on 2017/8/28.
 */

public class Broker {
    private List<Order> orderList = new ArrayList<Order>();

    public void takeOrder(Order order){
        orderList.add(order);
    }

    public void placeOrders(){
        for (Order order : orderList) {
            order.execute();
        }
        orderList.clear();
    }
}
