package com.chimu.kotlin.activity

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chimu.kotlin.R

/**
 *
 * kotlin实现登录逻辑
 * Created by Longwj on 2017/7/3.
 */
class loginActivity : AppCompatActivity() {
    var userName : EditText? = null
    var userPwd : EditText? = null
    var login : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        userName = findViewById(R.id.et_account)
        userPwd = findViewById(R.id.et_password)
        login = findViewById(R.id.login)
        login!!.setOnClickListener {
            if (userName!!.text.toString() == "123" && userPwd!!.text.toString() == "123") {
                Toast.makeText(this, "login succeed1", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this, "login error", Toast.LENGTH_SHORT).show()
            }
        }
    }

}