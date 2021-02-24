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
 * Use the [SelectMenu_New.newInstance] factory method to
 * create an instance of this fragment.
 */
class SelectMenu_New : Fragment(),View.OnClickListener {
    lateinit var editIntroButton : Button
    lateinit var checkMessageButton : Button
    lateinit var mbtiTestButton : Button
    var mainActivity : MainActivity?= null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_select_menu__new, container, false)
        init(view)
        listen()

        return view
    }
    fun init(view : View){
        editIntroButton = view.findViewById(R.id.edit_intro_message_button)
        checkMessageButton = view.findViewById(R.id.check_message_button)
        mbtiTestButton = view.findViewById(R.id.mbti_test_button)
    }
    fun listen(){
        editIntroButton.setOnClickListener(this)
        checkMessageButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        var id = v?.id
        when(id){
            R.id.edit_intro_message_button ->{

            }
            R.id.check_message_button ->{

            }
            R.id.mbti_test_button ->{
                var next : CheckTendency = CheckTendency()
                var trans = mainActivity!!.supportFragmentManager.beginTransaction()
                trans.add(R.id.frame_layout, next)
                trans.hide(this)
                trans.addToBackStack("SelectMenu New")
                trans.commit()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
}