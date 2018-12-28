package com.chimu.kotlin

/**
 * Created by yangzteL on 2018/12/28 0028.
 */
data class KotlinBean(var id: Int,
                      var name: String){
    fun print1(){
        print(id)
        print(name)
    }
}