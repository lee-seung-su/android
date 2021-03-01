package org.techtown.sports

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class LoadingMain : AppCompatActivity() {
    var handler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading_main)
        var intent = Intent(this, MainActivity::class.java)

        handler.postDelayed(Runnable{
            finish()
            startActivity(intent)
        },1500)

    }
}