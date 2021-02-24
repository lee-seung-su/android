package org.techtown.sports

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.RemoteViews

/**
 * Implementation of App Widget functionality.
 */
var ACTION_CLICK = "android.action.BUTTON_CLICK"

class weeee : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {


        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val widgetText = context.getString(R.string.appwidget_text)
    // Construct the RemoteViews object
    val views = RemoteViews(context.packageName, R.layout.weeee)

    var intent = Intent(Intent.ACTION_MAIN)
    intent.addCategory(Intent.CATEGORY_LAUNCHER)
    intent.component = ComponentName(context,MainActivity::class.java)
    var pi = PendingIntent.getActivity(context,0,intent,0)
    views.setOnClickPendingIntent(R.id.simple_btn,pi)

    views.setOnClickPendingIntent(R.id.simple_btn11,pi)
    views.setOnClickPendingIntent(R.id.simple_btn22,pi)
    views.setOnClickPendingIntent(R.id.simple_btn33,pi)
    views.setOnClickPendingIntent(R.id.simple_btn44,pi)
    views.setOnClickPendingIntent(R.id.simple_btn55,pi)



    appWidgetManager.updateAppWidget(appWidgetId, views)
}
