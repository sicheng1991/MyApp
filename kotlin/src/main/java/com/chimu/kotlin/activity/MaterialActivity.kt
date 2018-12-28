package com.chimu.kotlin.activity

import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.chimu.kotlin.R
import com.chimu.kotlin.Test
import com.google.android.material.bottomappbar.BottomAppBar





/**
 * Created by yangzteL on 2018/12/24 0024.
 */
class MaterialActivity : BActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_material)
//        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
//                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)

        val bar= findViewById<BottomAppBar>(R.id.bar)


        bar.setOnMenuItemClickListener({ item ->
            Toast.makeText(this, item.itemId, Toast.LENGTH_LONG).show()
            true
        }

        )

        //
        bar.setOnMenuItemClickListener {
            true
        }

//        bar.replaceMenu(R.menu.bottom_app_bar)
        bar.setNavigationOnClickListener {
            // Handle the navigation click by showing a BottomDrawer etc.
            run {
                val test = Test()
                test.main()

            }
        }
    }
}