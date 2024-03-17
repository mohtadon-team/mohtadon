package com.mohtdon.utilities.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.mohtdon.data.utils.Constant.Companion.NOTIFICATION_CONTENT_KEY
import com.mohtdon.data.utils.Constant.Companion.NOTIFICATION_TITLE_KEY
import com.mohtdon.ui.home.HomeFragment

class AzanNotificationWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    private val CHANNEL_ID = "AZAN_CHANNEL"
    private val CHANNEL_NAME = "azan channel"

    private fun sendNotification(title: String, content: String, sound: Uri) {

        val manager: NotificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        val notificationBuilder = createNotificationBuilder(title, content, sound)


        createNotificationChannel(manager, sound)

        manager.notify(0, notificationBuilder.build())
    }

    private fun createNotificationBuilder(
        title: String, content: String, sound: Uri
    ): NotificationCompat.Builder {

        val intent = Intent(applicationContext, HomeFragment::class.java)
        val pendingIntent = PendingIntent.getActivity(
            applicationContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notificationBuilder = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
        notificationBuilder.setSmallIcon(com.mohtdon.mohtdon.R.drawable.checkbox_checked)
            .setContentTitle(title).setSound(sound).setDefaults(NotificationCompat.DEFAULT_SOUND)
            .setContentText(content).setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH).addAction(
                com.mohtdon.mohtdon.R.drawable.arrow_up, "تم", pendingIntent
            )

        return notificationBuilder

    }


    private fun createNotificationChannel(manager: NotificationManager, sound: Uri) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT
            )
            val audioAttributes =
                AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION).build()
            notificationChannel.setSound(sound, audioAttributes)
            manager.createNotificationChannel(notificationChannel)
        }
    }

    override fun doWork(): Result {
        val azanSound =
            Uri.parse("android.resource://" + applicationContext.packageName + "/" + com.mohtdon.mohtdon.R.raw.azan2)
        val input: Data = inputData
        val title: String = input.getString(NOTIFICATION_TITLE_KEY)!!
        val content: String = input.getString(NOTIFICATION_CONTENT_KEY)!!

        sendNotification(title, content, azanSound)
        return Result.success()
    }


}