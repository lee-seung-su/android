package com.example.flowtext

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var enterButton:Button
    lateinit var edit1:EditText
    lateinit var intent1 : Intent
    lateinit var text1 : TextView
    lateinit var clearButton : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        enterButton.setOnClickListener(this)
        clearButton.setOnClickListener(this)

    }
    fun init(){
        enterButton = findViewById(R.id.enter_button)
        edit1 = findViewById(R.id.edit1)
        clearButton = findViewById(R.id.clear_button)
    }

    override fun onClick(v: View?) {
        when{
            v?.id == R.id.enter_button->{
                intent1 = Intent(this, Sub::class.java)
                intent1.putExtra("from",edit1.text.toString())
                startActivityForResult(intent1, 99)
            }
            v?.id == R.id.clear_button->{
                edit1.setText(null)
            }
        }
    }
}