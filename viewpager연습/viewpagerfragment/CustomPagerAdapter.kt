package com.example.viewpagerfragment

import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter

class CustomPagerAdapter : PagerAdapter() {
    var frags = listOf<Fragment>()
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val frag = frags.get(position)
        container.addView(frag.view)
        return frag

    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return frags.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as View
    }

}
