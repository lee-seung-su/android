package com.example.viewpagerfragment

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

class InstagramAdapter : PagerAdapter() {
    var views = listOf<View>()
    override fun getCount(): Int {
        return views.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as View
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = views.get(position)
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}