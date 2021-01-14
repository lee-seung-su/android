package com.example.day3

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var toastBtn:Button
    lateinit var nextPageBtn:Button
    lateinit var editText:EditText
    lateinit var returnText:TextView
    lateinit var main_intent:Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        main_intent = Intent(this, SubActivity::class.java)


        toastBtn.setOnClickListener(this)
        nextPageBtn.setOnClickListener(this)


    }
    fun init(){
        toastBtn = findViewById(R.id.toast_btn)
        nextPageBtn = findViewById(R.id.next_btn)
        editText=findViewById(R.id.edit_1)
        returnText=findViewById(R.id.return_text)
    }

    override fun onClick(v: View?) {
        var id =v?.id
        when(id){
             R.id.toast_btn->{
                var toast :Toast = Toast.makeText(this, editText.text,Toast.LENGTH_SHORT)
                 toast.setGravity(Gravity.CENTER_HORIZONTAL,0,0)
                 toast.show()


            }
            R.id.next_btn->{
                main_intent.putExtra("from11",editText.text.toString())
                startActivityForResult(main_intent,99)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode== Activity.RESULT_OK){
            returnText.text = data?.getStringExtra("return")
        }
    }
}