package org.techtown.sports

import android.graphics.Color
import android.util.Log
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.spans.DotSpan

class MemoDecorator(date: MutableList<CalendarDay>) : DayViewDecorator {
    var thisDate = date

    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return thisDate.contains(day)
    }

    override fun decorate(view: DayViewFacade?) {
        Log.d("@#@234234#","${thisDate.size}")
        view!!.addSpan(DotSpan(5f, Color.RED))
    }

}
