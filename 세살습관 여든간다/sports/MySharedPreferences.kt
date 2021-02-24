package org.techtown.sports

import android.content.Context
import android.content.SharedPreferences

object MySharedPreferences {
    private val account : String = "user"  //사용자 이름

    //습관 이름 입력
    fun setHabitTitle(context : Context, input : String){
        val prefs : SharedPreferences = context.getSharedPreferences(account, Context.MODE_PRIVATE)  //해당앱에서만 읽기,쓰기가능위해 MODE_PRIVATE
        val editor : SharedPreferences.Editor = prefs.edit()  //해당 account의 객체 editor에 저장
        editor.putString("habitTitle",input)
        editor.commit()
    }
    fun getHabitTitle(context : Context):String{
        val prefs : SharedPreferences = context.getSharedPreferences(account, Context.MODE_PRIVATE)
        return prefs.getString("habitTitle","Error").toString()
    }

    //습관 목표횟수 입력
    fun setHabitCount(context : Context, input : String){
        val prefs : SharedPreferences = context.getSharedPreferences(account, Context.MODE_PRIVATE)  //해당앱에서만 읽기,쓰기가능위해 MODE_PRIVATE
        val editor : SharedPreferences.Editor = prefs.edit()  //해당 account의 객체 editor에 저장
        editor.putString("habitCount",input)
        editor.commit()
    }
    fun getHabitCount(context : Context):String{
        val prefs : SharedPreferences = context.getSharedPreferences(account, Context.MODE_PRIVATE)
        return prefs.getString("habitCount","Error").toString()
    }

    //습관 알람 시작시간 입력
    fun setHabitAlarmStartTime(context : Context, input : String){
        val prefs : SharedPreferences = context.getSharedPreferences(account, Context.MODE_PRIVATE)  //해당앱에서만 읽기,쓰기가능위해 MODE_PRIVATE
        val editor : SharedPreferences.Editor = prefs.edit()  //해당 account의 객체 editor에 저장
        editor.putString("habitAlarmStartTime",input)
        editor.commit()
    }
    fun getHabitTAlarmStartTime(context : Context):String{
        val prefs : SharedPreferences = context.getSharedPreferences(account, Context.MODE_PRIVATE)
        return prefs.getString("habitAlarmStartTime","Error").toString()
    }

    //습관 알람 반복횟수 입력
    fun setHabitAlarmRepeatTime(context : Context, input : String){
        val prefs : SharedPreferences = context.getSharedPreferences(account, Context.MODE_PRIVATE)  //해당앱에서만 읽기,쓰기가능위해 MODE_PRIVATE
        val editor : SharedPreferences.Editor = prefs.edit()  //해당 account의 객체 editor에 저장
        editor.putString("habitAlarmRepeatTime",input)
        editor.commit()
    }
    fun getHabitTAlarmRepeatTime(context : Context):String{
        val prefs : SharedPreferences = context.getSharedPreferences(account, Context.MODE_PRIVATE)
        return prefs.getString("habitAlarmRepeatTime","Error").toString()
    }

    //습관 알람 반복일 입력
    fun setHabitAlarmRepeatDay(context : Context, input : String){
        val prefs : SharedPreferences = context.getSharedPreferences(account, Context.MODE_PRIVATE)  //해당앱에서만 읽기,쓰기가능위해 MODE_PRIVATE
        val editor : SharedPreferences.Editor = prefs.edit()  //해당 account의 객체 editor에 저장
        editor.putString("habitAlarmRepeatDay",input)
        editor.commit()
    }
    fun getHabitTAlarmRepeatDay(context : Context):String{
        val prefs : SharedPreferences = context.getSharedPreferences(account, Context.MODE_PRIVATE)
        return prefs.getString("habitAlarmRepeatDay","Error").toString()
    }

    //습관 방해금지시간-시작 입력
    fun setHabitNoDisturbTimeFrom(context : Context, input : String){
        val prefs : SharedPreferences = context.getSharedPreferences(account, Context.MODE_PRIVATE)  //해당앱에서만 읽기,쓰기가능위해 MODE_PRIVATE
        val editor : SharedPreferences.Editor = prefs.edit()  //해당 account의 객체 editor에 저장
        editor.putString("habitNoDisturbTimeFrom",input)
        editor.commit()
    }
    fun getHabitNoDisturbTimeFrom(context : Context):String{
        val prefs : SharedPreferences = context.getSharedPreferences(account, Context.MODE_PRIVATE)
        return prefs.getString("habitNoDisturbTimeFrom","Error").toString()
    }

    //습관 방해금지시간-종료 입력
    fun setHabitNoDisturbTimeTo(context : Context, input : String){
        val prefs : SharedPreferences = context.getSharedPreferences(account, Context.MODE_PRIVATE)  //해당앱에서만 읽기,쓰기가능위해 MODE_PRIVATE
        val editor : SharedPreferences.Editor = prefs.edit()  //해당 account의 객체 editor에 저장
        editor.putString("habitNoDisturbTimeTo",input)
        editor.commit()
    }
    fun getHabitNoDisturbTimeTo(context : Context):String{
        val prefs : SharedPreferences = context.getSharedPreferences(account, Context.MODE_PRIVATE)
        return prefs.getString("habitNoDisturbTimeTo","Error").toString()
    }

    //습관 목표기한(dday type) 입력
    fun setHabitDeadLineDDay(context : Context, input : String){
        val prefs : SharedPreferences = context.getSharedPreferences(account, Context.MODE_PRIVATE)  //해당앱에서만 읽기,쓰기가능위해 MODE_PRIVATE
        val editor : SharedPreferences.Editor = prefs.edit()  //해당 account의 객체 editor에 저장
        editor.putString("habitDeadLineDDay",input)
        editor.commit()
    }
    fun getHabitDeadLineDDay(context : Context):String{
        val prefs : SharedPreferences = context.getSharedPreferences(account, Context.MODE_PRIVATE)
        return prefs.getString("habitDeadLineDDay","Error").toString()
    }

    //습관 목표기한(date type) 입력
    fun setHabitDeadLineDate(context : Context, input : String){
        val prefs : SharedPreferences = context.getSharedPreferences(account, Context.MODE_PRIVATE)  //해당앱에서만 읽기,쓰기가능위해 MODE_PRIVATE
        val editor : SharedPreferences.Editor = prefs.edit()  //해당 account의 객체 editor에 저장
        editor.putString("habitDeadLineDate",input)
        editor.commit()
    }
    fun getHabitDeadLineDate(context : Context):String{
        val prefs : SharedPreferences = context.getSharedPreferences(account, Context.MODE_PRIVATE)
        return prefs.getString("habitDeadLineDate","Error").toString()
    }

    //습관 목표율 입력
    fun setHabitGoal(context : Context, input : String){
        val prefs : SharedPreferences = context.getSharedPreferences(account, Context.MODE_PRIVATE)  //해당앱에서만 읽기,쓰기가능위해 MODE_PRIVATE
        val editor : SharedPreferences.Editor = prefs.edit()  //해당 account의 객체 editor에 저장
        editor.putString("habitGoal",input)
        editor.commit()
    }
    fun getHabitGoal(context : Context):String{
        val prefs : SharedPreferences = context.getSharedPreferences(account, Context.MODE_PRIVATE)
        return prefs.getString("habitGoal","Error").toString()
    }

    //차선책 횟수 입력
    fun setHabitAlternate(context : Context, input : String){
        val prefs : SharedPreferences = context.getSharedPreferences(account, Context.MODE_PRIVATE)  //해당앱에서만 읽기,쓰기가능위해 MODE_PRIVATE
        val editor : SharedPreferences.Editor = prefs.edit()  //해당 account의 객체 editor에 저장
        editor.putString("habitAlternate",input)
        editor.commit()
    }
    fun getHabitAlternate(context : Context):String{
        val prefs : SharedPreferences = context.getSharedPreferences(account, Context.MODE_PRIVATE)
        return prefs.getString("habitAlternate","Error").toString()
    }

    //습관 이미지 입력
    fun setHabitImage(context : Context, input : Int){
        val prefs : SharedPreferences = context.getSharedPreferences(account, Context.MODE_PRIVATE)  //해당앱에서만 읽기,쓰기가능위해 MODE_PRIVATE
        val editor : SharedPreferences.Editor = prefs.edit()  //해당 account의 객체 editor에 저장
        editor.putInt("habitImage",input)
        editor.commit()
    }
    fun getHabitImage(context : Context):Int{
        val prefs : SharedPreferences = context.getSharedPreferences(account, Context.MODE_PRIVATE)
        return prefs.getInt("habitImage",-1).toInt()
    }
    fun setHabitStopWatch(context : Context, input : String){
        val prefs : SharedPreferences = context.getSharedPreferences(account, Context.MODE_PRIVATE)  //해당앱에서만 읽기,쓰기가능위해 MODE_PRIVATE
        val editor : SharedPreferences.Editor = prefs.edit()  //해당 account의 객체 editor에 저장
        editor.putString("habitStopwatch",input)
        editor.commit()
    }
    fun getHabitStopWatch(context : Context):String{
        val prefs : SharedPreferences = context.getSharedPreferences(account, Context.MODE_PRIVATE)
        return prefs.getString("habitStopwatch","Error").toString()
    }
    //SharedPreferences 삭제
    fun clearVariable(context : Context){
        val prefs : SharedPreferences = context.getSharedPreferences(account, Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = prefs.edit()
        editor.clear()
        editor.commit()
    }
}