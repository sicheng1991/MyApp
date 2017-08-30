package com.example.sof23.behavior.state;

/**
 * 状态模式
 *
 * 意图：允许对象在内部状态发生改变时改变它的行为，对象看起来好像修改了它的类。
 主要解决：对象的行为依赖于它的状态（属性），并且可以根据它的状态改变而改变它的相关行为。
 何时使用：代码中包含大量与对象状态有关的条件语句。
 如何解决：将各种具体的状态类抽象出来。

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
