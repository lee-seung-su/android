package org.techtown.sports

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
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
 * Use the [HabitAlternate.newInstance] factory method to
 * create an instance of this fragment.
 */
class HabitAlternate : Fragment(), View.OnClickListener {
    lateinit var title: TextView
    lateinit var answer: EditText
    lateinit var nextButton: Button
    lateinit var spinner: Spinner
    lateinit var helper : SqliteHelper
    var item: MutableList<String> = mutableListOf<String>("--단위를 선택하세요--", "회", "개", "분", "시간")
    var inputError = false
    var mainActivity: AnotherMain? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_habit_alternate, container, false)
        init(view)
        listen()

        return view
    }

    fun init(view: View) {
        title = view.findViewById(R.id.habit_alternate_title)
        answer = view.findViewById(R.id.habit_alternate_answer)
        nextButton = view.findViewById(R.id.habit_alternate_next_btn)
        spinner = view.findViewById(R.id.habit_alternate_unit_spinner)
        spinner.adapter = ArrayAdapter(
            mainActivity as Context,
            R.layout.support_simple_spinner_dropdown_item,
            item
        )
        answer.setText("5")
        spinner.setSelection(1)

    }

    fun listen() {
        nextButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        var id = v?.id
        when (id) {
            R.id.habit_alternate_next_btn -> {
                if(answer.text.toString().equals("")){
                    mainActivity!!.makeToast(mainActivity as Context, "횟수를 입력하세요!!")
                }
                else {
                    inputError = false
                    for(i in 0..answer.text.length-1){
                        if(!Character.isDigit(answer.text.get(i))){
                            inputError = true
                            break
                        }
                    }
                    if(inputError == false) {
                        MySharedPreferences.setHabitAlternate(
                                mainActivity as Context,
                                "${answer.text.toString()} ${spinner.selectedItem.toString()}"
                        )
                        //mainActivity!!.downKeyboard(mainActivity as Context, answer)
                        makeCustomHabit()

                        var intent = Intent(mainActivity, MainActivity::class.java)
                        mainActivity!!.finish()
                        startActivity(intent)
                    }
                    else{
                        mainActivity!!.makeToast(mainActivity as Context, "횟수는 숫자만 입력하세요!!")
                    }
                }

            }

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as AnotherMain
        helper = SqliteHelper(mainActivity as Context, "habit",1)
    }
    fun makeCustomHabit() {

        var habitTitle = MySharedPreferences.getHabitTitle(mainActivity as Context)
        if(habitTitle.equals("Error")){
            Log.d("########3","makeCustomHabit  if")
            return
        }
        else {
            var habitCount = MySharedPreferences.getHabitCount(mainActivity as Context)
            var habitDeadLineDDay =
                MySharedPreferences.getHabitDeadLineDDay(mainActivity as Context)
            var habitDeadLineDate =
                MySharedPreferences.getHabitDeadLineDate(mainActivity as Context)
            var habitGoal = MySharedPreferences.getHabitGoal(mainActivity as Context)
            var habitAlternate = MySharedPreferences.getHabitAlternate(mainActivity as Context)
            var habitStopWatchCheck = MySharedPreferences.getHabitStopWatch(mainActivity as Context)

            var memoContents = ""
            var stopWatchFlag =0
            if(habitStopWatchCheck.toString().equals("true")){
                stopWatchFlag = 1
            }
            else{
                stopWatchFlag = 0
            }
            Log.d("@#@#@#@#@@@@@@","${habitStopWatchCheck.toString()}  ${stopWatchFlag}")
            var habitImage = MySharedPreferences.getHabitImage(mainActivity as Context)
            helper.insertHabit(
                habit_item( habitTitle, habitDeadLineDDay, habitCount,0,"0-0-0", habitImage.toInt(),stopWatchFlag, 0, memoContents,
                    "오전 9시 00분", 3, 1, "오후 11시 00분","오전 8시 00분")
            )

            MySharedPreferences.clearVariable(mainActivity as Context)
            Log.d("@#@#@#@#@@@@@@","1111   ${habitTitle.toString()}   ${habitStopWatchCheck.toBoolean()}")

        }
    }
}