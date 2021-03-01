package org.techtown.sports

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.prolificinteractive.materialcalendarview.CalendarDay

class MemoActivity : AppCompatActivity(), View.OnClickListener {
    var main = this
    lateinit var memoTitle : TextView
    lateinit var dateText : TextView
    lateinit var memoContents : EditText
    lateinit var cancelButton : Button
    lateinit var saveButton : Button
    lateinit var helper : SqliteHelper
    var habitTitle : String?=null
    var date : String?=null
    var memoDate : CalendarDay?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memo)
        init()
        listen()


    }
    fun init(){
        memoTitle = findViewById(R.id.memo_title)
        dateText = findViewById(R.id.memo_date)
        memoContents = findViewById(R.id.memo_contents)
        cancelButton = findViewById(R.id.memo_cancel)
        saveButton = findViewById(R.id.memo_save)
        helper = SqliteHelper(this,"habit",1)
        habitTitle = intent.getStringExtra("habitTitle")
        date = intent.getStringExtra("date")
        Log.d("!@!@#!@#!@#","!!!! ${date.toString()}  ${habitTitle}")
        var year = dateToYear(date!!)
        var month = dateToMonth(date!!)
        var day = dateToDay(date!!)
        memoDate = CalendarDay(year,month,day)
        dateText.text="${memoDate!!.year}-${memoDate!!.month+1}-${memoDate!!.day}"

        var tempHabit = helper.selectHabitByTitleAndDate(habitTitle!!, "${memoDate!!.year}-${memoDate!!.month}-${memoDate!!.day}")
        if (tempHabit == null) {
            memoContents.setText("")
        } else {
            memoContents.setText("${tempHabit.memoContents.toString()}")
        }
        memoContents.requestFocus()
    }
    fun listen(){
        cancelButton.setOnClickListener(this)
        saveButton.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        var id = v?.id
        when(id){
            R.id.memo_cancel->{
                var intent = Intent(main,ShowCalendar::class.java)
                onBackPressed()
            }
            R.id.memo_save->{
                if (memoContents.text.toString()=="") {
                    //nothing
                    var temp = helper.selectHabitByTitleAndDate(habitTitle!!, "${memoDate!!.year}-${memoDate!!.month}-${memoDate!!.day}")
                    if(temp != null){  //내용 지웠으면
                        helper.deleteOneHabit(temp)
                    }
                } else {
                    var temp = helper.selectHabitByTitleAndDate(habitTitle!!, "${memoDate!!.year}-${memoDate!!.month}-${memoDate!!.day}")
                    Log.d("!@#!@#!@#!@#", "before     ${helper.selectHabit().size}")
                    if (temp == null) {
                        Log.d("!@#!@#!@#", "if~~~~")
                        var anotherTemp = helper.selectUnitHabitByTitle(habitTitle!!)
                        Log.d("@@@@##@#@#@", "${anotherTemp!!.title}")
                        anotherTemp.calendar_date = "${memoDate!!.year}-${memoDate!!.month}-${memoDate!!.day}"
                        anotherTemp!!.has_memo = 1
                        anotherTemp!!.memoContents = memoContents.text.toString()
                        Log.d("!@#!@#!@#!@#", "2222before     ${anotherTemp}")
                        helper.insertHabit(anotherTemp)
                        Log.d("!@#!@#!@#!@#", "2222after     ${helper.selectHabit().size}")

                    } else {
                        Log.d("!@#!@#!@#", "else~~~~")
                        temp.has_memo = 1
                        temp.memoContents = memoContents.text.toString()
                        helper.updateHabit(temp)

                    }
                }
                Log.d("!@#!@#!@#!@#", "after   ${helper.selectHabit().size}")
                var intent = Intent(main,ShowCalendar::class.java)
                intent.putExtra("itemTitle",habitTitle)
                finish()
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
        }
    }
    fun dateToYear(date : String):Int{
        var res = ""
        var ind = 0
        for(i in 0..date.length-1){
            if(date.get(i).equals('-')){
                res = date.substring(0,i)
                ind = i
                break
            }
        }
        return Integer.parseInt(res)
    }
    fun dateToMonth(date : String):Int{
        var res = ""
        var flag = 0

        for(i in 0..date.length-1){
            if(date.get(i).equals('-') && flag != 0){
                res = date.substring(flag,i)
                break
            }
            else if(date.get(i).equals('-')){
                flag = i+1
            }
        }
        return Integer.parseInt(res)
    }
    fun dateToDay(date : String):Int{
        var res = ""
        var count = 0
        for(i in 0..date.length-1){
            if(date.get(i).equals('-')){
                count++
            }
            if(count==2){
                res = date.substring(i+1, date.length)
                break
            }
        }
        return Integer.parseInt(res)
    }
}