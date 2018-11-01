package com.sicheng.game.snakegame.box2d;

import android.util.Log;
import android.view.View;

import com.sicheng.game.snakegame.R;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import java.util.Random;



/**
 * 世界的实现类
 * Created by kaka on 2017/7/25.
 */
public class JboxImpl {

    private World mWorld; //模拟世界
    private float dt = 1f/60f; //模拟世界的频率
    private int mVelocityIterations = 6; //速度迭代次数
    private int mPosiontIterations = 3; //位置迭代次数
    //世界的长宽
    public int mWidth = 50,mHeight = 100;
    private float mRatio = 20;//坐标映射比例
    //密度大小
    private float mDesity = 0.5f;

    private final Random mRandom = new Random();
    private Body body;

    public JboxImpl(float mDesity) {
        this.mDesity = mDesity;
    }



    /**
     * 开始世界
     */
    public void startWorld(){
        if (mWorld != null) {
            mWorld.step(dt, mVelocityIterations, mPosiontIterations);//dt时间差，mVelocityIterations，速度迭代差
        }
    }

    /**
     * 创建世界
     */
    public void createWorld() {
        if (mWorld == null) {
            mWorld = new World(new Vec2(10.0f, 10.0f));
            updateVertiacalBounds();
            updateHorizontalBounds();
        }
    }

    /**
     * 创建世界的左右边界，这里左右边界是一个静止的刚体
     */
    private void updateHorizontalBounds() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.STATIC; //定义静止的刚体

        PolygonShape box = new PolygonShape(); //定义的形状
        box.setAsBox(1, mHeight); //确定为矩形

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = box;
        fixtureDef.density = mDesity;
        fixtureDef.friction = 0.8f;//摩擦系数
        fixtureDef.restitution = 1.5f; //补偿系数，反弹

        bodyDef.position.set(-20.0f, 0.0f);
        Body rightBody = mWorld.createBody(bodyDef);
        rightBody.createFixture(fixtureDef);

        bodyDef.position.set(66.0f, 0.0f);
        Body leftBody = mWorld.createBody(bodyDef);
        leftBody.createFixture(fixtureDef);
    }
    /**
     * 创建世界的上下边界，这里上下边界是一个静止的刚体
     */
    private void updateVertiacalBounds() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.STATIC; //定义静止的刚体

        PolygonShape box = new PolygonShape(); //定义的形状
        box.setAsBox(mWidth, 1); //确定为矩形

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = box;
        fixtureDef.density = mDesity;
        fixtureDef.friction = 0.8f;//摩擦系数
        fixtureDef.restitution = 2.5f; //补偿系数，反弹


        bodyDef.position.set(0.0f, -2.0f);
        Body topBody = mWorld.createBody(bodyDef); //创建一个真实的上边 body
        topBody.createFixture(fixtureDef);

        bodyDef.position.set(0.0f, 100.0f);
        Body bottomBody = mWorld.createBody(bodyDef); //创建一个真实的下边 body
        bottomBody.createFixture(fixtureDef);



    }

    //创建圆形
    private Shape craeteCircleShape(float radius) {
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(radius);
        return circleShape;
    }

    public void creatBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.setType(BodyType.DYNAMIC);

        bodyDef.position.set(0,0);


        Shape  shape = craeteCircleShape(20);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.setShape(shape);
        fixtureDef.friction = 0.8f;//摩擦系数
        fixtureDef.density = mDesity;
        fixtureDef.restitution = 0.5f;//补偿系数

        body = mWorld.createBody(bodyDef);
        body.createFixture(fixtureDef);

//        body.setLinearVelocity(new Vec2(mRandom.nextFloat(), mRandom.nextFloat()));

    }
    //view坐标映射为物理的坐标
    private float switchPositionToBody(float viewPosition) {
        return viewPosition / mRatio;
    }

    //物理的坐标映射为iew坐标映射
    private float switchPositionToView(float bodyPosition) {
        return bodyPosition * mRatio;
    }


    /**
     * 将形状画到屏幕上的屏幕x坐标

     * @return
     */
    public float getViewX() {
        return switchPositionToView(body.getPosition().x) - 0;
    }
    /**
     * 将形状画到屏幕上的屏幕y坐标
     * @return
     */
    public float getViewY() {
        return switchPositionToView(body.getPosition().y ) - 0;
    }

    /**
     * 获取刚体的旋转弧度
     * @param view
     * @return
     */
    public float getViewRotaion(View view) {
        Body body = (Body) view.getTag(R.id.dn_view_body_tag);
        if (body != null) {
            float angle = body.getAngle();
            return  (angle / 3.14f * 180f) % 360;
        }
        return 0;
    }

    public void applyLinearImpulse(float x, float y, View view) {
        Body body = (Body) view.getTag(R.id.dn_view_body_tag);
        Vec2 impluse = new Vec2(x,y);
        body.applyLinearImpulse(impluse, body.getPosition(), true); //给body做线性运动 true 运动完之后停止
    }

    //
    public void createShape(){

        //https://blog.csdn.net/foupwang/article/details/79616957
        //圆,都是实心
        CircleShape circle = new CircleShape();
        circle.m_p.set(2.0f, 3.0f);
        circle.m_radius = 0.5f;

        //多边形
        //Box2D的多边形是实心的凸(Convex)多边形。在多边形内部任意选择两点，作一线段，如果所有的线段跟多边形的边都不相交，这个多边形就是凸多边形。多边形是实心的，而不是空心的。一个多边形必须有3个或以上的顶点。
        //多边形的顶点以逆时针（counter clockwise winding，CCW）的顺序存储。我们必须很小心，逆时针是相对于右手坐标系统来说的，这坐标系下，Z轴指向平面外面。有可能相对于你的屏幕，就变成顺时针了，这取决于你自己的坐标系统是怎么规定的。
        // This defines a triangle in CCW order.
        Vec2 [] vertices = new Vec2[3];
        vertices[0].set(0.0f, 0.0f);
        vertices[1].set(1.0f, 0.0f);
        vertices[2].set(0.0f, 1.0f);
        int count = 3;

        PolygonShape polygon = new PolygonShape();
        polygon.set(vertices, count);

        //多边形有一些方便的函数来创建box
        //void SetAsBox(float32 hx, float32 hy);
        //void SetAsBox(float32 hx, float32 hy, const b2Vec2& center, float32 angle);

        //多边形从b2Shape中继承了半径。通过半径，在多边形的周围创建了一个保护层(skin)。堆叠的情况下，此保护层让多边形之间保持稍微分开。这使得可以在核心多边形上执行连续碰撞。
        //多边形保护层通过保持多边形的分离来防止隧穿效应。这会导致形状之间有小空隙。你的显示可以比多边形大些，来隐藏这些空隙。

    }

}
