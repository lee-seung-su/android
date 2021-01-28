package com.example.storyboard

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WorldCupStart.newInstance] factory method to
 * create an instance of this fragment.
 */
class WorldCupStart : Fragment() {
    lateinit var worldcupStartButton : Button
    var mainActivity : MainActivity ?= null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_world_cup_start, container, false)
        init(view)
        mainActivity!!.worldcupInd = 1
        var worldcupCntText :TextView = mainActivity!!.findViewById(R.id.ind_total_text)
        worldcupCntText.text = ""
        worldcupStartButton.setOnClickListener{
            var next = WorldCup()
            var trans = mainActivity!!.supportFragmentManager.beginTransaction()
            trans.add(R.id.frame_layout, next)
            trans.hide(this)
            trans.addToBackStack("Worldcup start")

            trans.commit()
        }
        return view

    }
    fun init(view : View){
        worldcupStartButton = view.findViewById(R.id.worldcup_start_button)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
}