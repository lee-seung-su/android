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
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.prolificinteractive.materialcalendarview.MaterialCalendarView

class MainActivity : AppCompatActivity(),View.OnClickListener {
    lateinit var title : TextView
    lateinit var settingButton : ImageView
    var handler = Handler(Looper.getMainLooper())
    var helper = SqliteHelper(this as Context, "habit",1)
    var frequentHabit =arrayOf<String>("습관 초기화","홈으로")

    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
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
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(android.R.style.Theme_NoTitleBar)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        listen()

        moveNext()

    }
    fun init(){
        settingButton = findViewById(R.id.setting_button)
    }
    fun listen(){
        settingButton.setOnClickListener(this)
    }
    fun moveNext(){
        var next = ShowHabit()
        var trans = supportFragmentManager.beginTransaction()
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
    fun upKeyboard(context : Context, editText: EditText){
        var IMM = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        IMM.toggleSoftInput(InputMethodManager.SHOW_FORCED,InputMethodManager.HIDE_IMPLICIT_ONLY)
    }
    fun downKeyboard(context : Context, editText : EditText){
        var IMM = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        IMM.hideSoftInputFromWindow(editText.windowToken,0)
    }

    override fun onClick(v: View?) {
        var id = v?.id
        when(id){
            R.id.setting_button->{
                var position = -1
                var alertDialog = AlertDialog.Builder(this as Context).setTitle("").setSingleChoiceItems(frequentHabit, -1,
                        DialogInterface.OnClickListener{
                            dialog, which -> position = which
                        })
                        .setPositiveButton("확인", DialogInterface.OnClickListener{
                            dialog, which ->
                            if(position == 0){
                                var list = helper.selectHabit()
                                for(habit in list){
                                    helper.deleteHabit(habit)
                                }
                                var intent = Intent(this, MainActivity::class.java)
                                finish()
                                startActivity(intent)
                            }
                            else if(position == 1){
                                var intent = Intent(this, MainActivity::class.java)
                                finish()
                                startActivity(intent)
                            }
                        })
                        .setNegativeButton("닫기",null)
                alertDialog.show()
            }

        }
    }

}

