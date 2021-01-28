package com.example.storyboard

import android.content.Context
import android.os.Bundle
import android.text.Selection
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuizType2.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuizType2 : Fragment() {
    lateinit var answerEdit : EditText
    lateinit var enterButton : Button
    var mainActivity : MainActivity ?= null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_quiz_type2, container, false)
        init(view)
        var quizIndText: TextView = mainActivity!!.findViewById(R.id.ind_total_text)
        quizIndText.text = "${mainActivity!!.quizInd}/${mainActivity!!.quizTotal}"
        enterButton.setOnClickListener{
            if(answerEdit.text.toString() !=""){
                if(mainActivity!!.quizInd >= mainActivity!!.quizTotal){
                    var next = QuizResult()
                    var trans = mainActivity!!.supportFragmentManager.beginTransaction()
                    trans.add(R.id.frame_layout,next)
                    trans.hide(this)
                    trans.addToBackStack("quiz${mainActivity!!.quizInd}")
                    trans.commit()
                }
                else {
                    var i = Random.nextInt(2)
                    if (i == 0) {
                        var next = QuizType1()
                        var trans = mainActivity!!.supportFragmentManager.beginTransaction()
                        trans.add(R.id.frame_layout, next)
                        trans.hide(this)
                        trans.addToBackStack("quiz${mainActivity!!.quizInd}")
                        mainActivity!!.quizInd++

                        trans.commit()
                    } else {
                        var next = QuizType2()
                        var trans = mainActivity!!.supportFragmentManager.beginTransaction()
                        trans.add(R.id.frame_layout, next)
                        trans.hide(this)
                        trans.addToBackStack("quiz${mainActivity!!.quizInd}")
                        mainActivity!!.quizInd++

                        trans.commit()
                    }
                }
            }
            else{
                var toast = Toast.makeText(mainActivity, "답을 입력하세요", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER_VERTICAL, 0,0)
                toast.show()
            }
        }
        return view
    }
    fun init(view : View){
        answerEdit = view.findViewById(R.id.answer_text)
        enterButton = view.findViewById(R.id.answer_enter_button)
        //answerEdit.setSelection(0)
        answerEdit.requestFocus()
        Selection.setSelection(answerEdit.text,0)


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
}