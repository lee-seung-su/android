package com.example.viewpagerfragment

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.viewpager.widget.PagerAdapter

class CustomImage4(context: Context) : LinearLayoutCompat(context) {
    init {
        val view = LayoutInflater.from(context).inflate(R.layout.image4, this, false)
        addView(view)
    }
}