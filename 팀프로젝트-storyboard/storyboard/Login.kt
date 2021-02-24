package com.example.storyboard

import android.content.Context
import android.content.SharedPreferences
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
 * Use the [Login.newInstance] factory method to
 * create an instance of this fragment.
 */
class Login : Fragment() {
    lateinit var ID : EditText
    lateinit var PW : EditText
    lateinit var loginButton : Button
    lateinit var loginSignUpButton : Button
    var mainActivity : MainActivity?= null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_login, container, false)
        init(view)

        loginButton.setOnClickListener{
            if(MySharedPreferences.getUserId(mainActivity as Context) == ID.text.toString()){
                if(MySharedPreferences.getUserPw(mainActivity as Context) == PW.text.toString()){
                    var next = ChoosePeople()
                    var trans = mainActivity!!.supportFragmentManager.beginTransaction()
                    trans.add(R.id.frame_layout, next)
                    trans.hide(this)
                    trans.addToBackStack("login")

                    trans.commit()
                }
                else{
                    var toast = Toast.makeText(mainActivity,"비번이 달라여!!",Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.CENTER_VERTICAL,0,0)
                    toast.show()
                }
            }
            else{
                var toast = Toast.makeText(mainActivity,"아이디가 달라여!!",Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER_VERTICAL,0,0)
                toast.show()
            }

        }
        loginSignUpButton.setOnClickListener{
            var next : SignUp = SignUp()
            var trans = mainActivity!!.supportFragmentManager.beginTransaction()
            trans.add(R.id.frame_layout, next)
            trans.hide(this)
            trans.addToBackStack("login")
            trans.commit()
        }
        return view
    }
    fun init(view : View){
        ID = view.findViewById(R.id.login_id)
        PW = view.findViewById(R.id.login_pw)
        loginButton = view.findViewById(R.id.login_button)
        loginSignUpButton = view.findViewById(R.id.login_sign_up_button)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
}