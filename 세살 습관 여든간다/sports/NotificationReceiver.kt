package org.techtown.sports

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Vibrator
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class NotificationReceiver : BroadcastReceiver() {
    companion object {
        const val TAG = "AlarmReceiver"
        const val NOTIFICATION_ID = 0
        const val PRIMARY_CHANNEL_ID = "primary_notification_channel"
    }
    var CHANNEL_ID : String=""
    var CHANNEL_NAME : String=""
    init{
        //CHANNEL_ID = intent.getStringExtra("channel_id")

    }
    var CHANNEL_ID2 : String = "channel2"
    var CHANNEL_NAME2 = "Channel2"
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("###############33","onReceive")
        var manager = context!!.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
        var builder : NotificationCompat.Builder? = null
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            if(manager.getNotificationChannel("CHANNEL_ID2") == null){
                manager.createNotificationChannel(NotificationChannel(CHANNEL_ID2, CHANNEL_NAME2, NotificationManager.IMPORTANCE_LOW))
                builder = NotificationCompat.Builder(context, CHANNEL_ID2)
            }

        }
        else{
            builder = NotificationCompat.Builder(context,"CHANNEL_ID2")
        }
        var intent = Intent(context, MainActivity::class.java)
        var pendingIntent = PendingIntent.getActivity(context, 101, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        builder!!.setContentTitle("간단 알림")
        builder.setContentText("알림메시지~")
        builder.setSmallIcon(android.R.drawable.ic_menu_view)
        builder.setAutoCancel(true)
        builder.setContentIntent(pendingIntent)
        var vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(100)

        var noti = builder.build()
        manager.notify(2,noti)
    }


}
