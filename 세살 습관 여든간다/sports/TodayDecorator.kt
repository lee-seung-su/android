package org.techtown.sports

import android.graphics.Color
import android.graphics.Typeface
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import androidx.core.content.ContextCompat
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.CalendarDay.today
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import java.util.*

class TodayDecorator : DayViewDecorator {
    var date : CalendarDay = today()
    override fun shouldDecorate(day: CalendarDay?): Boolean {
        return date.equals(day)
    }

    override fun decorate(view: DayViewFacade?) {
        view!!.addSpan(ForegroundColorSpan(Color.parseColor("#70AA2C")))
        view.addSpan(StyleSpan(Typeface.BOLD))
        view.addSpan(RelativeSizeSpan(1.3f))
    }

}

