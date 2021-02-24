package com.example.storyboard

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SelectMenu.newInstance] factory method to
 * create an instance of this fragment.
 */
class SelectMenu : Fragment(), View.OnClickListener {
    lateinit var introButton : Button
    lateinit var quizButton : Button
    lateinit var tendencyButton : Button
    lateinit var fightingMessageButton : Button
    lateinit var checkSimilarityButton : Button
    var mainactivity : MainActivity ?= null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_select_menu, container, false)
        init(view)
        listen()

        return view
    }
    fun init(view : View){
        introButton = view.findViewById(R.id.intro_button)
        quizButton = view.findViewById(R.id.quiz_button)
        tendencyButton = view.findViewById(R.id.tendency)
        fightingMessageButton = view.findViewById(R.id.fighting_message_button)
        checkSimilarityButton = view.findViewById(R.id.check_similarity_button)

    }
    fun listen(){
        introButton.setOnClickListener(this)
        quizButton.setOnClickListener(this)
        tendencyButton.setOnClickListener(this)
        fightingMessageButton.setOnClickListener(this)
        checkSimilarityButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        var id = v?.id
        when(id){
            R.id.intro_button ->{
                var next : Intro = Intro()
                var trans = mainactivity!!.supportFragmentManager.beginTransaction()
                trans.add(R.id.frame_layout, next)
                trans.addToBackStack("select")
                trans.hide(this)
                trans.commit()
            }
            R.id.quiz_button ->{
                var next : QuizStart = QuizStart()
                var trans = mainactivity!!.supportFragmentManager.beginTransaction()
                trans.add(R.id.frame_layout, next)
                trans.addToBackStack("select")
                mainactivity!!.quizFlag = 1
                mainactivity!!.tendencyFlag = 0
                trans.hide(this)
                trans.commit()
            }
            R.id.tendency ->{
                var next : CheckTendency = CheckTendency()
                var trans = mainactivity!!.supportFragmentManager.beginTransaction()
                trans.add(R.id.frame_layout, next)
                trans.addToBackStack("select")
                mainactivity!!.quizFlag = 0
                mainactivity!!.tendencyFlag = 1
                trans.hide(this)
                trans.commit()
            }
            R.id.fighting_message_button ->{
                var next : TodayMatch = TodayMatch()
                var trans = mainactivity!!.supportFragmentManager.beginTransaction()
                trans.add(R.id.frame_layout, next)
                trans.addToBackStack("select")
                trans.hide(this)
                trans.commit()
            }
            R.id.check_similarity_button ->{
                var next : CheckTendency = CheckTendency()
                //var next : CheckTendency = CheckTendency()
                var trans = mainactivity!!.supportFragmentManager.beginTransaction()
                trans.add(R.id.frame_layout, next)
                trans.addToBackStack("select")
                trans.hide(this)
                trans.commit()
            }

        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainactivity = context as MainActivity
    }
}