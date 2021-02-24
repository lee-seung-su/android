package com.example.storyboard

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuizStart.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuizStart : Fragment() {
    lateinit var quizStartButton : Button
    var mainactivity : MainActivity ?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_quiz_start, container, false)
        init(view)
        mainactivity!!.quizInd = 1
        var quizIndText: TextView = mainactivity!!.findViewById(R.id.ind_total_text)
        quizIndText.text = ""
        quizStartButton.setOnClickListener{
            var i = Random.nextInt(2)
            if(i == 0){
                var next = QuizType1()
                var trans = mainactivity!!.supportFragmentManager.beginTransaction()
                trans.add(R.id.frame_layout, next)
                trans.hide(this)
                trans.addToBackStack("quiz_start")

                trans.commit()
            }
            else{
                var next = QuizType2()
                var trans = mainactivity!!.supportFragmentManager.beginTransaction()
                trans.add(R.id.frame_layout, next)
                trans.addToBackStack("quiz_start")
                trans.hide(this)
                var quizIndText: TextView = mainactivity!!.findViewById(R.id.ind_total_text)
                quizIndText.text =
                    "현재 ${mainactivity!!.quizInd }/${mainactivity!!.quizTotal}"
                trans.commit()
            }
        }
        return view
    }
    fun quizValueInit(){
        mainactivity!!.quizFlag = 1
        mainactivity!!.tendencyFlag = 0
        mainactivity!!.quizInd = 0
    }
    fun init(view:View){
        quizStartButton = view.findViewById(R.id.quiz_start_button)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainactivity = context as MainActivity
    }
}