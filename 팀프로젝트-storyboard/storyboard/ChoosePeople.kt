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
 * Use the [ChoosePeople.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChoosePeople : Fragment() {
    var mainActivity: MainActivity? = null
    lateinit var choosePeopleSpinner: Spinner
    lateinit var choosePeopleEnterButton: Button
    lateinit var choosePeopleData: MutableList<String>
    lateinit var choosePeopleadapter: ArrayAdapter<String>
    var choosePeopleFlag = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_choose_people, container, false)
        init(view)
        choosePeopleEnterButton.setOnClickListener {
            if(choosePeopleFlag == 1) {
                mainActivity!!.targerNameText.visibility = View.VISIBLE
                mainActivity!!.targerNameText.setText("${MySharedPreferences.getTargetName(mainActivity as Context)}")
                var next = SelectMenu()
                var trans = mainActivity!!.supportFragmentManager.beginTransaction()
                trans.hide(this)
                trans.add(R.id.frame_layout, next)
                trans.addToBackStack("Choose Person")
                trans.commit()
            }
            else{
                var toast = Toast.makeText(mainActivity as Context, "누구!!!!!",Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER_VERTICAL,0,0)
                toast.show()
            }
        }
        choosePeopleSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position != 0) {
                    MySharedPreferences.setTargetName(mainActivity as Context,choosePeopleData.get(position))
                    choosePeopleFlag = 1
                } else {
                    choosePeopleFlag = 0
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        return view
    }

    fun init(view: View) {
        choosePeopleSpinner = view.findViewById(R.id.choose_people_spinner)
        choosePeopleData = mutableListOf("--선택하세요--", "방경림", "하수진", "구자윤", "성도연",
                "김정훈","심재욱","이혜빈","이다연","이승수","함운경","조장현","박현태","김규리","문소희")
        choosePeopleadapter = ArrayAdapter<String>(
            mainActivity as Context,
            android.R.layout.simple_list_item_1,
            choosePeopleData
        )
        choosePeopleSpinner.adapter = choosePeopleadapter
        choosePeopleEnterButton = view.findViewById(R.id.choose_people_enter_button)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

}