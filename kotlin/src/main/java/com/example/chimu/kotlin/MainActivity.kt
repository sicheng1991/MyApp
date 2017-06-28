package com.example.chimu.kotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        println(getName(2))
//        println(sum(3, 5))
//        println("isString" + isString(1));
//        println(isNull());

        //函数式编程FP
//        val numbers = listOf(1,2,3)
//        print(numbers.filter(::isOdd))//有问题

        //lambda 表达式
        println(fsum0(1, 1))
        println(fsum0.invoke(1,1))

        println(fsum1(1, 1).invoke())
        println(fsum2(1, 1))
        println(1.fsum3(1))
    }
    private fun getName(id:Int): String{
        return "迟暮"
    }
    fun sum(a: Int, b: Int): Int { // kotlin中的返回值类型必须明确指定
        println("sum of $a and $b is ${a + b}")
        return a + b
    }
    fun isString(a:Any):Boolean{
        var isS  :Boolean
        if(a is String) isS = true else isS = false
        return isS
    }

    fun isNull(){
        val s: String? = null
        println(s?.length)
    }
    fun isOdd(x:Int) = x % 2 != 0

    //    Lambda 表达式
    val fsum0 = { x: Int, y: Int ->  x + y  }
    val fsum1 = { x: Int, y: Int -> { x + y } }
    val fsum2 = fun(x: Int, y: Int): Int = x + y
    val fsum3 = fun Int.(other: Int): Int = this + other
}
