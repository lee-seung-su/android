package org.techtown.sports

import android.graphics.Color
import android.text.style.ForegroundColorSpan
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import java.util.*

class SundayDecorator : DayViewDecorator {
    var calendar : Calendar = Calendar.getInstance()
    override fun shouldDecorate(day: CalendarDay?): Boolean {
        day!!.copyTo(calendar)
        /*Log.d("##########","$day")
        var year = day!!.year
        var month = day!!.month
        var date = day!!.day
        calendar.set(year, month, date)*/
        var weekDay : Int = calendar.get(Calendar.DAY_OF_WEEK)
        return weekDay == Calendar.SUNDAY
    }

    override fun decorate(view: DayViewFacade?) {
        view!!.addSpan(ForegroundColorSpan(Color.RED))
    }
}

