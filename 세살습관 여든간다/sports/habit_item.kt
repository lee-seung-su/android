package org.techtown.sports

import android.os.Parcel
import android.os.Parcelable

class habit_item (
    var title: String,
    var dday: String,
    var count: String,
    var calendar_status: Int,
    var calendar_date: String?,
    var habit_image : Int,
    var use_stopwatch : Int
)