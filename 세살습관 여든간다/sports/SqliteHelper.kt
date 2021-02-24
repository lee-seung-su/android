package org.techtown.sports

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqliteHelper(context : Context, name:String, version : Int) : SQLiteOpenHelper(context, name, null,version) {
    override fun onCreate(db: SQLiteDatabase?) {
        val str : String = "create table habit (" +
                "title text," +
                "dday text," +
                "calendar_status integer, "+
                "calendar_date text, "+
                "count text," +
                "useStopwatch integer,"+
                "habit_image integer," +
                "PRIMARY KEY(title,calendar_date))"
        db?.execSQL(str)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
    fun insertHabit(habit : habit_item){
        val values = ContentValues() // 값 입력위한 객체
        values.put("title",habit.title)
        values.put("dday",habit.dday)

        values.put("calendar_status",habit.calendar_status)
        values.put("calendar_date",habit.calendar_date)
        values.put("count",habit.count)

        values.put("useStopwatch",habit.use_stopwatch)
        values.put("habit_image",habit.habit_image)
        val wd = writableDatabase  //쓰기위한 객체
        wd.insert("habit",null, values) // habit이라는 table에 values객체 입력
        wd.close() // 꼭 닫아주기
    }
    fun selectHabitByTitleAndDate(title : String, date:String):habit_item?{
        var query : String = "Select * from habit where title = '${title}' and calendar_date = '${date}'"
        val rd = readableDatabase
        val cursor = rd.rawQuery(query, null)
        var d : habit_item? = null
        while(cursor.moveToNext()){
            var title = cursor.getString(cursor.getColumnIndex("title"))
            var dday = cursor.getString(cursor.getColumnIndex("dday"))
            var count = cursor.getString(cursor.getColumnIndex("count"))
            var calendar_status = cursor.getInt(cursor.getColumnIndex("calendar_status"))
            var calendar_date = cursor.getString(cursor.getColumnIndex("calendar_date"))
            var habit_image = cursor.getInt(cursor.getColumnIndex("habit_image"))
            var use_stopwatch = cursor.getString(cursor.getColumnIndex("useStopwatch"))
            d = habit_item(title,dday,count,calendar_status,calendar_date,habit_image,use_stopwatch.toInt())
            break;
        }
        cursor.close()
        rd.close()
        return d
    }
    fun selectHabitListByTitle(title:String):MutableList<habit_item>{
        var query : String = "Select * from habit where title = '${title}'"
        val rd = readableDatabase
        val cursor = rd.rawQuery(query, null)
        var d : MutableList<habit_item> = mutableListOf<habit_item>()
        while(cursor.moveToNext()){
            var title = cursor.getString(cursor.getColumnIndex("title"))
            var dday = cursor.getString(cursor.getColumnIndex("dday"))
            var count = cursor.getString(cursor.getColumnIndex("count"))
            var calendar_status = cursor.getInt(cursor.getColumnIndex("calendar_status"))
            var calendar_date = cursor.getString(cursor.getColumnIndex("calendar_date"))
            var habit_image = cursor.getInt(cursor.getColumnIndex("habit_image"))
            var use_stopwatch = cursor.getString(cursor.getColumnIndex("useStopwatch"))
            d.add(habit_item(title,dday,count,calendar_status,calendar_date,habit_image,use_stopwatch.toInt()))
        }
        cursor.close()
        rd.close()
        return d
    }
    fun selectOneHabitByTitle(title : String):habit_item?{
        var query : String = "Select * from habit where title = '${title}' and calendar_date = '0-0-0'"
        val rd = readableDatabase
        val cursor = rd.rawQuery(query, null)
        var d : habit_item? = null
        while(cursor.moveToNext()){
            var title = cursor.getString(cursor.getColumnIndex("title"))
            var dday = cursor.getString(cursor.getColumnIndex("dday"))
            var count = cursor.getString(cursor.getColumnIndex("count"))
            var calendar_status = cursor.getInt(cursor.getColumnIndex("calendar_status"))
            var calendar_date = cursor.getString(cursor.getColumnIndex("calendar_date"))
            var habit_image = cursor.getInt(cursor.getColumnIndex("habit_image"))
            var use_stopwatch = cursor.getString(cursor.getColumnIndex("useStopwatch"))
            d = habit_item(title,dday,count,calendar_status,calendar_date,habit_image,use_stopwatch.toInt())
            break;
        }
        cursor.close()
        rd.close()
        return d
    }
    fun selectHabitDistinct():MutableList<habit_item>{
        var list = mutableListOf<habit_item>()
        val str : String = "Select Distinct title, dday, count,habit_image,useStopwatch from habit"
        val rd = readableDatabase
        val cursor = rd.rawQuery(str,null)
        while(cursor.moveToNext()){
            var title = cursor.getString(cursor.getColumnIndex("title"))
            var dday = cursor.getString(cursor.getColumnIndex("dday"))
            var count = cursor.getString(cursor.getColumnIndex("count"))
            var habit_image = cursor.getInt(cursor.getColumnIndex("habit_image"))
            var use_stopwatch = cursor.getString(cursor.getColumnIndex("useStopwatch"))
            //var calendar_status = cursor.getInt(cursor.getColumnIndex("calendar_status"))
            //var calendar_date = cursor.getString(cursor.getColumnIndex("calendar_date"))
            var d = habit_item(title,dday,count,0,null,habit_image,use_stopwatch.toInt())
            list.add(d)
        }
        cursor.close()
        rd.close()
        return list
    }
    fun selectHabit():MutableList<habit_item>{
        val list = mutableListOf<habit_item>()
        val str : String = "Select * from habit"
        val rd = readableDatabase  //읽기위한 객체
        val cursor = rd.rawQuery(str, null) //rawQuery방식으로 execSQL실행 (모든 행 읽기위한 객체 반환)
        while(cursor.moveToNext()){
            var title = cursor.getString(cursor.getColumnIndex("title"))
            var dday = cursor.getString(cursor.getColumnIndex("dday"))
            var count = cursor.getString(cursor.getColumnIndex("count"))
            var calendar_status = cursor.getInt(cursor.getColumnIndex("calendar_status"))
            var calendar_date = cursor.getString(cursor.getColumnIndex("calendar_date"))
            var habit_image = cursor.getInt(cursor.getColumnIndex("habit_image"))
            var use_stopwatch = cursor.getString(cursor.getColumnIndex("useStopwatch"))
            var d = habit_item(title,dday,count,calendar_status,calendar_date,habit_image,use_stopwatch.toInt())
            list.add(d)
        }
        cursor.close()
        rd.close()
        return list
    }

    fun updateHabit(habit:habit_item){
        val values = ContentValues()  //값 입력위한 객체
        values.put("title", habit.title)
        values.put("dday",habit.dday)
        values.put("count",habit.count)
        values.put("calendar_status",habit.calendar_status)

        val wd = writableDatabase
        wd.update("habit",values, "title = '${habit.title}' and calendar_date = '${habit.calendar_date}'", null) // where조건에 맞는 row에 values값 update
        wd.close()
    }
    fun deleteHabit(habit : habit_item){
        val str = "delete from habit where title = '${habit.title}'"
        val wd = writableDatabase
        wd.execSQL(str)
        wd.close()
    }
}