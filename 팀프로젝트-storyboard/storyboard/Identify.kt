package com.example.storyboard

import android.content.Context
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
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
    lateinit var whoAmIEdit : EditText
    lateinit var whatIsMyTeamData : MutableList<String>
    lateinit var whatIsMyTeamAdapter : ArrayAdapter<String>
    lateinit var EnterButton : Button
    lateinit var whatIsMyTeamSpinner : Spinner
    var teamFlag=0
    lateinit var inputMethodManager : InputMethodManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_identify, container, false)
        init(view)
        Log.d("@@@@@@@@@@@@@@","${whoAmIEdit.text}")
        EnterButton.setOnClickListener{
            if((teamFlag == 1) && (whoAmIEdit.text.toString() != "")){
                var next = SelectMenu()
                var trans = mainActivity!!.supportFragmentManager.beginTransaction()
                trans.add(R.id.frame_layout, next)
                trans.hide(this)
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
                trans.addToBackStack("Who am I")

                trans.commit()
            }
            else {
                if (teamFlag == 1) {
                    var toast = Toast.makeText(mainActivity, "이름을 입력하세요!!", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
                    toast.show()
                } else {
                    var toast = Toast.makeText(mainActivity, "소속을 입력하세요!!", Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
                    toast.show()
                }
            }
        }
        whatIsMyTeamSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if(position != 0){
                    teamFlag = 1
                }
                else{
                    teamFlag = 0
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        whoAmIEdit.setOnEditorActionListener{textview, action, event ->
            if(action == EditorInfo.IME_ACTION_DONE) {

                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
                if((teamFlag == 1) && (whoAmIEdit.text.toString() != "")){
                    var next = SelectMenu()
                    var trans = mainActivity!!.supportFragmentManager.beginTransaction()
                    trans.add(R.id.frame_layout, next)
                    trans.hide(this)
                    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
                    trans.addToBackStack("Who am I")

                    trans.commit()
                }
                else {
                    if (teamFlag == 1) {
                        var toast = Toast.makeText(mainActivity, "이름을 입력하세요!!", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
                        toast.show()
                    } else {
                        var toast = Toast.makeText(mainActivity, "소속을 입력하세요!!", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0)
                        toast.show()
                    }
                }
            }
            true
        }

        return view
    }

    fun init(view: View){
        whoAmIEdit = view.findViewById(R.id.who_am_i_edit)
        EnterButton = view.findViewById(R.id.identify_enter_button)

        whatIsMyTeamSpinner = view.findViewById(R.id.what_is_my_team_spinner)
        whatIsMyTeamData = mutableListOf("--소속--", "Mobile융합", "스마트워크", "건설모터스", "IT솔루션", "네트워크")
        whatIsMyTeamAdapter = ArrayAdapter<String>(mainActivity as Context, android.R.layout.simple_list_item_1, whatIsMyTeamData)
        whatIsMyTeamSpinner.adapter = whatIsMyTeamAdapter
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
        inputMethodManager = mainActivity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }
}