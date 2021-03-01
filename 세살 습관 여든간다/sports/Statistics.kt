package org.techtown.sports

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.motion.utils.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Statistics.newInstance] factory method to
 * create an instance of this fragment.
 */
class Statistics(title: String) : Fragment() {
    var habitTitle = title
    lateinit var helper : SqliteHelper
    var mainActivity : ShowCalendar?= null
    lateinit var pieChart : PieChart
    lateinit var pieChartTextView : TextView
    var values : ArrayList<PieEntry> = arrayListOf<PieEntry>()
    lateinit var habitList : MutableList<habit_item>
    var successValue =0
    var normalValue =0
    var failValue =0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_statistics, container, false)
        init(view)

        return view

    }
    fun init(view : View){
        pieChart = view.findViewById(R.id.pie_chart)
        pieChartTextView = view.findViewById(R.id.pie_chart_textview)
        makePieChart()
    }
    fun makePieChart(){
        pieChart.setUsePercentValues(true)

        pieChart.setExtraOffsets(5f,10f,5f,5f)

        pieChart.dragDecelerationFrictionCoef = 0.95f

        pieChart.isDrawHoleEnabled = false
        pieChart.setHoleColor(Color.WHITE)
        pieChart.transparentCircleRadius = 61f
        pieChart.setEntryLabelColor(Color.BLACK)  //파이차트 내부 label
        pieChart.setEntryLabelTextSize(25f)

        var description = Description()   //파이차트 외부 description
        description.text = ""
        pieChart.description = description

        var dataSet = PieDataSet(values,"")
        dataSet.sliceSpace = 5f
        dataSet.selectionShift = 5f

        habitList = helper.selectHabitListByTitle(habitTitle)
        var temp = 0
        for(item in habitList){
            temp = item.calendar_status
            when(temp%4){
                1->{successValue++}
                2->{normalValue++}
                3->{failValue++}
                0->{}
            }
        }
        var arr = mutableListOf<Int>()

        if(successValue !=0) {
            values.add(PieEntry(successValue.toFloat(), "Success"))
            arr.add(Color.parseColor("#192B81"))
            //dataSet.addColor(Color.parseColor("#192B81"))
        }
        if(normalValue != 0) {
            values.add(PieEntry(normalValue.toFloat(), "Normal"))
            arr.add(Color.parseColor("#357738"))
            //dataSet.addColor(Color.parseColor("#357738"))
        }
        if(failValue !=0) {
            values.add(PieEntry(failValue.toFloat(), "Fail"))
            arr.add(Color.parseColor("#AF1F14"))
            //dataSet.addColor(Color.parseColor("#AF1F14"))
        }
        dataSet.setColors(arr)

        if(successValue == 0 && normalValue ==0 && failValue == 0){
            pieChart.visibility = View.GONE
            pieChartTextView.visibility = View.VISIBLE
        }
        else{
            pieChartTextView.visibility = View.GONE
            pieChart.visibility = View.VISIBLE
        }



        //dataSet.setColors(*ColorTemplate.COLORFUL_COLORS)  //파이차트 색상
        //dataSet.setColors(Color.parseColor("#FF039BE5"), Color.parseColor("#357738"), Color.parseColor("#AF1F14"))

        var data = PieData(dataSet)
        data.setValueTextSize(15f)
        data.setValueTextColor(Color.WHITE)   //파이차트 내부 숫자


        pieChart.data = data
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as ShowCalendar
        helper = SqliteHelper(mainActivity as Context, "habit",1)
    }
}