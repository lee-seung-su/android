package com.example.storyboard

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WorldCup.newInstance] factory method to
 * create an instance of this fragment.
 */
class WorldCup : Fragment(),View.OnClickListener {

    lateinit var image1 : ImageView
    lateinit var image2 : ImageView
    var mainActivity : MainActivity ?= null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_world_cup, container, false)
        init(view)
        var i = Random.nextInt(2)
        if(i==0){
            image1.setImageResource(R.drawable.simpson_image1)
            image2.setImageResource(R.drawable.simpson_image2)
        }
        else{
            image2.setImageResource(R.drawable.simpson_image1)
            image1.setImageResource(R.drawable.simpson_image2)
        }
        var worldcupCntText :TextView = mainActivity!!.findViewById(R.id.ind_total_text)
        worldcupCntText.text = "${mainActivity!!.worldcupInd}/${mainActivity!!.worldcupTotal}"

        listen()
        return view
    }
    fun init(view:View){
        image1 = view.findViewById(R.id.worldcup_image_1)
        image2 = view.findViewById(R.id.worldcup_image_2)
    }
    fun listen(){
        image1.setOnClickListener(this)
        image2.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        var id =v ?.id
        when(id){
            R.id.worldcup_image_1->{
                if(mainActivity!!.worldcupInd >= mainActivity!!.worldcupTotal){
                    var next = WorldCupResult()
                    var trans = mainActivity!!.supportFragmentManager.beginTransaction()
                    trans.add(R.id.frame_layout, next)
                    trans.hide(this)
                    trans.addToBackStack("Worldcup${mainActivity!!.worldcupInd}/${mainActivity!!.worldcupTotal}")
                    trans.commit()
                }
                else {
                    var next = WorldCup()
                    var trans = mainActivity!!.supportFragmentManager.beginTransaction()
                    trans.add(R.id.frame_layout, next)
                    trans.hide(this)
                    trans.addToBackStack("Worldcup${mainActivity!!.worldcupInd}/${mainActivity!!.worldcupTotal}")
                    mainActivity!!.worldcupInd++
                    trans.commit()
                }
            }
            R.id.worldcup_image_2->{
                if(mainActivity!!.worldcupInd >= mainActivity!!.worldcupTotal){
                    var next = WorldCupResult()
                    var trans = mainActivity!!.supportFragmentManager.beginTransaction()
                    trans.add(R.id.frame_layout, next)
                    trans.hide(this)
                    trans.addToBackStack("Worldcup${mainActivity!!.worldcupInd}/${mainActivity!!.worldcupTotal}")
                    trans.commit()
                }
                else {
                    var next = WorldCup()
                    var trans = mainActivity!!.supportFragmentManager.beginTransaction()
                    trans.add(R.id.frame_layout, next)
                    trans.hide(this)
                    trans.addToBackStack("Worldcup${mainActivity!!.worldcupInd}/${mainActivity!!.worldcupTotal}")
                    mainActivity!!.worldcupInd++
                    trans.commit()
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

}