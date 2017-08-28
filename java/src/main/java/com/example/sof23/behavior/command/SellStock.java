package com.example.sof23.behavior.command;

/**
 * Created by Longwj on 2017/8/28.
 */

public class SellStock implements Order {
    private Stock abcStock;

    public SellStock(Stock abcStock){
        this.abcStock = abcStock;
    }

    public void execute() {
        abcStock.sell();
    }
}
