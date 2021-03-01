package org.techtown.sports

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class AnotherMain : AppCompatActivity(), View.OnClickListener {
    lateinit var backButton : ImageView
    lateinit var title : TextView
    var handler = Handler(Looper.getMainLooper())
    var helper = SqliteHelper(this,"habit",1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_another_main)
        init()
        listen()


    }
    fun init(){
        backButton = findViewById(R.id.back_button)
        title = findViewById(R.id.another_title)
        var temp = intent.getStringExtra("title")
        title.text = "${temp.toString()}"
        if(title.text.equals("습관 추가")){
            move(1)
        }
        else if(title.text.equals("자주 쓰는 습관")){
            move(2)
        }
        else if(title.text.equals("스톱 워치")){
            move(3)
        }
        else if(title.text.equals("습관 수정")){
            move(4)
        }
    }
    fun move(value : Int){
        when(value){
            1->{  //습관 추가
                Log.d("@##@#@#","111")
                var next = HabitName("add")
                var trans = supportFragmentManager.beginTransaction()
                //trans.addToBackStack("AnotherMain")
                trans.add(R.id.frame_layout3,next)
                trans.commit()

            }
            2->{  //자주 쓰는 습관
                Log.d("@##@#@#","222")
                var next = CommonHabit()
                var trans = supportFragmentManager.beginTransaction()
                //trans.addToBackStack("AnotherMain")
                trans.add(R.id.frame_layout3,next)
                trans.commit()
            }
            3->{  //스톱워치
                Log.d("@##@#@#","333")
                var next = StopWatch("fromAnotherMain")
                var trans = supportFragmentManager.beginTransaction()
                //trans.addToBackStack("AnotherMain")
                trans.add(R.id.frame_layout3,next)
                trans.commit()
            }
            4->{
                var habitTitle = intent.getStringExtra("habit_title")
                var tempHabit = helper.selectUnitHabitByTitle(habitTitle!!)
                var next = HabitName("update",tempHabit)

                var trans = supportFragmentManager.beginTransaction()
                //trans.addToBackStack("AnotherMain")
                trans.add(R.id.frame_layout3,next)
                trans.commit()
            }
        }
    }
    fun listen(){
        backButton.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        var id = v?.id
        when(id){
            R.id.back_button->{
                onBackPressed()
            }
        }
    }
    fun makeToast(context: Context, str:String){
        var toast = Toast.makeText(context,"${str.toString()}", Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER_VERTICAL,0,0)
        toast.show()
        handler.postDelayed(Runnable {
            toast.cancel()
        },500)
    }
    fun upKeyboard(context : Context, editText: EditText){
        var IMM = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        IMM.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }
    fun downKeyboard(context : Context, editText : EditText){
        var IMM = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        IMM.hideSoftInputFromWindow(editText.windowToken,0)
    }
}