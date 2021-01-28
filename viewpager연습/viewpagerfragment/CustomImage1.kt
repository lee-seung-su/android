package com.example.viewpagerfragment

import android.content.Context
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.appcompat.widget.LinearLayoutCompat

class CustomImage1(context: Context) : LinearLayoutCompat(context) {
    init{
        val view = LayoutInflater.from(context).inflate(R.layout.image1,this,false)
        addView(view)
    }
}