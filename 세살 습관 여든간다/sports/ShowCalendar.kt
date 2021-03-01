package org.techtown.sports

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.prolificinteractive.materialcalendarview.*

class ShowCalendar : AppCompatActivity(),View.OnClickListener {
    lateinit var calendar : MaterialCalendarView
    var helper = SqliteHelper(this as Context, "habit",1)
    var handler = Handler(Looper.getMainLooper())
    var main = this
    var habitValue =0
    lateinit var backButton : ImageView
    lateinit var alarmTimeEditButton : Button
    lateinit var noDisturbTimeButton : Button
    var dateList : MutableList<CalendarDay> = mutableListOf<CalendarDay>()
    var habitList : MutableList<habit_item> = mutableListOf<habit_item>()
    lateinit var calendarTitle : TextView
    lateinit var habitTitle : String
    lateinit var showCalendarImage : ImageView
    lateinit var settingButton : ImageView
    lateinit var animation_dog : ImageView
    lateinit var animation_man : ImageView
    lateinit var anim : AnimationDrawable
    lateinit var anim2 : AnimationDrawable
    var list =arrayOf<String>("홈으로","통계")


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
                if((date.year > CalendarDay.today().year) || ((date.year == CalendarDay.today().year)&&(date.month > CalendarDay.today().month))||
                        (date.year == CalendarDay.today().year) && (date.month == CalendarDay.today().month)&&(date.day > CalendarDay.today().day)){
                    main.makeToast(main,"미래 날짜 입력 불가!!")
                }
                else {
                    /*var next = Memo(date, habitTitle)
                    var trans = supportFragmentManager.beginTransaction()
                    trans.add(R.id.frame_layout2, next)
                    //trans.hide(this)
                    trans.addToBackStack("Show Calendar")
                    trans.commit()*/
                    var intent = Intent(main,MemoActivity::class.java )
                    intent.putExtra("habitTitle",habitTitle)
                    intent.putExtra("date","${date.year}-${date.month}-${date.day}")
                    Log.d("!@!@#@!#@!#!@","!!!   send~~~")
                    calendar.invalidateDecorators()
                    startActivity(intent)
                    //dateList.add(date)

                }
            }

        })
        calendar.addDecorator(MemoDecorator(dateList))

        calendar.setOnDateChangedListener(object : OnDateSelectedListener {
            override fun onDateSelected(widget: MaterialCalendarView, date: CalendarDay, selected: Boolean) {

                if((date.year > CalendarDay.today().year) || ((date.year == CalendarDay.today().year)&&(date.month > CalendarDay.today().month))||
                        (date.year == CalendarDay.today().year) && (date.month == CalendarDay.today().month)&&(date.day > CalendarDay.today().day)){
                    main.makeToast(main,"미래 날짜 입력 불가!!")
                }
                else {
                    var tempHabit = helper.selectHabitByTitleAndDate(
                        habitTitle!!,
                        "${date.year}-${date.month}-${date.day}"
                    )
                    if (tempHabit == null) {  //없으면
                        var temp = helper.selectUnitHabitByTitle(habitTitle)
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
        backButton.setOnClickListener{
            onBackPressed()
        }

    }
    fun init(){
        showCalendarImage = findViewById(R.id.show_calendar_image)
        habitTitle = intent.getStringExtra("itemTitle")!!
        habitImage(habitTitle)
        backButton = findViewById(R.id.back_button)
        calendar = findViewById(R.id.calendar)
        calendar.setHeaderTextAppearance(R.style.Calendar)
        calendar.setWeekDayTextAppearance(R.style.Calendar)
        calendar.setDateTextAppearance(R.style.Calendar)
        calendarTitle = findViewById(R.id.calendar_title)
        calendarTitle.setText("${habitTitle.toString()}")
        alarmTimeEditButton = findViewById(R.id.alarm_time_edit_button)
        noDisturbTimeButton = findViewById(R.id.no_disturb_time_edit_button)
        settingButton = findViewById(R.id.calendar_setting_btn)

        animation_dog = findViewById(R.id.show_animation_dog)
        animation_man = findViewById(R.id.show_animation_man)
        Log.d("!@#!@#!@#!@#!@","112312  ${animation_dog.drawable}   ${animation_man.drawable}")
        anim = animation_dog.drawable as AnimationDrawable
        anim2 = animation_man.drawable as AnimationDrawable
        habitList = helper.selectHabit()

        anim.start()
        anim2.start()
        initDecorate(habitTitle)
        initDecorateMemo(habitTitle)
        calendar.invalidateDecorators()
        //func()

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
            //calendar.invalidateDecorators()
        }
    }
    fun initDecorateMemo(title : String){
        var list = mutableListOf<habit_item>()
        list = helper.selectHabitListByTitle(title)
        var resList = mutableListOf<CalendarDay>()
        var year : Int
        var month : Int
        var day : Int
        for(item in list){
            if(item.has_memo == 1) {
                year = dateToYear(item.calendar_date.toString())
                month = dateToMonth(item.calendar_date.toString())
                day = dateToDay(item.calendar_date.toString())
                var tempCalendarDay = CalendarDay(year, month, day)
                resList.add(tempCalendarDay)
                //calendar.addDecorator(MemoDecorator(tempCalendarDay))
            }

        }
        calendar.addDecorator(MemoDecorator(resList))
        //calendar.invalidateDecorators()
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
        settingButton.setOnClickListener(this)
    }
    fun habitImage(str : String){
        var tempHabit = helper.selectUnitHabitByTitle(str)
        if(tempHabit!!.habit_image.equals("")){
            tempHabit.habit_image = R.drawable.default_image
        }
        showCalendarImage.setImageResource(tempHabit!!.habit_image)
    }
    fun func(channelId : String, channelName : String){
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
        var temp = 1000 * 60
        var alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        var intent = Intent(this, NotificationReceiver::class.java)
        intent.putExtra("channel_id",channelId)
        intent.putExtra("channel_name",channelName)
        var pendingIntent = PendingIntent.getBroadcast(this, NotificationReceiver.NOTIFICATION_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        //alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+temp,pendingIntent)
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),temp.toLong(),pendingIntent)


    }

    override fun onClick(v: View?) {
        var id = v?.id
        when(id){
            R.id.alarm_time_edit_button->{

                var next = HabitAlarm(habitTitle)
                var trans = supportFragmentManager.beginTransaction()
                trans.add(R.id.frame_layout2, next)
                trans.addToBackStack("show Calendar")

                trans.commit()

            }
            R.id.no_disturb_time_edit_button->{
                anim.stop()
                var next = HabitNoDisturbTime(habitTitle)
                var trans = supportFragmentManager.beginTransaction()
                trans.add(R.id.frame_layout2, next)
                trans.addToBackStack("show Calendar")
                trans.commit()

            }
            R.id.calendar_setting_btn->{
                var position = 0
                var alertDialog = AlertDialog.Builder(this as Context).setTitle("").setSingleChoiceItems(list, -1,
                    DialogInterface.OnClickListener{
                            dialog, which -> position = which
                    })
                    .setPositiveButton("설정", DialogInterface.OnClickListener{
                            dialog, which ->
                        if(position == 0){
                            var intent = Intent(this, MainActivity::class.java)
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            finish()
                            startActivity(intent)
                        }
                        else if(position == 1){
                            var next = Statistics(habitTitle)
                            var trans = main.supportFragmentManager.beginTransaction()
                            trans.addToBackStack("ShowCalendar")
                            trans.add(R.id.frame_layout2,next)
                            trans.commit()
                        }

                    })
                    .setNegativeButton("닫기",null)
                alertDialog.show()
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
    fun upKeyboard(context : Context, editText: EditText){
        var IMM = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        IMM.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }
    fun downKeyboard(context : Context, editText : EditText){
        var IMM = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        IMM.hideSoftInputFromWindow(editText.windowToken,0)
    }



}