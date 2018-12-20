package com.example.sof23.behavior.template;

/**


 *
 * Created by Longwj on 2017/8/29.
 */

public class Client {

    public static void main(String[] args) {

        Game game = new Cricket();
        game.play();
        System.out.println();
        game = new Football();
        game.play();
    }

}
