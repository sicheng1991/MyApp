package com.chimu.kotlin.util

/**
 * Created by Longwj on 2017/9/5.
 */



////最简单版
//fun main(args:Array<String>){
//    println("hello world")
//}


//面向对象
class KotlinTest(val name:String){
    fun greet(){
        println("hello,$name")
    }
}
fun main(args: Array<String>) {
//    KotlinTest("sicheng").greet()          // 创建一个对象不用 new 关键字
    val a = arrayOf(1, 2, 3)
    //[0,2,4]
    val b = Array(3, { i -> (i * 2) })
}

// NULL处理
//类型后面加?表示可为空
var age: String? = "23"
//抛出空指针异常
val ages = age!!.toInt()
//不做处理返回 null
val ages1 = age?.toInt()
//age为空返回-1
val ages2 = age?.toInt() ?: -1
