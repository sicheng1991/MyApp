package com.example.sof23.behavior.state;

/**
 * ״̬ģʽ
 *


 * Created by Longwj on 2017/8/30.
 */

public class Client {

    public static void main(String[] args) {
        Context context = new Context();

        StartState startState = new StartState();
        startState.doAction(context);

        System.out.println(context.getState().toString());

        StopState stopState = new StopState();
        stopState.doAction(context);

        System.out.println(context.getState().toString());
    }
}
