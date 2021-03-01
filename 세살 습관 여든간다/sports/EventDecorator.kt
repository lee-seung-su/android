package org.techtown.sports

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.style.ForegroundColorSpan
import android.text.style.LineBackgroundSpan
import android.util.Log
import com.prolificinteractive.materialcalendarview.*
import java.util.*

class EventDecorator(date : CalendarDay, habitValue : Int) : DayViewDecorator {
    var thisDate = date
    //var thisHabitList = habitList
    var thisHabitValue = habitValue
    override fun hashCode(): Int {
        Log.d("@!@!@!@!","@@@@ ${thisDate}   ${thisHabitValue}")
        return super.hashCode()
    }

    var dayList : MutableList<CalendarDay> = mutableListOf<CalendarDay>()


    override fun shouldDecorate(day: CalendarDay?): Boolean {

        Log.d("!@#!@#@!#","-->>   ${thisDate}   ${thisHabitValue}")
        return thisDate.equals(day)

        //Log.d("@#@#@#@#@3", "->  ${day!!.year}-${day!!.month}-${day!!.day}   ${thisDate.year}-${thisDate.month}-${thisDate.day}")

    }

    override fun decorate(view: DayViewFacade?) {

            if (thisHabitValue % 4 == 0) {
                    //view!!.addSpan(drawCross(Color.WHITE))
                    view!!.addSpan(drawCircle(30f, Color.BLUE))
                    view.addSpan(ForegroundColorSpan(Color.BLACK))


                } else if (thisHabitValue % 4 == 1) {
                    view!!.addSpan(drawCircle(30f, Color.parseColor("#ffffff")))
                    view.addSpan(drawTriangle(Color.parseColor("#346503")))
                    view.addSpan(ForegroundColorSpan(Color.BLACK))
                } else if (thisHabitValue % 4 == 2) {
                    view!!.addSpan(drawTriangle(Color.parseColor("#ffffff")))
                    view.addSpan(drawCross(Color.RED))
                    view.addSpan(ForegroundColorSpan(Color.BLACK))

                } else if (thisHabitValue % 4 == 3) {
                    view!!.addSpan(drawCross(Color.parseColor("#ffffff")))
                    view.addSpan(ForegroundColorSpan(Color.BLACK))
                }

            //thisHelper.updateHabitCalendarStatus(thisHabit)
            //Log.d("@#@#@#@#@#@", "${tempHabit.calendar_status.toInt()}")
        }



}

class drawCircle : LineBackgroundSpan {
    var radius: Float
    var color: Int

    constructor (radius: Float, color: Int) {
        this.radius = radius
        this.color = color
    }

    override fun drawBackground(
            canvas: Canvas,
            paint: Paint,
            left: Int,
            right: Int,
            top: Int,
            baseline: Int,
            bottom: Int,
            text: CharSequence,
            start: Int,
            end: Int,
            lineNumber: Int
    ) {

        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 10f
        paint.color = this.color
        canvas.drawCircle(((left + right) / 2).toFloat(), ((top + bottom) / 2).toFloat(), radius, paint)

        paint.strokeWidth = 0f
        paint.color = Color.BLACK


    }
}

class drawCross : LineBackgroundSpan {
    var color: Int

    constructor(color: Int) {
        this.color = color
    }

    override fun drawBackground(
            canvas: Canvas,
            paint: Paint,
            left: Int,
            right: Int,
            top: Int,
            baseline: Int,
            bottom: Int,
            text: CharSequence,
            start: Int,
            end: Int,
            lineNumber: Int
    ) {
        paint.color = this.color
        paint.strokeWidth = 10f
        canvas.drawLine((left * 0.5 + 22).toFloat(), (top * 1.5 - 10).toFloat(), (right * 0.5 + 22).toFloat(), (bottom * 1.5 - 10).toFloat(), paint)
        canvas.drawLine((right * 0.5 + 22).toFloat(), (top * 1.5 - 10).toFloat(), (left * 0.5 + 22).toFloat(), (bottom * 1.5 - 10).toFloat(), paint)

        paint.strokeWidth = 0f
        paint.color = Color.BLACK

    }

}

class drawTriangle : LineBackgroundSpan {
    var color: Int

    constructor(color: Int) {
        this.color = color
    }

    override fun drawBackground(
            canvas: Canvas,
            paint: Paint,
            left: Int,
            right: Int,
            top: Int,
            baseline: Int,
            bottom: Int,
            text: CharSequence,
            start: Int,
            end: Int,
            lineNumber: Int
    ) {
        paint.color = this.color
        paint.strokeWidth = 10f
        canvas.drawLine((left * 0.5 + 10).toFloat(), (baseline.toFloat() + 10), (right * 0.5 + 30).toFloat(), (baseline.toFloat() + 10), paint)
        //canvas.drawLine((left*0.75 + 20).toFloat(), (baseline.toFloat()+20), (right*0.75 + 20).toFloat(), (baseline.toFloat()+20),paint)
        canvas.drawLine((left * 0.5 + 15).toFloat(), (baseline.toFloat() + 10), ((left + right) / 2).toFloat(), (top * 1.25 - 20).toFloat(), paint)//  /
        canvas.drawLine(((left + right) / 2).toFloat(), (top * 1.25 - 20).toFloat(), (right * 0.5 + 25).toFloat(), (baseline.toFloat() + 10), paint)  // \


        paint.strokeWidth = 0f
        paint.color = Color.BLACK
    }

}
