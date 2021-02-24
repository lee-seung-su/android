package com.example.storyboard

import android.content.Context
import android.os.Bundle
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
 * Use the [SignUp.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUp : Fragment() {
    lateinit var signUpId : EditText
    lateinit var signUpPw : EditText
    lateinit var signUpName : EditText
    lateinit var signUpButton : Button
    var mainActivity : MainActivity?= null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_sign_up, container, false)
        init(view)
        signUpButton.setOnClickListener{
            if(signUpId.text.toString().isBlank()){
                var toast = Toast.makeText(mainActivity, "아이디를 입력하세요!!!",Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER_VERTICAL,0,0)
                toast.show()
            }
            else if(signUpPw.text.toString().isBlank()){
                var toast = Toast.makeText(mainActivity, "비번을 입력하세요!!!",Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER_VERTICAL,0,0)
                toast.show()
            }
            else {
                MySharedPreferences.setUserId(mainActivity as Context,"${signUpId.text}")
                MySharedPreferences.setUserPw(mainActivity as Context, "${signUpPw.text}")
                var next: Login = Login()
                var trans = mainActivity!!.supportFragmentManager.beginTransaction()
                trans.add(R.id.frame_layout, next)
                trans.hide(this)
                trans.addToBackStack("Sign Up")
                trans.commit()
            }
        }
        return view

    }

    fun init(view : View){
        signUpName = view.findViewById(R.id.sign_up_name)
        signUpId = view.findViewById(R.id.sign_up_id)
        signUpPw = view.findViewById(R.id.sign_up_pw)
        signUpButton = view.findViewById(R.id.sign_up_button)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
}