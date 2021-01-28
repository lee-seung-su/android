package com.example.viewpagerfragment

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.widget.LinearLayoutCompat

class CustomImage3(context: Context) : LinearLayoutCompat(context) {
    init{
        val view = LayoutInflater.from(context).inflate(R.layout.image3,this, false)
        addView(view)
    }
}