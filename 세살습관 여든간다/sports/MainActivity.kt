package org.techtown.sports

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.prolificinteractive.materialcalendarview.MaterialCalendarView

class MainActivity : AppCompatActivity() {
    lateinit var calendar : MaterialCalendarView
    lateinit var title : TextView
    var handler = Handler(Looper.getMainLooper())
    var helper = SqliteHelper(this as Context, "habit",1)
    var frequentHabit =arrayOf<String>("물 200ml 먹기","금연하기","자리에서 일어나기","계단으로 걸어다니기","아침 챙겨먹기")

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.frequent_habit->{
                var position : Int = -1
                var alertDialog = AlertDialog.Builder(this).setTitle("자주 쓰는 습관").setSingleChoiceItems(frequentHabit, -1,DialogInterface.OnClickListener{
                    dialog, which -> position = which
                    Log.d("@@@@@!@!@@@!","  ${position}  ${helper.selectHabit().size}")
                })
                    .setPositiveButton("확인",DialogInterface.OnClickListener{
                        dialog, which ->
                        if(position == 0){
                            helper.insertHabit(habit_item("물 200ml 먹기","D-100", "10회",0,"0-0-0",R.drawable.drinking_water,0))
                        }
                        else if(position == 1){
                            helper.insertHabit(habit_item( "금연하기","D-30", "5번",0,"0-0-0",R.drawable.stop_smoking,0))
                        }
                        else if(position == 2){
                            helper.insertHabit(habit_item("자리에서 일어나기","D-100", "12번",0,"0-0-0",R.drawable.standup,0))
                        }
                        else if(position == 3){
                            helper.insertHabit(habit_item("계단으로 걸어다니기","D-66", "5번",0,"0-0-0",R.drawable.climbing_stairs,0))
                        }
                        else if(position == 4){
                            helper.insertHabit(habit_item("아침 챙겨먹기","D-33", "1번",0,"0-0-0",R.drawable.eat_breakfast, 0))
                        }
                        moveNext()
                    })
                    .setNegativeButton("닫기",null)
                alertDialog.show()


                Log.d("@@@@@!@!@@@!","  ${position}  ${helper.selectHabit().size}")
            }

            R.id.menu_clear_habit->{

                var list : MutableList<habit_item> = mutableListOf<habit_item>()
                list = helper.selectHabit()
                for(habit in list){
                    helper.deleteHabit(habit)
                }
                moveNext()

            }
            R.id.to_home->{
                var next = ShowHabit()
                var trans = supportFragmentManager.beginTransaction()
                trans.add(R.id.frame_layout, next)
                trans.commit()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater = menuInflater
        inflater.inflate(R.menu.menu_option,menu)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        moveNext()

    }

    fun moveNext(){
        var next = ShowHabit()
        var trans = this.supportFragmentManager.beginTransaction()
        trans.add(R.id.frame_layout, next)
        trans.commit()
    }

    fun makeToast(context: Context, str:String){
        var toast = Toast.makeText(context,"${str.toString()}", Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER_VERTICAL,0,0)
        toast.show()
        handler.postDelayed(Runnable {
            toast.cancel()
        },300)
    }

}

