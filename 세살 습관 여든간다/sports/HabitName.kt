package org.techtown.sports

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
class HabitName(state : String, habit:habit_item? = null) : Fragment(), View.OnClickListener {
    lateinit var nameQuestion: TextView
    lateinit var nameAnswer: EditText
    lateinit var nextButton: Button
    lateinit var updateButton : Button
    lateinit var countTitle: TextView
    lateinit var countAnswer: EditText
    lateinit var spinner: Spinner
    lateinit var stopwatchCheck : CheckBox

    lateinit var helper : SqliteHelper

    lateinit var habitImageRecycler : RecyclerView
    lateinit var adapter : HabitImageRecyclerAdapter
    lateinit var data : MutableList<image_item>
    var inputError = false
    var thisHabit = habit
    var flag = state
    var item: MutableList<String> = mutableListOf<String>("--단위를 선택하세요--", "회", "개", "분", "시간")
    var mainActivity: AnotherMain? = null
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
        //"만들고싶은 습관을 써주세요 (1회 습관)\nex)물 200ml 마시기"
        var sps = SpannableStringBuilder(nameQuestion.text)
        var start = 18
        var end = nameQuestion.length()
        sps.setSpan(ForegroundColorSpan(Color.parseColor("#5C5E5C")),start,end,SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)

        sps.setSpan(RelativeSizeSpan(0.7f),start,end,SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
        nameQuestion.setText(sps)

        nameAnswer = view.findViewById(R.id.habit_name_answer)
        nextButton = view.findViewById(R.id.habit_name_next_btn)
        updateButton = view.findViewById(R.id.habit_update_btn)
        countTitle = view.findViewById(R.id.habit_count_title)
        countAnswer = view.findViewById(R.id.habit_count_answer)
        spinner = view.findViewById(R.id.habit_count_unit_spinner)
        spinner.adapter = ArrayAdapter(mainActivity as Context, R.layout.support_simple_spinner_dropdown_item, item)

        countAnswer.setText("10")
        spinner.setSelection(1)
        stopwatchCheck = view.findViewById(R.id.enable_stopwatch_checkBox)
        nameAnswer.requestFocus()

        //mainActivity!!.upKeyboard(mainActivity as Context, nameAnswer)
        habitImageRecycler = view.findViewById(R.id.habit_image_recycler)
        data = makeImageList()
        adapter = HabitImageRecyclerAdapter()
        adapter.helper = helper
        adapter.data = data
        habitImageRecycler.adapter = adapter
        habitImageRecycler.layoutManager = LinearLayoutManager(mainActivity,LinearLayoutManager.HORIZONTAL,true)
        Log.d("@@#@!!@#","init~~~   ${flag.toString()}")
        if(flag.equals("update")){
            countAnswer.requestFocus()
            nextButton.visibility =View.INVISIBLE
            updateButton.visibility=View.VISIBLE
            nameAnswer.setText(thisHabit!!.title.toString())
            var resCount = ""
            var tempCount = thisHabit!!.count.toString()
            for(i in 0..tempCount.length-1){
                if(!Character.isDigit(tempCount.get(i))){
                    resCount = tempCount.substring(0,i)
                    break
                }
            }
            Log.d("@@#@!!@#","if~~~~   ${resCount.toString()}")
            countAnswer.setText(resCount)

        }
        else if(flag.equals("add")){
            Log.d("@@#@!!@#","else~~~~  ")
            nextButton.visibility = View.VISIBLE
            updateButton.visibility = View.INVISIBLE
        }


    }
    fun makeImageList():MutableList<image_item>{
        var list = mutableListOf<image_item>()
        list.add(image_item("default",R.drawable.default_image))
        list.add(image_item("금연하기",R.drawable.stop_smoking))
        list.add(image_item("아침 먹기",R.drawable.eat_breakfast))
        list.add(image_item("일어나기",R.drawable.standup))
        list.add(image_item("계단 걷기",R.drawable.climbing_stairs))
        list.add(image_item("물 마시기",R.drawable.drinking_water))

        return list
    }
    fun listen() {
        nextButton.setOnClickListener(this)
        updateButton.setOnClickListener(this)
        stopwatchCheck.setOnClickListener(this)
        nameAnswer.setOnClickListener(this)

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
                    inputError = false
                    for(i in 0..countAnswer.text.length-1){
                        if(!Character.isDigit(countAnswer.text.get(i))){
                            inputError = true
                            break
                        }
                    }
                    if(inputError == false) {
                        MySharedPreferences.setHabitTitle(mainActivity as Context, nameAnswer.text.toString())
                        MySharedPreferences.setHabitCount(mainActivity as Context, "${countAnswer.text.toString()}${spinner.selectedItem.toString()}")
                        MySharedPreferences.setHabitStopWatch(mainActivity as Context, "${stopwatchCheck.isChecked.toString()}")
                        //mainActivity!!.downKeyboard(mainActivity as Context,nameAnswer)
                        var next = HabitAlternate()
                        var trans = mainActivity!!.supportFragmentManager.beginTransaction()
                        trans.addToBackStack("HabitName")
                        trans.add(R.id.frame_layout3, next)
                        trans.hide(this)
                        trans.commit()
                    }
                    else{
                        mainActivity!!.makeToast(mainActivity as Context,"횟수는 숫자만 입력하세요!!")
                    }

                }

            }
            R.id.habit_update_btn->{
                if(nameAnswer.text.toString().equals("") || countAnswer.text.toString().equals("")) {
                    if (nameAnswer.text.toString().equals("")) {
                        mainActivity!!.makeToast(mainActivity as Context, "습관 이름을 입력하세요!!")
                    }
                    else if(countAnswer.text.toString().equals("")){
                        mainActivity!!.makeToast(mainActivity as Context, "횟수를 입력하세요!!")
                    }
                }
                else {
                    inputError = false
                    for(i in 0..countAnswer.text.length-1){
                        if(!Character.isDigit(countAnswer.text.get(i))){
                            inputError = true
                            break
                        }
                    }
                    if(inputError == false) {
                        thisHabit!!.count = "${countAnswer.text.toString()}${spinner.selectedItem.toString()}"
                        if(stopwatchCheck.isChecked.equals(true)){
                            thisHabit!!.use_stopwatch = 1
                        }
                        else{
                            thisHabit!!.use_stopwatch = 0
                        }

                        var tempHabit = helper!!.selectUnitHabitByTitle(nameAnswer.text.toString())
                        if(tempHabit == null){
                            mainActivity!!.makeToast(mainActivity as Context,"해당 습관이 없습니다")
                            mainActivity!!.onBackPressed()
                        }
                        else{
                            thisHabit!!.habit_image = MySharedPreferences.getHabitImage(mainActivity as Context)
                            Log.d("!@#!@#!@#!!!!@#","${tempHabit!!.use_stopwatch.toString()}")
                            helper!!.updateHabit(thisHabit!!)
                            var tttt = helper.selectUnitHabitByTitle(tempHabit!!.title.toString())
                            Log.d("!@#!@#!@#!!!!@#","@!@!  ${tttt!!.use_stopwatch.toString()}  ${thisHabit!!.use_stopwatch.toString()}")
                            mainActivity!!.makeToast(mainActivity as Context,"수정 완료!")
                            var intent = Intent(mainActivity, MainActivity::class.java)
                            mainActivity!!.finish()
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            startActivity(intent)
                        }
                    }
                    else{
                        mainActivity!!.makeToast(mainActivity as Context,"횟수는 숫자만 입력하세요!!")
                    }

                }
            }
            R.id.habit_name_answer->{
                Log.d("!@#@!#!@#!@#!","${stopwatchCheck.isChecked}")
            }

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as AnotherMain
        helper = SqliteHelper(mainActivity as Context, "habit",1)
    }

}