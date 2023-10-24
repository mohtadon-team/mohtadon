package com.example.data.azan

import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi

class AlarmScheduling() : AlarmScheduler {


    @RequiresApi(Build.VERSION_CODES.M)
    override fun schedule(
        alarmTime: Long, pendingIntent: PendingIntent, application: Application
    ) {
        val alarmManager = application.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP, alarmTime, pendingIntent
        )
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun scheduleInExact(
        alarmTime: Long, pendingIntent: PendingIntent, application: Application
    ) {
        val alarmManager = application.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP, alarmTime, pendingIntent
        )
    }

    override fun cancel(pendingIntent: PendingIntent, application: Application) {
        val alarmManager = application.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        alarmManager.cancel(pendingIntent)
        pendingIntent.cancel()

    }
}