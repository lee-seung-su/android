package org.techtown.sports

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.prolificinteractive.materialcalendarview.CalendarDay
import org.w3c.dom.Text
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HabitGoal.newInstance] factory method to
 * create an instance of this fragment.
 */
class HabitDeadLine : Fragment(), View.OnClickListener {
    lateinit var deadLineTitle: TextView
    lateinit var deadLineQuestion: TextView
    lateinit var dDayAnswer: TextView
    lateinit var dateAnswerFrom: TextView
    lateinit var dateAnswerBetween: TextView
    lateinit var dateAnswerTo: TextView
    lateinit var radioGroup: RadioGroup

    lateinit var goadTitle: TextView
    lateinit var goalQuestion: TextView

    lateinit var goalAnswer: EditText
    lateinit var nextButton: Button
    lateinit var backButton: Button
    var mainActivity: MainActivity? = null
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_habit_deadline, container, false)
        init(view)
        listen()
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.deadline_type_d_day -> {
                    dDayAnswer.visibility = View.VISIBLE
                    dateAnswerFrom.visibility = View.GONE
                    dateAnswerBetween.visibility = View.GONE
                    dateAnswerTo.visibility = View.GONE
                }
                R.id.deadline_type_date -> {
                    dDayAnswer.visibility = View.GONE
                    dateAnswerFrom.visibility = View.VISIBLE
                    dateAnswerBetween.visibility = View.VISIBLE
                    dateAnswerTo.visibility = View.VISIBLE
                }
            }

        }

        return view
    }

    fun init(view: View) {

        deadLineTitle = view.findViewById(R.id.habit_deadline_title)
        deadLineQuestion = view.findViewById(R.id.habit_deadline_question)
        dDayAnswer = view.findViewById(R.id.habit_d_day_answer)
        dateAnswerFrom = view.findViewById(R.id.habit_date_answer_from)
        dateAnswerBetween = view.findViewById(R.id.habit_date_answer_between)
        dateAnswerTo = view.findViewById(R.id.habit_date_answer_to)
        radioGroup = view.findViewById(R.id.deadline_radio_group)

        goadTitle = view.findViewById(R.id.habit_goal_title)
        goalQuestion = view.findViewById(R.id.habit_goal_question)
        goalAnswer = view.findViewById(R.id.habit_goal_answer)
        nextButton = view.findViewById(R.id.habit_goal_next_btn)
        backButton = view.findViewById(R.id.habit_goal_back_btn)

        dDayAnswer.text = "D-100"
        dateAnswerFrom.text = "${CalendarDay.today().year}-${CalendarDay.today().month + 1}-${CalendarDay.today().day}"
        dateAnswerTo.text = "${CalendarDay.today().year}-${CalendarDay.today().month + 1}-${CalendarDay.today().day}"
        goalAnswer.setText("80")

    }

    fun listen() {
        nextButton.setOnClickListener(this)
        backButton.setOnClickListener(this)
        dDayAnswer.setOnClickListener(this)
        dateAnswerFrom.setOnClickListener(this)
        dateAnswerTo.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        var id = v?.id
        when (id) {
            R.id.habit_goal_next_btn -> {
                if(dDayAnswer.text.toString().equals("") || dateAnswerFrom.text.toString().equals("") ||
                        dateAnswerTo.text.toString().equals("") || goalAnswer.text.toString().equals("")){
                    if(radioGroup.checkedRadioButtonId == R.id.deadline_type_d_day && dDayAnswer.text.toString().equals("")){
                        mainActivity!!.makeToast(mainActivity as Context, "기한을 입력하세요!!")
                    }
                    else if(dateAnswerFrom.text.toString().equals("")){
                        mainActivity!!.makeToast(mainActivity as Context, "시작기간을 입력하세요!!")
                    }
                    else if(dateAnswerTo.text.toString().equals("")){
                        mainActivity!!.makeToast(mainActivity as Context, "종료기간을 입력하세요!!")
                    }
                    else if(goalAnswer.text.toString().equals("")){
                        mainActivity!!.makeToast(mainActivity as Context,"목표율을 입력하세요!!")
                    }
                }
                else {
                    MySharedPreferences.setHabitDeadLineDDay(
                        mainActivity as Context,
                        dDayAnswer.text.toString()
                    )
                    MySharedPreferences.setHabitDeadLineDate(
                        mainActivity as Context,
                        dateAnswerFrom.text.toString()
                    )
                    MySharedPreferences.setHabitDeadLineDate(
                        mainActivity as Context,
                        dateAnswerTo.text.toString()
                    )
                    MySharedPreferences.setHabitGoal(
                        mainActivity as Context,
                        goalAnswer.text.toString()
                    )

                    var next = HabitAlternate()
                    var trans = mainActivity!!.supportFragmentManager.beginTransaction()
                    trans.addToBackStack("HabitName")
                    trans.hide(this)
                    trans.add(R.id.frame_layout, next)
                    trans.commit()
                }

            }
            R.id.habit_goal_back_btn -> {
                mainActivity!!.onBackPressed()
            }
            R.id.habit_d_day_answer -> {
                var listener = DatePickerDialog.OnDateSetListener { view: DatePicker?, year: Int, month: Int, day: Int ->
                    var res_day = changeToDDay(year.toInt(), month.toInt(), day.toInt())
                    if (res_day < 0) {
                        var toast = Toast.makeText(mainActivity as Context, "과거로 목표설정 불가!!", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
                        toast.show()
                        mainActivity!!.handler.postDelayed(Runnable {
                            toast.cancel()
                        }, 1000)
                    } else {
                        dDayAnswer.text = "D-$res_day"
                    }

                }
                var datePickerDialog = DatePickerDialog(mainActivity as Context, listener, CalendarDay.today().year, CalendarDay.today().month, CalendarDay.today().day)
                datePickerDialog.show()

            }
            R.id.habit_date_answer_from -> {
                var listener = DatePickerDialog.OnDateSetListener { view: DatePicker?, year: Int, month: Int, day: Int ->
                    dateAnswerFrom.text = "$year-${month.toInt() + 1}-$day"
                }
                var datePickerDialog = DatePickerDialog(mainActivity as Context, listener, CalendarDay.today().year, CalendarDay.today().month, CalendarDay.today().day)
                datePickerDialog.show()
            }
            R.id.habit_date_answer_to -> {
                var listener = DatePickerDialog.OnDateSetListener { view: DatePicker?, year: Int, month: Int, day: Int ->
                    dateAnswerTo.text = "$year-${month.toInt() + 1}-$day"
                }
                var datePickerDialog = DatePickerDialog(mainActivity as Context, listener, CalendarDay.today().year, CalendarDay.today().month, CalendarDay.today().day)
                datePickerDialog.show()
            }
        }
    }

    fun changeToDDay(year: Int, month: Int, day: Int): Int {
        var ONE_DAY = 24 * 60 * 60 * 1000
        var ddayCalendar = Calendar.getInstance()
        ddayCalendar.set(year, month, day)
        var d_day = ddayCalendar.timeInMillis / ONE_DAY
        var to_day = Calendar.getInstance().timeInMillis / ONE_DAY
        var res = d_day - to_day
        return res.toInt()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
}