package com.example.viewpagerfragment

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fraga.newInstance] factory method to
 * create an instance of this fragment.
 */
class fraga() : Fragment() {
    var mainActivity : MainActivity ?=null
    lateinit var clearButton : FloatingActionButton
    lateinit var addButton : FloatingActionButton
    lateinit var deleteButton : FloatingActionButton

    lateinit var totalNum : TextView

    lateinit var kakaoData : MutableList<item_class>

    lateinit var recycler : RecyclerView
    lateinit var adapter : CustomAdapter

    var dataNum = 0
    var flag=0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_fraga, container, false)
        init(view)
        listen()
        refresh()
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
    fun init(view : View){
        kakaoData = makeData()
        clearButton = view.findViewById(R.id.fraga_clear_button)
        addButton = view.findViewById(R.id.fraga_add_button)
        deleteButton = view.findViewById(R.id.fraga_delete_button)

        totalNum = view.findViewById(R.id.fraga_total_num)

        recycler = view.findViewById(R.id.fraga_recycler_view)
        recycler.layoutManager = LinearLayoutManager(activity)
        adapter = CustomAdapter(this)
        Log.d("############3","CustomAdapter()")
        adapter.data = kakaoData
        recycler.adapter = adapter

    }
    fun listen(){
        clearButton.setOnClickListener{
            var alert : AlertDialog.Builder = AlertDialog.Builder(mainActivity)
            alert.setTitle("진짜 다 지워?")
            alert.setNegativeButton("ㅇㅇㅇ",DialogInterface.OnClickListener{
                dialog, which ->  kakaoData.clear()
                refresh()
            })
            alert.setPositiveButton("ㄴㄴ",null)
            alert.show()
        }
        addButton.setOnClickListener{
            dataNum++
            var i = Random.nextInt(4)
            var image : Int
            var name : String
            var content : String
            var draw : Boolean

            if(i %4 == 0){image = R.drawable.peach}
            else if(i%4 == 1){image = R.drawable.duck}
            else if(i%4 == 2){image = R.drawable.rabbit}
            else{image = R.drawable.lion}
            name = "${dataNum}번째사람"
            content = "안녕하세요 ${dataNum}번째 입니다~"
            var data = item_class(image, name, content)
            kakaoData.add(data)
            refresh()
            //Log.d("############3","안녕하세요 ${dataNum}번째 입니다~")
        }
        deleteButton.setOnClickListener{
            if(flag %2 == 0){  //안 눌렸을 때
                for(i in kakaoData){
                    i.draw = true
                }
            }
            else{
                for(i in kakaoData){
                    i.draw = false
                }
            }
            flag++
            refresh()
        }
    }
    fun makeData():MutableList<item_class>{
        var l : MutableList<item_class> = mutableListOf<item_class>()
        for(i in 1..40){
            var image : Int
            if(i %4 == 0){image = R.drawable.duck}
            else if(i % 4 == 1){image = R.drawable.rabbit}
            else if(i % 4 == 2){image = R.drawable.lion}
            else{image = R.drawable.peach}
            var name = "${i}번째사람"
            var content = "안녕하세요 ${i}번째 입니다~"
            var data = item_class(image,name,content)
            l.add(data)
            dataNum++
        }
        return l
    }
    fun refresh(){
        totalNum.text = "현재 ${kakaoData.size}명"
        //Log.d("############3","totalNum.text   현재 ${kakaoData.size}명")
        adapter.notifyDataSetChanged()
    }

}