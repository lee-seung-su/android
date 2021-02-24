package com.example.storyboard

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CheckTendency.newInstance] factory method to
 * create an instance of this fragment.
 */
class CheckTendency : Fragment(),View.OnClickListener {
    lateinit var fillInMbti : EditText
    lateinit var enterMbtiButton : Button
    lateinit var startMbtiButton : Button
    var mainactivity : MainActivity?= null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_check_tendency, container, false)
        init(view)
        listen()
        return view

    }
    fun init(view : View){
        fillInMbti = view.findViewById(R.id.fill_in_mbti_text)
        enterMbtiButton = view.findViewById(R.id.enter_mbti_button)
        startMbtiButton = view.findViewById(R.id.start_mbti_button)
    }
    fun listen(){
        enterMbtiButton.setOnClickListener(this)
        startMbtiButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        var id = v?.id
        when(id){
            R.id.enter_mbti_button->{

            }
            R.id.start_mbti_button->{
                tendencyValueInit()
                var next : TendencyEINum1 = TendencyEINum1()
                var trans = mainactivity!!.supportFragmentManager.beginTransaction()
                trans.add(R.id.frame_layout, next)
                trans.addToBackStack("CheckTendency start")
                trans.hide(this)
                trans.commit()
            }
        }
    }
    fun tendencyValueInit(){
        mainactivity!!.ei_value=0
        mainactivity!!.jp_value=0
        mainactivity!!.sn_value = 0
        mainactivity!!.tf_value=0
        mainactivity!!.tendencyFlag=1
        mainactivity!!.quizFlag=0
        mainactivity!!.tendencyInd=0
        //소개글flag = 0
        //응원글flag = 0
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainactivity = context as MainActivity
    }
}