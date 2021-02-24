package com.example.storyboard

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TendencyJPNum3.newInstance] factory method to
 * create an instance of this fragment.
 */
class TendencyJPNum3 : Fragment(),View.OnClickListener {
    var mainActivity : MainActivity?=null
    lateinit var question1 : TextView
    lateinit var question2 : TextView
    lateinit var radio1 : RadioButton
    lateinit var radio2 : RadioButton
    lateinit var titleNum : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_tendency_j_p_num3, container, false)
        init(view)
        listen()
        return view
    }
    fun init(view:View){
        titleNum = view.findViewById(R.id.jp_num3_title)
        question1 = view.findViewById(R.id.jp_num3_question1)
        question2 = view.findViewById(R.id.jp_num3_question2)
        radio1 = view.findViewById(R.id.jp_num3_radio1)
        radio2 = view.findViewById(R.id.jp_num3_radio2)
        mainActivity!!.tendencyInd++
        titleNum.text = "${mainActivity!!.tendencyInd}/20"
    }
    fun listen(){
        radio1.setOnClickListener(this)
        radio2.setOnClickListener(this)
        question1.setOnClickListener(this)
        question2.setOnClickListener(this)
    }

    fun goNext(){
        var next : TendencyJPNum4 = TendencyJPNum4()
        var trans = mainActivity!!.supportFragmentManager.beginTransaction()
        trans.add(R.id.frame_layout, next)
        trans.hide(this)
        trans.addToBackStack("TendencyJPNum3")
        trans.commit()
    }
    override fun onClick(v: View?) {
        var id = v?.id
        when(id){
            R.id.jp_num3_radio1->{
                mainActivity!!.tendency_select_top=1
                mainActivity!!.tendency_select_bottom=0
                mainActivity!!.jp_value++
                goNext()
            }

            R.id.jp_num3_radio2->{
                mainActivity!!.tendency_select_top=0
                mainActivity!!.tendency_select_bottom=1
                mainActivity!!.jp_value--
                goNext()

            }
            R.id.jp_num3_question1->{
                radio1.isChecked = true
                radio1.callOnClick()
            }
            R.id.jp_num3_question2->{
                radio2.isChecked = true
                radio2.callOnClick()
            }

        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
}