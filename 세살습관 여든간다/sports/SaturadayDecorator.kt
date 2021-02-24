package org.techtown.sports

import android.graphics.Color
import android.text.style.ForegroundColorSpan
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import java.util.*
import java.util.Calendar.getInstance

class SaturdayDecorator : DayViewDecorator {
    var calendar : Calendar = Calendar.getInstance()
    override fun shouldDecorate(day: CalendarDay?): Boolean {
        day!!.copyTo(calendar)
        var weekDay : Int = calendar.get(Calendar.DAY_OF_WEEK)
        return weekDay == Calendar.SATURDAY
    }


    override fun decorate(view: DayViewFacade?) {
        view!!.addSpan(ForegroundColorSpan(Color.BLUE))
    }

}

