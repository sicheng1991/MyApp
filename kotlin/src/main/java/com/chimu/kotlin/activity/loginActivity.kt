package com.chimu.kotlin.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.chimu.kotlin.R

/**
 *
 * kotlin实现登录逻辑
 * Created by Longwj on 2017/7/3.
 */
class loginActivity : AppCompatActivity() {
    var userName : EditText? = null;
    var userPwd : EditText? = null;
    var login : TextView? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        userName = findViewById(R.id.et_account) as EditText
        userPwd = findViewById(R.id.et_password) as EditText
        login = findViewById(R.id.login) as TextView

        login!!.setOnClickListener {
            if (userName!!.text.toString() == "123456" && userPwd!!.text.toString() == "abc") {
                Toast.makeText(this, "login succeed1", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

}