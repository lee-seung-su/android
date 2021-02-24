package com.example.storyboard

import android.content.Context
import android.content.SharedPreferences

object MySharedPreferences {
    private val account : String = "lee"
    fun setUserId(context : Context, input : String){
        val prefs : SharedPreferences = context.getSharedPreferences(account, Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = prefs.edit()
        editor.putString("MY_ID", input)
        editor.commit()
    }
    fun getUserId(context: Context) : String{
        val prefs : SharedPreferences = context.getSharedPreferences(account,Context.MODE_PRIVATE)
        return prefs.getString("MY_ID","").toString()
    }
    fun setUserPw(context: Context, input : String){
        val prefs : SharedPreferences = context.getSharedPreferences(account, Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = prefs.edit()
        editor.putString("MY_PW",input)
        editor.commit()
    }
    fun getUserPw(context: Context):String{
        val prefs : SharedPreferences = context.getSharedPreferences(account, Context.MODE_PRIVATE)
        return prefs.getString("MY_PW","").toString()
    }
    fun clearUser(context: Context){
        val prefs : SharedPreferences = context.getSharedPreferences(account, Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = prefs.edit()
        editor.clear()
        editor.commit()
    }
    fun setTargetName(context: Context, input : String){
        val prefs : SharedPreferences = context.getSharedPreferences(account, Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = prefs.edit()
        editor.putString("MY_TARGET",input)
        editor.commit()
    }
    fun getTargetName(context: Context):String{
        val prefs : SharedPreferences = context.getSharedPreferences(account, Context.MODE_PRIVATE)
        return prefs.getString("MY_TARGET","").toString()
    }
}