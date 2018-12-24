package com.chimu.kotlin.activity

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chimu.kotlin.R
import com.example.chimu.kotlin.bean.PersonBean

class MainActivity : AppCompatActivity() {
    var btn: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn = findViewById(R.id.btn_bbb)

        btn!!.setOnClickListener {
            Toast.makeText(this,"你好",Toast.LENGTH_LONG).show()
            btn!!.text = "改个名字"
        }

//        println(getName(2))
//        println(sum(3, 5))
//        println("isString" + isString(1));
//        println(isNull());

        //函数式编程FP
//        val numbers = listOf(1,2,3)
//        print(numbers.filter(::isOdd))//有问题

//        //lambda 表达式
//        println(fsum0(1, 1))
//        println(fsum0.invoke(1, 1))
//
//        println(fsum1(1, 1).invoke())
//        println(fsum2(1, 1))
//        println(1.fsum3(1))

        val l = mutableListOf(1, 2, 3)
        l.swap(0, 2) // 'swap()' 内部的 'this' 得到 'l' 的值

        //空安全NPE
//        var a: String = "abcd"
//        a = null //编译错误

        var a: String? = "abcd"
        a = null //编译成功
//        不仅如此，为了避免NPE异常，Kotlin做了一件很有趣的事：当你允许属性可空时，Kotlin编译器将不允许你在未经检查的情况下引用它。
        var person: PersonBean? = null
//        person.name = "shinelw" //编译失败
//        person?.name = "shinelw" //编译成功
//        如上面的代码所示，当person对象可为null时，必须强制使用 ?. 来进行null检查。



    }

    fun MutableList<Int>.swap(index1: Int, index2: Int) {
        val tmp = this[index1] // 'this' 对应该列表
        this[index1] = this[index2]
        this[index2] = tmp
    }


    private fun getName(id: Int): String {
        return "迟暮"
    }

    fun sum(a: Int, b: Int): Int { // kotlin中的返回值类型必须明确指定
        println("sum of $a and $b is ${a + b}")
        return a + b
    }

    fun isString(a: Any): Boolean {
        var isS: Boolean
        isS = a is String
        return isS
    }

    fun isNull() {
        val s: String? = null
        println(s?.length)
    }

    fun isOdd(x: Int) = x % 2 != 0

    //    Lambda 表达式
    val fsum0 = { x: Int, y: Int -> x + y }
    val fsum1 = { x: Int, y: Int -> { x + y } }
    val fsum2 = fun(x: Int, y: Int): Int = x + y
    val fsum3 = fun Int.(other: Int): Int = this + other
}
