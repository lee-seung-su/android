package com.example.viewpagerfragment

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.Window
import android.view.WindowManager
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.size
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class MainActivity : AppCompatActivity() {
    lateinit var tab : TabLayout
    lateinit var naver : WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var fragmentList = listOf(fraga(), fragb(), fragc(), fragd())
        val adapter = FragmentAdapter(supportFragmentManager,1)

        adapter.fragmentList = fragmentList
        var viewPager : ViewPager = findViewById(R.id.pager)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                if((position % adapter.fragmentList.size) == 1){

                }
                else if((position % adapter.fragmentList.size) == 2){
                    naver  = findViewById(R.id.fragc_webview)

                    naver.webViewClient = WebViewClient()
                    naver.loadUrl("https://www.naver.com")
                }

                /*
                if(position % adapter.fragmentList.size == 2){
                    var intent = Intent(Intent.ACTION_VIEW)
                    intent.data= Uri.parse("https://www.naver.co.kr")
                    startActivity(intent)
                }
                */



            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })





    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if((keyCode == KeyEvent.KEYCODE_BACK) && naver.canGoBack()){
            naver.goBack()
            return true
        }

        return super.onKeyDown(keyCode, event)
    }

}