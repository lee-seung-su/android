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
import org.w3c.dom.Text

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HabitAlarm.newInstance] factory method to
 * create an instance of this fragment.
 */
class HabitAlarm() : Fragment(), View.OnClickListener {
    lateinit var title: TextView
    lateinit var question_hour: TextView
    lateinit var question_count: TextView
    lateinit var answer_hour: TextView
    lateinit var answer_count: EditText
    lateinit var saveButton: Button
    lateinit var backButton: Button
    lateinit var checkBox: CheckBox
    lateinit var repeatQuestion: TextView
    lateinit var repeatAnswer: EditText
    lateinit var alarmLayout : LinearLayout
    var showCalendar: ShowCalendar? = null
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
        question_hour = view.findViewById(R.id.habit_alarm_question_hour)
        question_count = view.findViewById(R.id.habit_alarm_question_count)
        answer_hour = view.findViewById(R.id.habit_alarm_answer_hour)
        answer_count = view.findViewById(R.id.habit_alarm_answer_count)
        saveButton = view.findViewById(R.id.habit_alarm_save_btn)
        backButton = view.findViewById(R.id.habit_alarm_back_btn)
        checkBox = view.findViewById(R.id.alarm_repeat_checkbox)
        repeatQuestion = view.findViewById(R.id.habit_alarm_repeat_question)
        repeatAnswer = view.findViewById(R.id.habit_alarm_repeat_answer)
        answer_hour.text = "오전 9시 00분"
        answer_count.setText("3")
        repeatAnswer.setText("3")
        alarmLayout = view.findViewById(R.id.alarm_layout)
    }

    fun listen() {
        saveButton.setOnClickListener(this)
        answer_hour.setOnClickListener(this)
        checkBox.setOnClickListener(this)
        backButton.setOnClickListener(this)
        alarmLayout.setOnTouchListener(object : View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                return true
            }

        })
    }

    override fun onClick(v: View?) {
        var id = v?.id
        when (id) {
            R.id.habit_alarm_save_btn -> {
                if(answer_hour.text.toString().equals("") || answer_count.text.toString().equals("") ||
                    (checkBox.isChecked && repeatAnswer.text.toString().equals("")))
                    {
                        if(answer_hour.text.toString().equals("")) {
                            showCalendar!!.makeToast(showCalendar as Context, "알람시간을 입력하세요!!")
                        }
                        else if(answer_count.text.toString().equals("")){
                            showCalendar!!.makeToast(showCalendar as Context, "알람횟수를 입력하세요!!")
                        }
                        else{
                            showCalendar!!.makeToast(showCalendar as Context, "반복일을 입력하세요!!")
                        }

                }
                else {
                    /*MySharedPreferences.setHabitAlarmStartTime(
                        mainActivity as Context,
                        answer_hour.text.toString()
                    )
                    MySharedPreferences.setHabitAlarmRepeatTime(
                        mainActivity as Context,
                        answer_count.text.toString()
                    )
                    MySharedPreferences.setHabitAlarmRepeatDay(
                        mainActivity as Context,
                        repeatAnswer.text.toString()
                    )*/


                    var intent = Intent(showCalendar as Context, ShowCalendar::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    showCalendar!!.finish()
                }
                
            }
            R.id.habit_alarm_answer_hour -> {
                Log.d("###########33", "habit_alarm_answer_hour")
                TimePickerDialog(showCalendar as Context, TimePickerDialog.OnTimeSetListener { view: TimePicker?, hourOfDay: Int, minute: Int ->
                    if (hourOfDay >= 13) {
                        answer_hour.text = "오후 ${hourOfDay.toInt() - 12}시 ${minute}분"
                    } else {
                        answer_hour.text = "오전 ${hourOfDay}시 ${minute}분"
                    }

                }, 9, 0, false).show()
            }
            R.id.alarm_repeat_checkbox -> {
                if(checkBox.isChecked){
                    repeatQuestion.visibility = View.VISIBLE
                    repeatAnswer.visibility = View.VISIBLE
                }
                else{
                    repeatQuestion.visibility = View.INVISIBLE
                    repeatAnswer.visibility = View.INVISIBLE
                }

            }
            R.id.habit_alarm_back_btn -> {
                showCalendar!!.onBackPressed()

            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        showCalendar = context as ShowCalendar

    }

}