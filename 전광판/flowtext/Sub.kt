package com.example.flowtext

import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text
import java.lang.Thread.sleep

class Sub : Activity() {
    lateinit var text2:TextView
    lateinit var changeButton : Button
    var value=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_sub)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)


        init()
        text2.text = intent.getStringExtra("from")
        text2.isSelected=true
        //sleep(1000)
        //text2.setTextColor(Color.parseColor("#FF0000"))
        changeButton.setOnClickListener{
            if(value % 6 == 0){
                text2.setTextColor(Color.RED)
            }
            else if(value %6 ==1){
                text2.setTextColor(Color.DKGRAY)
            }
            else if(value % 6 == 2){
                text2.setTextColor(Color.YELLOW)
            }
            else if(value %6 ==3){
                text2.setTextColor(Color.GREEN)
            }
            else if(value %6 ==4){
                text2.setTextColor(Color.BLUE)
            }
            else{
                text2.setTextColor(Color.WHITE)
            }
            value++
        }


    }

    fun init(){
        text2 = findViewById(R.id.text2)
        changeButton = findViewById(R.id.change_btn)
    }
}