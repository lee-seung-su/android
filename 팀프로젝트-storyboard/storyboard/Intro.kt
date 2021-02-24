package com.example.storyboard

import android.content.Context
import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Intro.newInstance] factory method to
 * create an instance of this fragment.
 */
class Intro : Fragment(), View.OnClickListener {
    lateinit var introImage : ImageView
    lateinit var introDetailText : TextView
    lateinit var introToQuizButton : Button
    lateinit var introToTendencyButton : Button
    var mainActivity : MainActivity?= null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_intro, container, false)
        init(view)
        listen()
        makeDetail(MySharedPreferences.getTargetName(mainActivity as Context))
        return view

    }
    fun init(view : View){
        introImage = view.findViewById(R.id.intro_image)
        introDetailText = view.findViewById(R.id.intro_detail_text)
        introToQuizButton = view.findViewById(R.id.intro_to_quiz_button)
        introToTendencyButton = view.findViewById(R.id.intro_to_tendency_button)
    }
    fun makeDetail(str:String){
        if(str == "방경림"){
            introDetailText.setText(resources.getText(R.string.bkr_intro))
            introImage.setImageResource(R.drawable.bkr)
        }
        else if(str =="하수진"){
            introDetailText.setText(resources.getText(R.string.hsj_intro))
            introImage.setImageResource(R.drawable.hsj)
        }
        else if(str =="함운경"){
            introDetailText.setText(resources.getText(R.string.hwk_intro))
            introImage.setImageResource(R.drawable.hwk)
        }
        else if(str =="이승수"){
            introDetailText.setText(resources.getText(R.string.lss_intro))
            introImage.setImageResource(R.drawable.lss)
        }
        else if(str =="성도연"){
            introDetailText.setText(resources.getText(R.string.sdy_intro))
            introImage.setImageResource(R.drawable.sdy)
        }
        else if(str =="구자윤"){
            introDetailText.setText(resources.getText(R.string.kjy_intro))
            introImage.setImageResource(R.drawable.kjy)
        }
        else if(str =="김정훈"){
            introDetailText.setText(resources.getText(R.string.kjh_intro))
            introImage.setImageResource(R.drawable.kjh)
        }
        else if(str =="심재욱"){
            introDetailText.setText(resources.getText(R.string.sjw_intro))
            introImage.setImageResource(R.drawable.sjw)
        }
        else if(str =="이혜빈"){
            introDetailText.setText(resources.getText(R.string.lhb_intro))
            introImage.setImageResource(R.drawable.lhb)
        }
        else if(str =="이다연"){
            introDetailText.setText(resources.getText(R.string.ldy_intro))
            introImage.setImageResource(R.drawable.ldy)
        }
        else if(str =="조장현"){
            introDetailText.setText(resources.getText(R.string.jjh_intro))
            introImage.setImageResource(R.drawable.jjh)
        }
        else if(str =="박현태"){
            introDetailText.setText(resources.getText(R.string.pht_intro))
            introImage.setImageResource(R.drawable.pht)
        }
        else if(str =="김규리"){
            introDetailText.setText(resources.getText(R.string.kkr_intro))
            introImage.setImageResource(R.drawable.kkr)
        }
        else if(str =="문소희"){
            introDetailText.setText(resources.getText(R.string.msh_intro))
            introImage.setImageResource(R.drawable.msh)
        }


    }
    fun listen(){
        introToQuizButton.setOnClickListener(this)
        introToTendencyButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        var id = v?.id
        when(id){
            R.id.intro_to_quiz_button ->{
                var next : QuizStart = QuizStart()
                var trans = mainActivity!!.supportFragmentManager.beginTransaction()
                trans.add(R.id.frame_layout, next)
                trans.addToBackStack("intro")
                trans.hide(this)
                trans.commit()
            }
            R.id.intro_to_tendency_button->{
                var next : CheckTendency = CheckTendency()
                var trans = mainActivity!!.supportFragmentManager.beginTransaction()
                trans.add(R.id.frame_layout, next)
                trans.addToBackStack("intro")
                trans.hide(this)
                trans.commit()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
}