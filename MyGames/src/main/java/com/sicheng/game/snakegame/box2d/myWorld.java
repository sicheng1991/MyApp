package com.sicheng.game.snakegame.box2d;

import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;


/**
 * Created by yangzteL on 2018/10/29 0029.
 */

public class myWorld {
    final float RATE = 30;

    private void createWorld(){
        AABB aabb = new AABB();//物理世界范围
        aabb.lowerBound.set(-100,-100);//左上角坐标,物理世界中单位为m
        aabb.upperBound.set(100,100);//右下角坐标
        Vec2 gravity = new Vec2(0,10);//重力向量，可正和负


        World world = new World(gravity);
        BodyDef def = new BodyDef();
        def.position.set(0.0f,-10.0f);
        world.createBody(def);
    }


}
