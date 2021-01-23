package com.example.viewpagerfragment

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class FragmentAdapter(fm: FragmentManager, behavior: Int) : FragmentPagerAdapter(fm, behavior) {
    var fragmentList = listOf<Fragment>()
    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getItem(position: Int): Fragment {

        if(position %4 == 0){
            return fraga()
        }
        else if(position %4 == 1){
            return fragb()
        }
        else if(position %4 == 2){
            return fragc()
        }
        else{return fragd()}
        //return fragmentList.get(temp)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var temp = position % 4
        return when (temp){
            0->"카카오"
            1->"인스타"
            2->"네이버"
            else->"전화"
        }
    }





}
