package com.chimu.kotlin

/**
 * Created by yangzteL on 2018/12/28 0028.
 */

class Test{
    fun main(){
        //    bean.print() //可能是null，无法编译
        var bean: KotlinBean? = null  //可以通过编译
        print("msggg" + bean.toString())

    }
}

