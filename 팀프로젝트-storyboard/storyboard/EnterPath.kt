package com.example.storyboard

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EnterPath.newInstance] factory method to
 * create an instance of this fragment.
 */
class EnterPath() : Fragment() {
    var mainActivity : MainActivity?=null
    lateinit var filePath : EditText
    lateinit var enterButton : Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_enter_path, container, false)
        init(view)

        enterButton.setOnClickListener{
            if(filePath.text.toString() != ""){
                Log.d("##########","${filePath.text.toString()}")
                var next = Identify()
                var trans = mainActivity!!.supportFragmentManager.beginTransaction()
                trans.addToBackStack("Enter Path")
                trans.add(R.id.frame_layout,next)
                trans.hide(this)
                trans.commit()
            }
            else{
                var toast = Toast.makeText(mainActivity, "파일을 입력하세요", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER_VERTICAL,0,0)
                toast.show()
            }
        }


        return view
    }
    fun init(view: View){
        enterButton = view.findViewById(R.id.enter_button)
        filePath = view.findViewById(R.id.edit_path)
        filePath.requestFocus()
        filePath.setSelection(0)
        var filename : String = "testfile.xlsx"
        var str : String= ""
        str = mainActivity!!.readExcel(filename)
        filePath.setText(str)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

}