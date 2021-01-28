package com.example.storyboard

import android.content.Context
import android.content.Intent
import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import kotlin.random.Random

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WorldCupResult.newInstance] factory method to
 * create an instance of this fragment.
 */
class WorldCupResult : Fragment(),View.OnClickListener {
    lateinit var worldcupResultImage : ImageView
    lateinit var worldcupAgainButton : Button
    lateinit var worldcupHomeButton : Button
    var mainActivity : MainActivity ?= null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_world_cup_result, container, false)
        init(view)
        var i = Random.nextInt(2)
        if(i == 0){
            worldcupResultImage.setImageResource(R.drawable.test_image)
        }
        else{
            worldcupResultImage.setImageResource(R.drawable.tongtong)
        }
        listen()
        return view
    }
    fun init(view:View){
        worldcupResultImage = view.findViewById(R.id.worldcup_result_image)
        worldcupAgainButton = view.findViewById(R.id.worldcup_again_button)
        worldcupHomeButton = view.findViewById(R.id.worldcup_home_button)
    }
    fun listen(){
        worldcupAgainButton.setOnClickListener(this)
        worldcupHomeButton.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        var id = v?.id
        when(id){
            R.id.worldcup_again_button->{
                var next = WorldCupStart()
                var trans = mainActivity!!.supportFragmentManager.beginTransaction()
                trans.add(R.id.frame_layout, next)
                trans.hide(this)
                trans.addToBackStack("Worldcup Result")
                trans.commit()

            }
            R.id.worldcup_home_button->{
                var intent = Intent(mainActivity, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
                mainActivity!!.finish()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
}