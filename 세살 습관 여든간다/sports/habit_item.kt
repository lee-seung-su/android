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
    var use_stopwatch : Int,
    var has_memo : Int,
    var memoContents : String,
    var alarmStartTime : String,
    var alarmRepeat : Int,
    var alarmRepeatDay : Int,
    var noDisturbStartTime : String,
    var noDisturbEndTime : String
)
class common_habit(
    var title : String,
    var habit_image : Int
)
class image_item(
        var title:String,
        var image : Int
)

//"alarmStartTime text,"+
//                "alarmRepeat integer,"+
//                "alarmRepeatDay integer,"+
//                "noDisturbStartTime text,"+
//                "noDisturbEndTime text,"+