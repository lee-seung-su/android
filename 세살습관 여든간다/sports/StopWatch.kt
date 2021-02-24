package org.techtown.sports

import android.content.Context
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.UiThread
import java.util.*
import kotlin.concurrent.timer

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StopWatch.newInstance] factory method to
 * create an instance of this fragment.
 */
class StopWatch : Fragment(),View.OnClickListener {
    lateinit var stopwatchTime : TextView
    lateinit var stopwatchStartButton : Button
    lateinit var stopwatchStopButton : Button
    var mainActivity : MainActivity?= null
    var timerTask : Timer?=null
    var time = 0
    var state = 0  //0 : start , 1 : initialize
    var stopState = 0    //0 : stopStart, 1 : continue
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_stop_watch, container, false)
        init(view)
        listen()

        return view

    }
    fun init(view : View){
        stopwatchTime = view.findViewById(R.id.stopwatch_title)
        stopwatchStartButton = view.findViewById(R.id.stopwatch_start_button)
        stopwatchStopButton = view.findViewById(R.id.stopwatch_stop_button)

    }
    fun listen(){
        stopwatchStartButton.setOnClickListener(this)
        stopwatchStopButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        var id = v?.id
        when(id){
            R.id.stopwatch_start_button->{
                if(state == 0){
                    state = 1
                    stopwatchStartButton.setText("초기화")
                    startTimer()
                }
                else{
                    state = 0
                    stopwatchStartButton.setText("시작")
                    stopwatchStopButton.setText("일시 정지")
                    stopState = 0
                    resetTimer()

                }
            }
            R.id.stopwatch_stop_button->{
                if(stopState == 0 && state == 1) {
                    stopState=1
                    stopwatchStopButton.setText("이어서 시작")
                    stopTimer()
                }
                else if(stopState == 1 && state == 1){
                    stopState=0
                    stopwatchStopButton.setText("일시 정지")
                    startTimer()
                }
            }
        }
    }
    fun startTimer(){
        timerTask = timer(period = 10){
            time++

            val sec = time / 100
            val milli = time % 100

            mainActivity!!.runOnUiThread{
                stopwatchTime.setText("${sec} : ${milli}")
            }
        }
    }
    fun stopTimer(){
        timerTask?.cancel()
    }
    fun resetTimer(){
        timerTask?.cancel()
        time = 0
        mainActivity!!.runOnUiThread {
            stopwatchTime.setText("00 : 00")
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
}