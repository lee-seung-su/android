package com.example.day3

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.w3c.dom.Text

class SubActivity : AppCompatActivity() {
    lateinit var text2 : TextView
    lateinit var editText2 : EditText
    lateinit var returnButton : Button
    lateinit var clearButton:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)
        init()
        text2.text = intent.getStringExtra("from11")
        returnButton.setOnClickListener {
            val returnIntent = Intent()
            returnIntent.putExtra("return",editText2.text.toString())
            setResult(Activity.RESULT_OK,returnIntent)
            finish()
        }
        clearButton.setOnClickListener{
            //editText2.setText(null)
            editText2.text=null
        }

    }
    fun init(){
        text2 = findViewById(R.id.text_2)
        editText2 = findViewById(R.id.edit_2)
        returnButton = findViewById(R.id.return_btn)
        clearButton = findViewById(R.id.clear_btn)
    }
}