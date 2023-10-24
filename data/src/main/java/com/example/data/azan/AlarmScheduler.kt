package com.example.data.azan

import android.app.Application
import android.app.PendingIntent

interface AlarmScheduler {
    fun schedule(alarmTime: Long, pendingIntent: PendingIntent, application: Application)
    fun scheduleInExact(alarmTime: Long, pendingIntent: PendingIntent, application: Application)
    fun cancel(pendingIntent: PendingIntent, application: Application)
}