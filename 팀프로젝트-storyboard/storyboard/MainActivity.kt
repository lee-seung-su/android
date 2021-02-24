package com.example.storyboard

import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.core.widget.NestedScrollView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.apache.poi.xssf.usermodel.XSSFCell
import org.apache.poi.xssf.usermodel.XSSFRow
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.w3c.dom.Text

import java.io.InputStream
import javax.net.ssl.HandshakeCompletedListener

class MainActivity : AppCompatActivity() {
    lateinit var title : TextView
    lateinit var fragmentLayout : FrameLayout
    lateinit var backButton : FloatingActionButton
    lateinit var indTotalText : TextView
    lateinit var mainScrollView : NestedScrollView
    lateinit var targerNameText : TextView
    var quizInd=0
    var quizTotal = 3
    var worldcupInd=1
    var worldcupTotal=3
    var quizFlag = 0

    //////////////////////////tendecy변수
    var tendencyFlag = 0
    var tendencyInd=0
    var ei_value=0
    var jp_value=0
    var sn_value = 0
    var tf_value=0
    var tendency_select_top = 0
    var tendency_select_bottom = 0
    var handler : Handler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        MySharedPreferences.setUserId(this,"")
        MySharedPreferences.setUserPw(this,"")
        indTotalText.text = ""
        worldcupInd = 1
        quizInd = 1
        setFragment()
        title.setOnClickListener{
            var intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            finish()
        }
        backButton.setOnClickListener{
            onBackPressed()

        }
    }
    fun setFragment(){
        val next : Login = Login()
        var trans = supportFragmentManager.beginTransaction()
        trans.add(R.id.frame_layout, next)
        //trans.addToBackStack("main")
        Log.d("############","setFragment")
        trans.commit()
    }
    fun init(){
        title= findViewById(R.id.main_title)
        fragmentLayout = findViewById(R.id.frame_layout)
        backButton = findViewById(R.id.back_button)
        indTotalText = findViewById(R.id.ind_total_text)
        mainScrollView = findViewById(R.id.main_scrollview)
        targerNameText = findViewById(R.id.target_name_text)
        //MySharedPreferences.setUserId(this,"이승수1")
        //MySharedPreferences.setUserPw(this,"1234")
    }
    fun makeToast(str:String){
        var toast = Toast.makeText(this,"${str.toString()}", Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER_VERTICAL,0,0)
        toast.show()
        handler.postDelayed(Runnable {
            toast.cancel()
        },300)
    }
    fun buttonToggle(button : Button,value:Int){
        if(value == 0) {  //0->1 set
            button.setBackgroundColor(resources.getColor(R.color.purple_200, null))
        }
        else {  //1->0 reset
            button.setBackgroundColor(resources.getColor(R.color.purple_500, null))
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        MySharedPreferences.clearUser(this)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if(quizFlag==1){ //quiz골랐으면
            quizInd--
            if(quizInd <= 1){
                indTotalText.visibility = View.INVISIBLE
                quizInd=1
            }
            else{
                indTotalText.text = "${quizInd}/${quizTotal}"
            }
        }
        else if(tendencyFlag ==1){
            if(tendencyInd>=1 && tendencyInd <=5){    //ei
                returnTendencyValue()
            }
            else if(tendencyInd>=6 && tendencyInd<=10){   //sn
                returnTendencyValue()
            }
            else if(tendencyInd>=11 && tendencyInd<=15){  //tf
                returnTendencyValue()
            }
            else if(tendencyInd>=16 && tendencyInd<=20){  //jp
                returnTendencyValue()
            }
            tendencyInd--
            if(tendencyInd<=0){
                tendencyInd=0
            }
        }
    }
    fun returnTendencyValue(){
        if(tendency_select_top == 1){
            jp_value--
        }
        else if(tendency_select_bottom == 1){
            jp_value++
        }
    }
    fun readExcel(filePath : String) :String {
        var res = ""
        try {
            val myInput: InputStream
            // assetManager 초기 설정
            val assetManager = assets
            //  엑셀 시트 열기

            myInput = assetManager.open(filePath)
            // POI File System 객체 만들기

            var workbook : XSSFWorkbook = XSSFWorkbook(myInput)
            var sheet : XSSFSheet = workbook.getSheetAt(0)

            var rows = sheet.physicalNumberOfRows
            for(rowindex in 1..rows-1){
                var row : XSSFRow = sheet.getRow(rowindex)
                var cells : Int = row.physicalNumberOfCells
                for(columnindex in 0..cells-1){
                    var cell : XSSFCell = row.getCell(columnindex)
                    Log.d("#############","$cell")
                    if(rowindex == 1 && columnindex == 0){res = cell.toString()}
                }
            }


        }catch(e:Exception){
            Log.d("###############","에러")
        }
        return res

    }

}