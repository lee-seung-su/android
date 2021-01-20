package com.example.a1313

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var d : MutableList<kakao_item>
    lateinit var recycle : RecyclerView
    lateinit var adapter : customAdapter
    lateinit var addButton : FloatingActionButton
    lateinit  var itemNum : TextView
    lateinit var deleteButton : FloatingActionButton
    lateinit var clearAllButton : FloatingActionButton
    var num=0
    var flag=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        addButton.setOnClickListener {
            num++
            makeData(num)
            adapter.notifyDataSetChanged()
            itemNum.text = "현재 ${d.size}명"
            recycle.scrollToPosition(d.size-1)

        }


        deleteButton.setOnClickListener {
            //Log.d("DFDFDFDF","clicked")
            var del :Button = findViewById(R.id.delete_butt)
            if(flag %2 == 0){ // 눌렸을 때
                for(i in 1..d.size-1){
                    d.get(i).draw_flag = true
                }
            }
            else{
                for(i in 1..d.size-1){
                    d.get(i).draw_flag = false
                }
            }
            flag++
            adapter.notifyDataSetChanged()


        }


        clearAllButton.setOnClickListener{
            var check : AlertDialog.Builder = AlertDialog.Builder(this)
            check.setTitle("진짜 다 지워?")
            check.setNegativeButton("어 다지워",DialogInterface.OnClickListener{
                dialog, which -> d.clear()
                adapter.notifyDataSetChanged()
                itemNum.text = "현재 ${d.size}명"
            })
            check.setPositiveButton("아니 지우지마ㅜ", null)
            check.show()

        }
    }
    fun init(){
        d = loadData()
        recycle = findViewById(R.id.recycle_main)
        adapter = customAdapter(this)
        adapter.data = d
        recycle.adapter = adapter;
        recycle.layoutManager = LinearLayoutManager(this)
        addButton = findViewById(R.id.add_button)
        itemNum =findViewById(R.id.item_num)
        itemNum.text = "현재 ${d.size}명"
        deleteButton = findViewById(R.id.delete_button)
        clearAllButton = findViewById(R.id.clear_all_button)

    }
    fun loadData() : MutableList<kakao_item>{
        val l : MutableList<kakao_item> = mutableListOf()
        for(i in 1..40){
            var image1 : Int
            if(i % 4 == 0){ image1 = R.drawable.lion }
            else if(i%4 == 1){ image1 = R.drawable.peach }
            else if(i%4 == 2){ image1 = R.drawable.tiger }
            else { image1 = R.drawable.rabbit }
            var name1 = "${i}번째 사람"
            var content1 = "안녕하세요 ${i}번째입니다~"

            var data = kakao_item(image1, name1, content1)
            l.add(data)
            num++
        }
        return l
    }
    fun makeData(num : Int){

        var image : Int = Random.nextInt(4)
        var name : String
        var content : String
        if(image % 4 == 0){image = R.drawable.lion}
        else if(image % 4 == 1){image = R.drawable.peach}
        else if(image % 4 == 2){image = R.drawable.tiger}
        else{image = R.drawable.rabbit}
        name = "${num}번째 사람"
        content = "안녕하세요 ${num}번째입니다~"

        var data = kakao_item(image, name, content)
        d.add(data)

    }


}