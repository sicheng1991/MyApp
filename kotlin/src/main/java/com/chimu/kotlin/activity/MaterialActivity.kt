package com.chimu.kotlin.activity

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.Window
import androidx.appcompat.widget.Toolbar
import com.chimu.kotlin.R
import com.chimu.kotlin.R.id.bar
import com.google.android.material.bottomappbar.BottomAppBar
import com.chimu.kotlin.R.id.bar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.chimu.kotlin.R.id.bar




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

        val bar: BottomAppBar = findViewById(R.id.bar)

        //
        bar.setOnMenuItemClickListener {
            true
        }

        bar.setNavigationOnClickListener {
            // Handle the navigation click by showing a BottomDrawer etc.
            run {
                Log.i("haa", "123")
                Log.i("haa", "233")



            }
        }
    }
}