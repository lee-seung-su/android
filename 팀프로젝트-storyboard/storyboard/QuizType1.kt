package com.example.storyboard

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [QuizType1.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuizType1 : Fragment() {
    lateinit var checkBox1 : CheckBox
    lateinit var checkBox2 : CheckBox
    lateinit var checkBox3 : CheckBox
    lateinit var checkBox4 : CheckBox
    lateinit var nextButton : Button

    var flag1 = false
    var flag2 = false
    var flag3 = false
    var flag4 = false
    var mainActivity : MainActivity ?= null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_quiz_type1, container, false)
        init(view)
        var quizIndText: TextView = mainActivity!!.findViewById(R.id.ind_total_text)
        quizIndText.text = "${mainActivity!!.quizInd}/${mainActivity!!.quizTotal}"
        nextButton.setOnClickListener{
            if(check()){
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
                var toast = Toast.makeText(mainActivity as Context, "값을 선택하세요",Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER_VERTICAL,0,0)
                toast.show()
            }
        }
        return view
    }
    fun init(view:View){
        checkBox1 = view.findViewById(R.id.checkbox_1)
        checkBox2 = view.findViewById(R.id.checkbox_2)
        checkBox3 = view.findViewById(R.id.checkbox_3)
        checkBox4 = view.findViewById(R.id.checkbox_4)
        nextButton = view.findViewById(R.id.quiz_next_button)
        mainActivity!!.quizInd++
    }
    fun check():Boolean{
        flag1 = checkBox1.isChecked()
        flag2 = checkBox2.isChecked()
        flag3 = checkBox3.isChecked()
        flag4 = checkBox4.isChecked()
        return flag1 || flag2 || flag3 || flag4
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
}