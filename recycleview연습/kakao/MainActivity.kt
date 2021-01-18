package com.example.kakao

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var recycle : RecyclerView
    lateinit var adapter : CustomAdapter
    lateinit var item_list : MutableList<item>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        item_list = make_data()
        init()


    }
    fun init(){

        recycle = findViewById(R.id.recycle_1)
        adapter = CustomAdapter()
        adapter.data = item_list
        recycle.adapter = adapter
        recycle.layoutManager = LinearLayoutManager(this)

    }
    fun make_data() : MutableList<item>{
        var item_list : MutableList<item> = mutableListOf<item>()
        for(i in 1..100){
            var image :Int
            if(i%4 == 0){
                image = R.drawable.peach
            }
            else if(i%4 == 1){
                image = R.drawable.duck
            }
            else if(i%4==2){
                image = R.drawable.lion
            }
            else{image = R.drawable.rabbit}
            var name  = "${i}번"
            var content = "반갑습니다 ${i}번 입니다"
            var data = item(image, name, content)
            item_list.add(data)
        }
        return item_list
    }
}