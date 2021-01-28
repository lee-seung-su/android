package com.example.storyboard

import android.content.Context
import android.os.Bundle
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
 * Use the [Identify.newInstance] factory method to
 * create an instance of this fragment.
 */
class Identify : Fragment() {
    var mainActivity : MainActivity?=null
    lateinit var whoAmISpinner : Spinner
    lateinit var whoAmIData : MutableList<String>
    lateinit var whoAmIadapter : ArrayAdapter<String>
    lateinit var whoAmIEnterButton : Button

    lateinit var choosePeopleSpinner : Spinner
    lateinit var choosePeopleEnterButton : Button
    lateinit var choosePeopleData : MutableList<String>
    lateinit var choosePeopleadapter : ArrayAdapter<String>
    var choosePeopleFlag = 0

    var whoAmIFlag=0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_identify, container, false)
        init(view)
        whoAmIEnterButton.setOnClickListener{
            if((choosePeopleFlag == 1) && (whoAmIFlag == 1)){
                var next = SelectMenu()
                var trans = mainActivity!!.supportFragmentManager.beginTransaction()
                trans.add(R.id.frame_layout, next)
                trans.hide(this)
                trans.addToBackStack("Who am I")
                trans.commit()
            }
            else {
                if (whoAmIFlag == 1) {
                    var toast = Toast.makeText(mainActivity, "누가 궁금한가요??", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
                    toast.show()
                } else {
                    var toast = Toast.makeText(mainActivity, "누구십니까!!", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
                    toast.show()
                }
            }
        }
        whoAmISpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if(position != 0){
                    whoAmIFlag = 1
                }
                else{
                    whoAmIFlag = 0
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        choosePeopleEnterButton.setOnClickListener{
            if((choosePeopleFlag==1) && (whoAmIFlag == 1)){
                var next = SelectMenu()
                var trans = mainActivity!!.supportFragmentManager.beginTransaction()
                trans.hide(this)
                trans.add(R.id.frame_layout, next)
                trans.addToBackStack("Choose Person")
                trans.commit()
            }
            else {
                if (choosePeopleFlag == 1) {
                    var toast = Toast.makeText(mainActivity as Context, "누구십니까!!", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.CENTER_VERTICAL,0,0)
                    toast.show()
                } else {
                    var toast = Toast.makeText(mainActivity as Context, "누가 궁금한가요??", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
                    toast.show()
                }
            }
        }
        choosePeopleSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if(position !=0){
                    choosePeopleFlag = 1
                }
                else{
                    choosePeopleFlag = 0
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        return view
    }

    fun init(view: View){
        whoAmISpinner = view.findViewById(R.id.who_am_i_spinner)
        whoAmIEnterButton = view.findViewById(R.id.who_am_i_enter_button)
        whoAmIData = mutableListOf("--선택하세요--", "1번", "2번", "3번", "4번", "5번")
        whoAmIadapter = ArrayAdapter<String>(mainActivity as Context, android.R.layout.simple_list_item_1, whoAmIData)
        whoAmISpinner.adapter = whoAmIadapter

        choosePeopleSpinner = view.findViewById(R.id.choose_people_spinner)
        choosePeopleData = mutableListOf("--선택하세요--","1111","2222","3333","4444","5555")
        choosePeopleadapter = ArrayAdapter<String>(mainActivity as Context, android.R.layout.simple_list_item_1, choosePeopleData)
        choosePeopleSpinner.adapter = choosePeopleadapter
        choosePeopleEnterButton = view.findViewById(R.id.choose_people_enter_button)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
}