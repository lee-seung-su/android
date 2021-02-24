package org.techtown.sports

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.get
import org.w3c.dom.Text

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddHabit.newInstance] factory method to
 * create an instance of this fragment.
 */
class HabitName : Fragment(), View.OnClickListener {
    lateinit var nameQuestion: TextView
    lateinit var nameAnswer: EditText
    lateinit var nextButton: Button
    lateinit var backButton: Button
    lateinit var countTitle: TextView
    lateinit var countQuestion: TextView
    lateinit var countAnswer: EditText
    lateinit var spinner: Spinner
    lateinit var stopwatchCheck : CheckBox
    var item: MutableList<String> = mutableListOf<String>("--단위를 선택하세요--", "회", "개", "분", "시간")
    var mainActivity: MainActivity? = null
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_habit_name, container, false)
        init(view)
        listen()

        return view
    }

    fun init(view: View) {
        nameQuestion = view.findViewById(R.id.habit_name_question)
        nameAnswer = view.findViewById(R.id.habit_name_answer)
        nextButton = view.findViewById(R.id.habit_name_next_btn)
        backButton = view.findViewById(R.id.habit_name_back_btn)
        countTitle = view.findViewById(R.id.habit_count_title)
        countQuestion = view.findViewById(R.id.habit_count_question)
        countAnswer = view.findViewById(R.id.habit_count_answer)
        spinner = view.findViewById(R.id.habit_count_unit_spinner)
        spinner.adapter = ArrayAdapter(mainActivity as Context, R.layout.support_simple_spinner_dropdown_item, item)
        countAnswer.setText("10")
        spinner.setSelection(1)
        stopwatchCheck = view.findViewById(R.id.enable_stopwatch_checkBox)

    }

    fun listen() {
        nextButton.setOnClickListener(this)
        backButton.setOnClickListener(this)
        stopwatchCheck.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        var id = v?.id
        when (id) {
            R.id.habit_name_next_btn -> {
                if(nameAnswer.text.toString().equals("") || countAnswer.text.toString().equals("")) {
                    if (nameAnswer.text.toString().equals("")) {
                        mainActivity!!.makeToast(mainActivity as Context, "습관 이름을 입력하세요!!")
                    }
                    else if(countAnswer.text.toString().equals("")){
                        mainActivity!!.makeToast(mainActivity as Context, "횟수를 입력하세요!!")
                    }
                }
                else {
                    MySharedPreferences.setHabitTitle(mainActivity as Context, nameAnswer.text.toString())
                    MySharedPreferences.setHabitCount(mainActivity as Context, "${countAnswer.text.toString()}${spinner.selectedItem.toString()}")
                    MySharedPreferences.setHabitStopWatch(mainActivity as Context, "${stopwatchCheck.isChecked.toString()}")
                    Log.d("@#@#@#@#@@@@@@","${stopwatchCheck.isChecked.toString()}")
                    var next = HabitDeadLine()
                    var trans = mainActivity!!.supportFragmentManager.beginTransaction()
                    trans.addToBackStack("AddHabit")
                    trans.hide(this)
                    trans.replace(R.id.frame_layout, next)
                    trans.commit()
                }

            }
            R.id.habit_name_back_btn -> {
                mainActivity!!.onBackPressed()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
}