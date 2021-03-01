package org.techtown.sports

import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import java.sql.SQLTimeoutException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HabitNoDisturbTime.newInstance] factory method to
 * create an instance of this fragment.
 */
class HabitNoDisturbTime(title: String) : Fragment(), View.OnClickListener {
    lateinit var helper : SqliteHelper
    var habitTitle = title
    lateinit var title: TextView
    lateinit var question_from: TextView
    lateinit var question_to: TextView
    lateinit var answer_from: TextView
    lateinit var answer_to: TextView
    lateinit var saveButton: Button
    lateinit var backButton: Button
    lateinit var noDisturbLayout : LinearLayout

    var mainActivity: ShowCalendar? = null
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_habit_no_disturb_time, container, false)
        init(view)
        listen()

        return view
    }

    fun init(view: View) {
        title = view.findViewById(R.id.habit_no_disturb_title)
        question_from = view.findViewById(R.id.habit_no_disturb_from_question)
        question_to = view.findViewById(R.id.habit_no_disturb_to_question)
        answer_from = view.findViewById(R.id.habit_no_disturb_from_answer)
        answer_to = view.findViewById(R.id.habit_no_disturb_to_answer)
        saveButton = view.findViewById(R.id.habit_no_disturb_save_btn)
        backButton = view.findViewById(R.id.habit_no_disturb_back_btn)
        answer_from.text = "오후 11시 00분"
        answer_to.text = "오전 8시 00분"
        noDisturbLayout = view.findViewById(R.id.no_disturb_layout)
    }

    fun listen() {
        saveButton.setOnClickListener(this)
        answer_from.setOnClickListener(this)
        answer_to.setOnClickListener(this)
        backButton.setOnClickListener(this)
        noDisturbLayout.setOnTouchListener(object : View.OnTouchListener{
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                return true
            }
        })
    }

    override fun onClick(v: View?) {
        var id = v?.id
        when (id) {
            R.id.habit_no_disturb_save_btn -> {
                if(answer_from.text.toString().equals("") || answer_to.text.toString().equals("")){
                    mainActivity!!.makeToast(mainActivity as Context, "방해금지시간을 입력하세요!!")
                }
                else {
                    var tempHabit = helper.selectUnitHabitByTitle(habitTitle)
                    tempHabit!!.noDisturbStartTime = answer_from.text.toString()
                    tempHabit!!.noDisturbEndTime = answer_to.text.toString()
                    helper.insertHabit(tempHabit)

                    var intent = Intent(mainActivity as Context, ShowCalendar::class.java)
                    intent.putExtra("itemTitle",habitTitle)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    mainActivity!!.finish()
                }

            }
            R.id.habit_no_disturb_from_answer -> {
                TimePickerDialog(mainActivity as Context, TimePickerDialog.OnTimeSetListener { view: TimePicker?, hourOfDay: Int, minute: Int ->
                    if (hourOfDay >= 13) {
                        answer_from.text = "오후 ${hourOfDay.toInt() - 12}시 ${minute}분"
                    } else {
                        answer_from.text = "오전 ${hourOfDay.toInt()}시 ${minute}분"
                    }

                }, 23, 0, false).show()
            }
            R.id.habit_no_disturb_to_answer -> {
                TimePickerDialog(mainActivity as Context, TimePickerDialog.OnTimeSetListener { view: TimePicker?, hourOfDay: Int, minute: Int ->
                    if (hourOfDay >= 13) {
                        answer_to.text = "오후 ${hourOfDay.toInt() - 12}시 ${minute}분"
                    } else {
                        answer_to.text = "오전 ${hourOfDay.toInt()}시 ${minute}분"
                    }

                }, 8, 0, false).show()
            }
            R.id.habit_no_disturb_back_btn -> {
                mainActivity!!.onBackPressed()
            }

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as ShowCalendar
        helper = SqliteHelper(mainActivity as Context, "habit",1)
    }
}