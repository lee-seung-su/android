package org.techtown.sports

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Vibrator
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.view.marginTop
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ShowHabit.newInstance] factory method to
 * create an instance of this fragment.
 */

class ShowHabit : Fragment(),View.OnClickListener {
    lateinit var addButton : Button
    lateinit var habitRecyclerView : RecyclerView
    lateinit var data : MutableList<habit_item>
    lateinit var adapter : HabitRecyclerViewAdapter
    var mainActivity : MainActivity?= null
    lateinit var eFAB1 : ExtendedFloatingActionButton
    lateinit var eFAB2 : ExtendedFloatingActionButton
    lateinit var eFAB3 : ExtendedFloatingActionButton
    lateinit var FAB : FloatingActionButton
    lateinit var title : TextView
    var fabFlag = 0

    lateinit var helper : SqliteHelper
    var frequentHabit =arrayOf<String>("물 200ml 먹기","금연하기","자리에서 일어나기","계단으로 걸어다니기","아침 챙겨먹기")
    var swipeCallback = object : SwipeToCallback(){
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            /*var vibrator = mainActivity!!.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibrator.vibrate(100)
            val from = viewHolder.adapterPosition
            val to = target.adapterPosition
            var temp = data.get(from)
            data.removeAt(from)
            data.add(to,temp)

            adapter.notifyItemMoved(from,to)
            return true*/
            return true
        }


    }
    var itemTouchHelper = ItemTouchHelper(swipeCallback)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_show_habit, container, false)
        init(view)
        listen()
        //makeCustomHabit()
        changeData()

        return view
    }

    fun init(view : View){
        addButton = view.findViewById(R.id.show_habit_add_button)
        habitRecyclerView = view.findViewById(R.id.habit_recycler_view)
        data = MakeHabit()
        adapter = HabitRecyclerViewAdapter(this)

        adapter.data = data
        adapter.helper = helper
        habitRecyclerView.adapter = adapter
        habitRecyclerView.layoutManager = LinearLayoutManager(mainActivity)
        itemTouchHelper.attachToRecyclerView(habitRecyclerView)

        title = view.findViewById(R.id.show_habit_title)
        eFAB1 = view.findViewById(R.id.efab1)
        eFAB2 = view.findViewById(R.id.efab2)
        eFAB3 = view.findViewById(R.id.efab3)
        FAB = view.findViewById(R.id.fab)
    }
    fun MakeHabit(): MutableList<habit_item>{

        var items : MutableList<habit_item> = mutableListOf<habit_item>()
        return items
    }

    fun listen(){
        addButton.setOnClickListener(this)
        eFAB1.setOnClickListener(this)
        eFAB2.setOnClickListener(this)
        eFAB3.setOnClickListener(this)
        FAB.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        var id = v?.id

        when(id){
            R.id.show_habit_add_button->{
                var intent = Intent(mainActivity, AnotherMain::class.java)
                intent.putExtra("title","습관 추가")
                startActivity(intent)

            }
            R.id.efab1->{  //자주 쓰는 습관

                /*var position : Int = -1
                var alertDialog = AlertDialog.Builder(mainActivity as Context).setTitle("자주 쓰는 습관").setSingleChoiceItems(frequentHabit, -1,
                    DialogInterface.OnClickListener{
                        dialog, which -> position = which
                })
                    .setPositiveButton("확인", DialogInterface.OnClickListener{
                            dialog, which ->
                        if(position == 0){
                            helper.insertHabit(habit_item("물 200ml 먹기","D-100", "10회",0,"0-0-0",R.drawable.drinking_water,0, 0,"",
                                    "오전 9시 00분", 3, 1, "오후 11시 00분","오전 8시 00분"))
                        }
                        else if(position == 1){
                            helper.insertHabit(habit_item( "금연하기","D-30", "5번",0,"0-0-0",R.drawable.stop_smoking,0,0,"",
                                    "오전 9시 00분", 3, 1, "오후 11시 00분","오전 8시 00분"))
                        }
                        else if(position == 2){
                            helper.insertHabit(habit_item("자리에서 일어나기","D-100", "12번",0,"0-0-0",R.drawable.standup,0,0,"",
                                    "오전 9시 00분", 3, 1, "오후 11시 00분","오전 8시 00분"))
                        }
                        else if(position == 3){
                            helper.insertHabit(habit_item("계단으로 걸어다니기","D-66", "5번",0,"0-0-0",R.drawable.climbing_stairs,0,0,"",
                                    "오전 9시 00분", 3, 1, "오후 11시 00분","오전 8시 00분"))
                        }
                        else if(position == 4){
                            helper.insertHabit(habit_item("아침 챙겨먹기","D-33", "1번",0,"0-0-0",R.drawable.eat_breakfast, 0,0,"",
                                    "오전 9시 00분", 3, 1, "오후 11시 00분","오전 8시 00분"))
                        }

                        var next = ShowHabit()
                        var trans = mainActivity!!.supportFragmentManager.beginTransaction()
                        trans.add(R.id.frame_layout, next)
                        trans.commit()
                    })
                    .setNegativeButton("닫기",null)
                alertDialog.show()*/  //자주쓰는 습관 dialog
                var intent = Intent(mainActivity, AnotherMain::class.java)
                intent.putExtra("title","자주 쓰는 습관")
                startActivity(intent)
            }
            R.id.efab2->{  //습관 추가
                var intent = Intent(mainActivity, AnotherMain::class.java)
                intent.putExtra("title","습관 추가")
                startActivity(intent)
            }
            R.id.efab3 ->{ // 스톱 워치
                var intent = Intent(mainActivity, AnotherMain::class.java)
                intent.putExtra("title","스톱 워치")
                startActivity(intent)
            }
            R.id.fab->{
                if(fabFlag % 2 == 0){
                    fabFlag++
                    eFAB1.visibility = View.VISIBLE
                    eFAB2.visibility = View.VISIBLE
                    eFAB3.visibility = View.VISIBLE
                    FAB.setImageResource(R.drawable.exxx)
                }
                else{
                    fabFlag++
                    eFAB1.visibility = View.INVISIBLE
                    eFAB2.visibility = View.INVISIBLE
                    eFAB3.visibility = View.INVISIBLE
                    FAB.setImageResource(R.drawable.plus)
                }
            }

        }
    }
    fun changeDataOnlyOne(){
        adapter.data.clear()
        adapter.data.addAll(helper.selectHabitDistinct())
        if(adapter.data.size == 0){
            title.visibility = View.VISIBLE
        }
        else{
            title.visibility = View.GONE
        }
        adapter.notifyDataSetChanged()
    }
    fun changeData(){
        adapter.data.clear()
        adapter.data.addAll(helper.selectHabitDistinct())
        if(adapter.data.size == 0){
            title.marginTop
            title.visibility = View.VISIBLE
            addButton.visibility = View.VISIBLE
        }
        else{
            addButton.visibility = View.GONE
            title.visibility = View.GONE
        }
        adapter.notifyDataSetChanged()
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
        helper = SqliteHelper(mainActivity as Context, "habit", 1)
    }

}