package com.example.week1

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import com.example.week1.databinding.ActivityPopupdeleteBinding

class delpopup : AppCompatActivity() {
    private lateinit var viewBinders: ActivityPopupdeleteBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinders = ActivityPopupdeleteBinding.inflate(layoutInflater)
        setContentView(viewBinders.root)
        val display = DisplayMetrics()
        this.windowManager.defaultDisplay.getMetrics(display)
        supportActionBar?.hide()
        var width:Int = display.widthPixels
        var height:Int = display.heightPixels

        window.setLayout((width*.7).toInt(), (height*0.4).toInt())
    }
}