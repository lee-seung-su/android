package com.example.fragment_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    lateinit var fragmentText : Fragment
    lateinit var fragmentButton : Button
    lateinit var mainFrameLayout: FrameLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //init()
        setFragment()
    }
    fun setFragment(){
        val tt : TestFragment = TestFragment()
        val tt2 : TestFragment2 = TestFragment2()
        val trans = supportFragmentManager.beginTransaction()
        trans.add(R.id.main_frame_layout, tt)
        trans.add(R.id.main_frame_layout2, tt2)
        trans.commit()
    }
    fun init(){
        fragmentButton = findViewById(R.id.fragment_next_button)
        fragmentText = findViewById(R.id.fragment_text1)
        mainFrameLayout = findViewById(R.id.main_frame_layout)
    }
    fun goNext(){
        val next_page = Result()
        val trans = supportFragmentManager.beginTransaction()
        trans.add(R.id.main_frame_layout,next_page)
        trans.addToBackStack("result")
        trans.commit()
    }
    fun goNext2(){
        val next_page = Result()
        val trans = supportFragmentManager.beginTransaction()
        trans.add(R.id.main_frame_layout2,next_page)
        trans.addToBackStack("result2")
        trans.commit()
    }
    fun goBack(){
        onBackPressed()

    }


}