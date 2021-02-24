package com.example.storyboard

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.content.ContextCompat.getSystemService
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

    lateinit var fightingPersonImage : ImageView
    lateinit var fightingPersonEdit : EditText
    lateinit var fightingPersonComments : TextView
    lateinit var fightingPersonHomeButton : Button
    lateinit var likesButton : ImageButton
    lateinit var likesNumText : TextView
    var mainActivity : MainActivity ?= null
    var likesNum = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_fighting_message, container, false)
        init(view)
        var str = MySharedPreferences.getTargetName(mainActivity as Context)
        makeImage(str)
        likesButton.setOnClickListener{
            if(likesNum==0){
                likesNumText.visibility = View.VISIBLE
            }
            likesNumText.text = "${likesNum} Likes"
            likesNum++
        }
        fightingPersonHomeButton.setOnClickListener{
            var next : SelectMenu = SelectMenu()
            var trans = mainActivity!!.supportFragmentManager.beginTransaction()
            trans.add(R.id.frame_layout, next)
            trans.addToBackStack("Today Match Result")
            trans.hide(this)
            trans.commit()
            /*
            var intent = Intent(maintActivity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            */

        }
        fightingPersonEdit.setOnEditorActionListener{textview, action, event ->

            if(action == EditorInfo.IME_ACTION_DONE){
                val inputMethodManager = mainActivity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(fightingPersonEdit.windowToken, 0)
                if(fightingPersonEdit.text.toString() != null){
                    fightingPersonComments.setText("${fightingPersonComments.text}\n${fightingPersonEdit.text}")
                    fightingPersonEdit.setText("")

                }
            }
            true
        }
        return view
    }
    fun init(view : View){
        fightingPersonImage = view.findViewById(R.id.fighting_message_image)
        fightingPersonEdit = view.findViewById(R.id.fighting_message_edit)
        fightingPersonComments = view.findViewById(R.id.fighting_message_comments)
        fightingPersonHomeButton = view.findViewById(R.id.fighting_message_home_button)
        likesButton = view.findViewById(R.id.fighting_likes_button)
        likesNumText = view.findViewById(R.id.fighting_likes_num_text)
    }
    fun makeImage(str : String){
        if(str == "방경림"){
            fightingPersonImage.setImageResource(R.drawable.bkr)
        }
        else if(str =="하수진"){
            fightingPersonImage.setImageResource(R.drawable.hsj)
        }
        else if(str =="함운경"){
            fightingPersonImage.setImageResource(R.drawable.hwk)
        }
        else if(str =="이승수"){
           fightingPersonImage.setImageResource(R.drawable.lss)
        }
        else if(str =="성도연"){
            fightingPersonImage.setImageResource(R.drawable.sdy)
        }
        else if(str =="구자윤"){
            fightingPersonImage.setImageResource(R.drawable.kjy)
        }
        else if(str =="김정훈"){
            fightingPersonImage.setImageResource(R.drawable.kjh)
        }
        else if(str =="심재욱"){
            fightingPersonImage.setImageResource(R.drawable.sjw)
        }
        else if(str =="이혜빈"){
            fightingPersonImage.setImageResource(R.drawable.lhb)
        }
        else if(str =="이다연"){
            fightingPersonImage.setImageResource(R.drawable.ldy)
        }
        else if(str =="조장현"){
            fightingPersonImage.setImageResource(R.drawable.jjh)
        }
        else if(str =="박현태"){
            fightingPersonImage.setImageResource(R.drawable.pht)
        }
        else if(str =="김규리"){
            fightingPersonImage.setImageResource(R.drawable.kkr)
        }
        else if(str =="문소희"){
            fightingPersonImage.setImageResource(R.drawable.msh)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
}