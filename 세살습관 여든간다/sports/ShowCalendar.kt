package org.techtown.sports

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.EventLog
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.children
import androidx.core.view.updateLayoutParams
import com.prolificinteractive.materialcalendarview.*
import java.util.*

class ShowCalendar : AppCompatActivity(),View.OnClickListener {
    lateinit var calendar : MaterialCalendarView
    var helper = SqliteHelper(this as Context, "habit",1)
    var handler = Handler(Looper.getMainLooper())
    var main = this
    var habitValue =0
    lateinit var alarmTimeEditButton : Button
    lateinit var noDisturbTimeButton : Button
    var dateList : MutableList<CalendarDay> = mutableListOf<CalendarDay>()
    var habitList : MutableList<habit_item> = mutableListOf<habit_item>()

    lateinit var title : String
    lateinit var showCalendarImage : ImageView
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.to_home->{
                var intent = Intent(this, MainActivity::class.java)
                finish()
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater = menuInflater
        inflater.inflate(R.menu.menu_option2,menu)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_calendar)
        init()
        listen()
        calendar.addDecorator(SaturdayDecorator())
        calendar.addDecorator(SundayDecorator())
        calendar.addDecorator(TodayDecorator())

        calendar.setOnDateLongClickListener(object : OnDateLongClickListener {
            override fun onDateLongClick(widget: MaterialCalendarView, date: CalendarDay) {
                dateList.add(date)
                calendar.invalidateDecorators()
                var next = Memo(date)
                var trans = supportFragmentManager.beginTransaction()
                trans.add(R.id.frame_layout2, next)
                //trans.hide(this)
                trans.addToBackStack("Show Calendar")
                trans.commit()

            }

        })
        calendar.addDecorator(MemoDecorator(dateList))

        calendar.setOnDateChangedListener(object : OnDateSelectedListener {
            override fun onDateSelected(widget: MaterialCalendarView, date: CalendarDay, selected: Boolean) {

                if((date.year > CalendarDay.today().year) || (date.month > CalendarDay.today().month) || (date.day > CalendarDay.today().day)){
                    main.makeToast(main,"미래 날짜 입력 불가!!")
                }
                else {
                    var tempHabit = helper.selectHabitByTitleAndDate(
                        title!!,
                        "${date.year}-${date.month}-${date.day}"
                    )
                    if (tempHabit == null) {  //없으면
                        var temp = helper.selectOneHabitByTitle(title)
                        temp!!.calendar_date = "${date.year}-${date.month}-${date.day}"
                        habitValue = 0
                        temp!!.calendar_status++
                        helper.insertHabit(temp)

                    } else { //있으면
                        habitValue = tempHabit!!.calendar_status
                        tempHabit!!.calendar_status++
                        helper.updateHabit(tempHabit)
                    }
                    calendar.addDecorator(EventDecorator(date, habitValue))
                }
            }
        })

    }
    fun init(){
        showCalendarImage = findViewById(R.id.show_calendar_image)
        title = intent.getStringExtra("itemTitle")!!
        habitImage(title)

        calendar = findViewById(R.id.calendar)
        calendar.setHeaderTextAppearance(R.style.Calendar)
        calendar.setWeekDayTextAppearance(R.style.Calendar)
        calendar.setDateTextAppearance(R.style.Calendar)

        alarmTimeEditButton = findViewById(R.id.alarm_time_edit_button)
        noDisturbTimeButton = findViewById(R.id.no_disturb_time_edit_button)

        habitList = helper.selectHabit()

        initDecorate(title)
        calendar.invalidateDecorators()

    }
    fun initDecorate(title : String){
        var list : MutableList<habit_item> = mutableListOf<habit_item>()
        list = helper.selectHabitListByTitle(title)
        var year : Int
        var month : Int
        var day : Int
        for (item in list){
            year = dateToYear(item.calendar_date.toString())
            month = dateToMonth(item.calendar_date.toString())
            day = dateToDay(item.calendar_date.toString())
            var tempCalendarDay = CalendarDay(year, month, day)
            calendar.addDecorator(EventDecorator(tempCalendarDay,item.calendar_status -1) )
            calendar.invalidateDecorators()
        }
    }
    fun dateToYear(date : String):Int{
        var res = ""
        var ind = 0
        for(i in 0..date.length-1){
            if(date.get(i).equals('-')){
                res = date.substring(0,i)
                ind = i
                break
            }
        }
        return Integer.parseInt(res)
    }
    fun dateToMonth(date : String):Int{
        var res = ""
        var flag = 0

        for(i in 0..date.length-1){
            if(date.get(i).equals('-') && flag != 0){
                res = date.substring(flag,i)
                break
            }
            else if(date.get(i).equals('-')){
                flag = i+1
            }
        }
        return Integer.parseInt(res)
    }
    fun dateToDay(date : String):Int{
        var res = ""
        var count = 0
        for(i in 0..date.length-1){
            if(date.get(i).equals('-')){
                count++
            }
            if(count==2){
                res = date.substring(i+1, date.length)
                break
            }
        }
        return Integer.parseInt(res)
    }
    fun listen(){
        alarmTimeEditButton.setOnClickListener(this)
        noDisturbTimeButton.setOnClickListener(this)
    }
    fun habitImage(str : String){
        var tempHabit = helper.selectOneHabitByTitle(str)
        showCalendarImage.setImageResource(tempHabit!!.habit_image)
    }
    fun func(){
        /*var mAlarmManager = mainActivity.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        var intent2 = Intent(mainActivity, NotificationReceiver::class.java)

        var manager = mainActivity.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
        var builder : NotificationCompat.Builder? = null
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            if(manager.getNotificationChannel("CHANNEL_ID2") == null){
                manager.createNotificationChannel(NotificationChannel(CHANNEL_ID2, CHANNEL_NAME2, NotificationManager.IMPORTANCE_LOW))
                builder = NotificationCompat.Builder(mainActivity, CHANNEL_ID2)
            }

        }
        else{
            builder = NotificationCompat.Builder(mainActivity,"CHANNEL_ID2")
        }
        var intent = Intent(mainActivity, MainActivity::class.java)
        var pendingIntent = PendingIntent.getActivity(mainActivity, 101, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        builder!!.setContentTitle("간단 알림")
        builder!!.setContentText("알림메시지~")
        builder!!.setSmallIcon(android.R.drawable.ic_menu_view)
        builder.setAutoCancel(true)
        builder.setContentIntent(pendingIntent)
        var vibrator = mainActivity.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(100)

        mAlarmManager.set(
            AlarmManager.RTC_WAKEUP, 5000,
        )
        var noti = builder.build()
        mainActivity!!.handler.postDelayed(Runnable {
            manager.notify(2,noti)
        },5000)*/

        var alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        var intent = Intent(this, NotificationReceiver::class.java)
        var pendingIntent = PendingIntent.getBroadcast(this, NotificationReceiver.NOTIFICATION_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+10000,pendingIntent)
        Log.d("###############33","func")

    }

    override fun onClick(v: View?) {
        var id = v?.id
        when(id){
            R.id.alarm_time_edit_button->{
                var next = HabitAlarm()
                var trans = supportFragmentManager.beginTransaction()
                trans.add(R.id.frame_layout2, next)
                trans.addToBackStack("show Calendar")

                trans.commit()

            }
            R.id.no_disturb_time_edit_button->{
                var next = HabitNoDisturbTime()
                var trans = supportFragmentManager.beginTransaction()
                trans.add(R.id.frame_layout2, next)
                trans.addToBackStack("show Calendar")
                trans.commit()

            }
        }
    }
    fun makeToast(context: Context, str:String){
        var toast = Toast.makeText(context,"${str.toString()}", Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER_VERTICAL,0,0)
        toast.show()
        handler.postDelayed(Runnable {
            toast.cancel()
        },500)
    }



}