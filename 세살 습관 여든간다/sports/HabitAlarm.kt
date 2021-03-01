package org.techtown.sports

import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HabitAlarm.newInstance] factory method to
 * create an instance of this fragment.
 */
class HabitAlarm(title: String) : Fragment(), View.OnClickListener {
    var habitTitle = title
    lateinit var helper : SqliteHelper
    lateinit var title: TextView
    lateinit var toggleButton: ToggleButton
    lateinit var plusButton : TextView
    lateinit var alarmTime : TextView
    lateinit var minusButton : TextView

    lateinit var howMany : EditText
    lateinit var noDTFrom : TextView
    lateinit var noDTTo : TextView

    lateinit var checkBox: CheckBox

    lateinit var radioGroup: RadioGroup
    lateinit var repeatDay : EditText
    lateinit var saveButton : Button
    var hour = 0
    var min = 0
    var mainActivity: ShowCalendar? = null
    lateinit var alarmLayout : ConstraintLayout
    var inputError = false
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_habit_alarm, container, false)
        init(view)
        listen()

        return view
    }

    fun init(view: View) {
        toggleButton = view.findViewById(R.id.alarm_onoff_button)
        plusButton =view.findViewById(R.id.alarm_plus_button)
        alarmTime = view.findViewById(R.id.habit_alarm_time)
        minusButton = view.findViewById(R.id.alarm_minus_button)
        howMany = view.findViewById(R.id.habit_alarm_how_many_answer)
        noDTFrom = view.findViewById(R.id.no_disturb_from)
        noDTTo = view.findViewById(R.id.no_disturb_to)
        checkBox = view.findViewById(R.id.alarm_repeat_checkbox)
        radioGroup = view.findViewById(R.id.alarm_repeat_radio_group)
        repeatDay = view.findViewById(R.id.habit_alarm_repeat_day_answer)
        saveButton = view.findViewById(R.id.habit_alarm_save_btn)
        alarmLayout = view.findViewById(R.id.alarm_layout)
    }

    fun listen() {
        saveButton.setOnClickListener(this)
        toggleButton.setOnClickListener {
            if(toggleButton.isChecked){
                toggleButton.isActivated = true
            }
            else{
                toggleButton.isActivated = false
            }
        }
        alarmTime.setOnClickListener(this)
        plusButton.setOnClickListener(this)
        minusButton.setOnClickListener(this)

        noDTFrom.setOnClickListener(this)
        noDTTo.setOnClickListener(this)

        //mainActivity!!.upKeyboard(mainActivity as Context, howMany)
        alarmLayout.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                return true
            }
        })


    }

    override fun onClick(v: View?) {
        var id = v?.id
        when (id) {
            R.id.habit_alarm_save_btn -> {
                Log.d("@#@@@@@@@@@@@@","toggle   ${toggleButton.isActivated.toString()}")
                if(((toggleButton.isActivated.equals(true)) && (alarmTime.text.toString().equals("") || howMany.text.toString().equals("")))
                        ||((checkBox.isChecked.equals(true) && (repeatDay.text.toString().equals(""))))) {
                    //켜져있고 알람시간 비어있으면
                    if(toggleButton.isActivated.equals(true) && alarmTime.text.toString().equals("")) {
                        mainActivity!!.makeToast(mainActivity as Context, "알림시간을 입력하세요!!")
                    }
                    else if(checkBox.isChecked.equals(true) && repeatDay.text.toString().equals("")){
                        mainActivity!!.makeToast(mainActivity as Context, "반복 일자를 입력하세요!!")
                    }
                    else if(toggleButton.isActivated.equals(true) && howMany.text.toString().equals("")){
                        mainActivity!!.makeToast(mainActivity as Context, "반복 횟수를 입력하세요!!")
                    }
                }
                else {
                    Log.d("@#@@@@@@@@@@@@","else~")
                    var tempHabit = helper.selectUnitHabitByTitle(habitTitle)
                    tempHabit!!.alarmStartTime = alarmTime.text.toString()
                    if(toggleButton.isActivated.equals(false)){
                        tempHabit!!.alarmRepeat = 0
                    }
                    else {
                        inputError = false
                        for(i in 0..howMany.text.length-1){
                            if(!Character.isDigit(howMany.text.get(i))){
                                inputError = true
                                break
                            }
                        }
                        if(inputError==false) {
                            tempHabit!!.alarmRepeat = Integer.parseInt(howMany.text.toString())
                        }
                        else{
                            mainActivity!!.makeToast(mainActivity as Context,"횟수는 숫자만 입력하세요!!")
                        }
                    }
                    if(inputError == false) {
                        if (checkBox.isChecked == true) {
                            tempHabit!!.alarmRepeatDay = Integer.parseInt(repeatDay.text.toString())
                        } else {
                            tempHabit!!.alarmRepeatDay = 0
                        }
                        helper.insertHabit(tempHabit)

                        var intent = Intent(mainActivity as Context, ShowCalendar::class.java)
                        intent.putExtra("itemTitle", habitTitle)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                        mainActivity!!.finish()
                    }
                }
                
            }
            R.id.habit_alarm_time->{
                hour = changeTimeToHour(alarmTime.text.toString())
                min = changeTimeToMin(alarmTime.text.toString())
                TimePickerDialog(mainActivity as Context, TimePickerDialog.OnTimeSetListener { view: TimePicker?, hourOfDay: Int, minute: Int ->
                    Log.d("@#@#!@#!@#!@#","${hourOfDay.toInt()}  ${minute.toInt()}")
                    if(hourOfDay >= 12){
                        if(hourOfDay==12){
                            alarmTime.text = "오후 ${hourOfDay.toInt()}시 ${minute}분"
                        }
                        else {
                            alarmTime.text = "오후 ${hourOfDay.toInt() - 12}시 ${minute}분"
                        }
                    }
                    else {
                        if(hourOfDay==0){
                            alarmTime.text="오전 12시 ${minute}분"
                        }
                        else {
                            alarmTime.text = "오전 ${hourOfDay.toInt()}시 ${minute}분"
                        }
                    }

                }, hour, min, false).show()
            }
            R.id.no_disturb_from->{
                hour = changeTimeToHour(alarmTime.text.toString())
                min = changeTimeToMin(alarmTime.text.toString())
                TimePickerDialog(mainActivity as Context, TimePickerDialog.OnTimeSetListener { view: TimePicker?, hourOfDay: Int, minute: Int ->

                    if(hourOfDay >= 12){
                        if(hourOfDay==12){
                            noDTFrom.text = "오후 ${hourOfDay.toInt()}시 ${minute}분"
                        }
                        else {
                            noDTFrom.text = "오후 ${hourOfDay.toInt() - 12}시 ${minute}분"
                        }
                    }
                    else {
                        if(hourOfDay==0){
                            noDTFrom.text="오전 12시 ${minute}분"
                        }
                        else {
                            noDTFrom.text = "오전 ${hourOfDay.toInt()}시 ${minute}분"
                        }
                    }
                }, hour, min, false).show()
            }
            R.id.no_disturb_to->{
                hour = changeTimeToHour(alarmTime.text.toString())
                min = changeTimeToMin(alarmTime.text.toString())
                TimePickerDialog(mainActivity as Context, TimePickerDialog.OnTimeSetListener { view: TimePicker?, hourOfDay: Int, minute: Int ->

                    if(hourOfDay >= 12){
                        if(hourOfDay==12){
                            noDTTo.text = "오후 ${hourOfDay.toInt()}시 ${minute}분"
                        }
                        else {
                            noDTTo.text = "오후 ${hourOfDay.toInt() - 12}시 ${minute}분"
                        }
                    }
                    else {
                        if(hourOfDay==0){
                            noDTTo.text="오전 12시 ${minute}분"
                        }
                        else {
                            noDTTo.text = "오전 ${hourOfDay.toInt()}시 ${minute}분"
                        }
                    }

                }, hour, min, false).show()
            }
            R.id.alarm_repeat_checkbox -> {
                if(checkBox.isChecked){
                    radioGroup.visibility = View.VISIBLE
                    repeatDay.visibility = View.VISIBLE
                    //mainActivity!!.upKeyboard(mainActivity as Context, repeatDay)
                }
                else{
                    radioGroup.visibility = View.INVISIBLE
                    repeatDay.visibility = View.INVISIBLE
                    //mainActivity!!.downKeyboard(mainActivity as Context, repeatDay)

                }

            }
            R.id.alarm_plus_button->{
                Log.d("!!!@@##!!@#","plus   ${alarmTime.text.toString()}")
                alarmTime.setText("${changeTime(alarmTime.text.toString(),1)}")
            }
            R.id.alarm_minus_button->{
                Log.d("!!!@@##!!@#","minus  ${alarmTime.text.toString()}")
                alarmTime.setText("${changeTime(alarmTime.text.toString(),0)}")
            }
        }
    }
    fun changeTimeToHour(str: String):Int {
        var hour = 0
        var min = 0
        var flag = str.substring(0,2)
        for(i in 3..str.length-1){
            Log.d("hour~~","~.~   ${str.get(i).toString()}")
            if(str.get(i).equals('시')){
                Log.d("hour~~","~.~   ${i.toInt()}")
                hour = Integer.parseInt(str.substring(3,i))
                min = Integer.parseInt(str.substring(i+2,(str.length-1).toInt()))
                break
            }
        }
        return hour


    }
    fun changeTimeToMin(str: String):Int {
        var hour = 0
        var min = 0
        var flag = str.substring(0,2)
        for(i in 3..str.length-1){
            Log.d("hour~~","~.~   ${str.get(i).toString()}")
            if(str.get(i).equals('시')){
                Log.d("hour~~","~.~   ${i.toInt()}")
                hour = Integer.parseInt(str.substring(3,i))
                min = Integer.parseInt(str.substring(i+2,(str.length-1).toInt()))
                break
            }
        }
        return min


    }
    fun changeTime(str: String, value: Int):String {
        var hour = 0
        var min = 0
        var flag = str.substring(0,2)
        for(i in 3..str.length-1){
            Log.d("hour~~","~.~   ${str.get(i).toString()}")
            if(str.get(i).equals('시')){
                Log.d("hour~~","~.~   ${i.toInt()}")
                hour = Integer.parseInt(str.substring(3,i))
                min = Integer.parseInt(str.substring(i+2,(str.length-1).toInt()))
                break
            }
        }
        Log.d("time","~.~   ${hour.toString()}  ${min.toString()}   ${str.toString()}")

        if(value == 1){//plus
            min++
            if(min == 60){
                min=0
                hour++
            }
            if(flag.equals("오전")){
                if(hour==12 && min==0){
                    flag="오후"
                }
            }
            else{
                if(hour==12 && min==0){
                    flag="오전"
                }
            }
        }
        else if(value == 0){
            if(flag.equals("오전")){
                if(hour==12 && min==0){
                    flag="오후"
                }
            }
            else{
                if(hour==12 && min==0){
                    flag="오전"
                }
            }
            min--
            if(min<0){
                min=59
                hour--
                if(hour<=0){
                    hour=12
                }
            }
        }

        if(hour>12){
            hour-=12
        }
        else if(hour <= 0){
            hour = 12
        }
        return "${flag.toString()} ${hour.toInt()}시 ${min.toInt()}분"

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as ShowCalendar
        helper = SqliteHelper(mainActivity as Context, "habit",1)

    }

}