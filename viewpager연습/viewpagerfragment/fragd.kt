package com.example.viewpagerfragment

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragd.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragd : Fragment() {
    var mainActivity : MainActivity? = null
    lateinit var webButton : Button
    lateinit var naverButton : Button
    lateinit var dialButton : Button
    lateinit var callButton : Button
    lateinit var enableButton : Button
    lateinit var disableButton : Button
    lateinit var smsButton : Button

    lateinit var webEdit : EditText
    lateinit var phoneEdit : EditText
    lateinit var phoneWatch : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fragd, container, false)
        init(view)
        listen(view)
        phoneEdit.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                phoneWatch.text = phoneEdit.text
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        return view
    }
    fun init(view : View){
        webButton = view.findViewById(R.id.fragd_web_button)
        naverButton = view.findViewById(R.id.fragd_naver_button)
        dialButton = view.findViewById(R.id.fragd_dial_button)
        callButton = view.findViewById(R.id.fragd_call_button)
        enableButton = view.findViewById(R.id.enable_button)
        disableButton = view.findViewById(R.id.disable_button)
        smsButton = view.findViewById(R.id.fragd_sms_button)

        webEdit = view.findViewById(R.id.fragd_web_edit)
        phoneEdit = view.findViewById(R.id.fragd_phone_edit)
        phoneWatch = view.findViewById(R.id.fragd_phone_edit_watcher)
    }
    fun listen(view : View){
        webButton.setOnClickListener{
            var intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://${webEdit.text}")
            startActivity(intent)
        }
        naverButton.setOnClickListener{
            var intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://naver.co.kr")
            startActivity(intent)
        }
        dialButton.setOnClickListener{
            var intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${phoneEdit.text}")
            startActivity(intent)
        }
        callButton.setOnClickListener{
            var PERMISSIONS = ContextCompat.checkSelfPermission(mainActivity as MainActivity, Manifest.permission.CALL_PHONE)
            var intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:${phoneEdit.text}")
            if(PERMISSIONS == PackageManager.PERMISSION_GRANTED){
                startActivity(intent)
            }
            else {
                ActivityCompat.requestPermissions(mainActivity as MainActivity, arrayOf(Manifest.permission.CALL_PHONE),112)
            }
        }
        smsButton.setOnClickListener{
            var intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("smsto:${phoneEdit.text}")
            startActivity(intent)
        }
        enableButton.setOnClickListener{
            phoneWatch.visibility = View.VISIBLE
        }
        disableButton.setOnClickListener{
            phoneWatch.visibility = View.INVISIBLE
        }

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }


}