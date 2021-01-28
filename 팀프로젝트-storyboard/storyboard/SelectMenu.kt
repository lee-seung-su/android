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
    lateinit var quizButton : Button
    lateinit var worldCupButton : Button
    lateinit var todayMatchButton : Button
    lateinit var similarityButton : Button
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
        quizButton = view.findViewById(R.id.quiz_button)
        worldCupButton = view.findViewById(R.id.game_worldcup)
        todayMatchButton = view.findViewById(R.id.today_match_button)
        similarityButton = view.findViewById(R.id.similarity_button)

    }
    fun listen(){
        quizButton.setOnClickListener(this)
        worldCupButton.setOnClickListener(this)
        todayMatchButton.setOnClickListener(this)
        similarityButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        var id = v?.id
        when(id){
            R.id.quiz_button ->{
                var next : ChoosePeople = ChoosePeople()
                var trans = mainactivity!!.supportFragmentManager.beginTransaction()
                trans.add(R.id.frame_layout, next)
                trans.addToBackStack("select")
                trans.hide(this)
                trans.commit()
            }
            R.id.game_worldcup ->{
                var next : WorldCupStart = WorldCupStart()
                var trans = mainactivity!!.supportFragmentManager.beginTransaction()
                trans.add(R.id.frame_layout, next)
                trans.addToBackStack("select")
                trans.hide(this)
                trans.commit()
            }
            R.id.today_match_button ->{
                var next : TodayMatch = TodayMatch()
                var trans = mainactivity!!.supportFragmentManager.beginTransaction()
                trans.add(R.id.frame_layout, next)
                trans.addToBackStack("select")
                trans.hide(this)
                trans.commit()
            }
            R.id.similarity_button ->{
                var next : Similarity = Similarity()
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