package com.example.storyboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import org.apache.poi.ss.formula.functions.Choose

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuizResult.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuizResult : Fragment(),View.OnClickListener {
    lateinit var samePersonAgainButton : Button
    lateinit var tendencyButton : Button
    lateinit var quizHomeButton : Button
    var mainActivity : MainActivity ?= null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_quiz_result, container, false)
        init(view)
        listen()

        return view
    }
    fun init(view : View){
        samePersonAgainButton = view.findViewById(R.id.quiz_again_button)
        tendencyButton = view.findViewById(R.id.tendency_button)
        quizHomeButton = view.findViewById(R.id.quiz_home_button)
    }
    fun listen(){
        samePersonAgainButton.setOnClickListener(this)
        tendencyButton.setOnClickListener(this)
        quizHomeButton.setOnClickListener(this)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onClick(v: View?) {
        var id = v?.id
        when(id){
            R.id.quiz_again_button->{
                var next = QuizStart()
                var trans = mainActivity!!.supportFragmentManager.beginTransaction()
                trans.hide(this)
                trans.add(R.id.frame_layout, next)
                trans.addToBackStack("quiz Result")
                trans.commit()
            }
            R.id.tendency_button ->{
                var next = CheckTendency()
                var trans = mainActivity!!.supportFragmentManager.beginTransaction()
                trans.hide(this)
                trans.add(R.id.frame_layout,next)
                //trans.add(R.id.frame_layout,next)
                trans.addToBackStack("quiz Result")

                trans.commit()
            }
            R.id.quiz_home_button ->{
                var next : SelectMenu = SelectMenu()
                var trans = mainActivity!!.supportFragmentManager.beginTransaction()
                trans.add(R.id.frame_layout, next)
                trans.addToBackStack("Today Match Result")
                trans.hide(this)
                trans.commit()
                /*
                var intent = Intent(mainActivity, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
                mainActivity!!.finish()

                 */

            }
        }
    }
}