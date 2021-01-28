package com.example.storyboard

import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.util.Log
import android.widget.FrameLayout
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.apache.poi.xssf.usermodel.XSSFCell
import org.apache.poi.xssf.usermodel.XSSFRow
import org.apache.poi.xssf.usermodel.XSSFSheet
import org.apache.poi.xssf.usermodel.XSSFWorkbook

import java.io.InputStream

class MainActivity : AppCompatActivity() {
    lateinit var title : TextView
    lateinit var fragmentLayout : FrameLayout
    lateinit var backButton : FloatingActionButton
    lateinit var indTotalText : TextView
    var quizInd=1
    var quizTotal = 3
    var worldcupInd=1
    var worldcupTotal=3
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
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
        val next : EnterPath = EnterPath()
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
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if(quizInd>=2){ //quiz골랐으면
            quizInd--
            if(quizInd <= 1){
                indTotalText.text = "${quizInd}/${quizTotal}"
                quizInd=1
            }
            else{
                indTotalText.text = "${quizInd}/${quizTotal}"
            }
        }
        else if(worldcupInd >=2){
            worldcupInd--
            if(worldcupInd <=1){
                indTotalText.text = "${quizInd}/${quizTotal}"
                worldcupInd=1
            }
            else{
                indTotalText.text = "${worldcupInd}/${worldcupTotal}"
            }
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