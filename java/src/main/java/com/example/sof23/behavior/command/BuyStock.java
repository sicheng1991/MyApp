package com.example.sof23.behavior.command;

/**
 * Created by Longwj on 2017/8/28.
 */

public class BuyStock implements Order {
    private Stock abcStock;

    public BuyStock(Stock abcStock){
        this.abcStock = abcStock;
    }

    public void execute() {
        abcStock.buy();
    }
}
