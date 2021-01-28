package com.example.storyboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TodayMatch.newInstance] factory method to
 * create an instance of this fragment.
 */
class TodayMatch : Fragment() {
   lateinit var todayMatchImage : ImageView
   lateinit var todayMatchPersonInfo : TextView
   lateinit var todayMatchHomeButton : Button
   var maintActivity : MainActivity ?= null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_today_match, container, false)
        init(view)

        var i = Random.nextInt(3)
        func(i)

        todayMatchHomeButton.setOnClickListener{
            var intent = Intent(maintActivity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }
        return view
    }
    fun init(view : View){
        todayMatchImage = view.findViewById(R.id.today_match_image)
        todayMatchPersonInfo = view.findViewById(R.id.today_match_person_info)
        todayMatchHomeButton = view.findViewById(R.id.today_match_home_button)
    }
    fun func(value : Int){
        if(value == 0){
            todayMatchImage.setImageResource(R.drawable.pikacu)
            todayMatchPersonInfo.text = "좋아하는 것 : 사탕 \n싫어하는것 : 생선 \n혈액형 : b형 \n생일 : 12월 12일"
        }
        else if(value == 1){
            todayMatchImage.setImageResource(R.drawable.gorapaduk)
            todayMatchPersonInfo.text = "좋아하는 것 : 커피 \n싫어하는것 : 더운거 \n혈액형 : ab형 \n생일 : 3월 16일"
        }
        else{
            todayMatchImage.setImageResource(R.drawable.lion)
            todayMatchPersonInfo.text = "좋아하는 것 : 퇴근 \n싫어하는것 : 업무 \n혈액형 : o형 \n생일 : 1월 2일"
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        maintActivity = context as MainActivity
    }
}