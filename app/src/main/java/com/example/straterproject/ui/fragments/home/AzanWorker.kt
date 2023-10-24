package com.example.straterproject.ui.fragments.home

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.straterproject.R

class AzanWorker(
    private var context: Context, private var workerParameters: WorkerParameters
) : Worker(context, workerParameters) {
    private val CHANNEL_ID = "AZAN_CHANNEL"
    private val CHANNEL_NAME = "azan channel"


    override fun doWork(): Result {
//        val azanSound =
//            Uri.parse("android.resource://" + applicationContext.packageName + "/" + R.raw.azan)
//        val input: Data = inputData
//        val title: String = input.getString(SALAH_NAME)!!
//
//        sendNotification(title, "content", azanSound)

        Log.i("ahmed", "worker 2 ")

        var mediaPlayer = MediaPlayer.create(
            context.applicationContext, R.raw.azan
        ); // Change "your_audio_file" to your actual audio file name
        mediaPlayer.start()


        return Result.success()
    }

    fun sendNotification(title: String, content: String, sound: Uri) {

        val manager: NotificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        val notificationBuilder = createNotificationBuilder(title, content, sound)


        // this function if application run on android minimum O
        createNotificationChannel(manager, sound)

        manager.notify(0, notificationBuilder.build())
    }

    private fun createNotificationBuilder(
        title: String, content: String, sound: Uri
    ): NotificationCompat.Builder {

//        val intent = Intent(applicationContext, PrayersTimeFragment::class.java)
//        val pendingIntent = PendingIntent.getActivity(
//            applicationContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT
//        )

        val notificationBuilder = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher).setContentTitle(title)
            .setSound(sound).setDefaults(NotificationCompat.DEFAULT_SOUND).setContentText(content)
            .setAutoCancel(true).setPriority(NotificationCompat.PRIORITY_HIGH)
//            .addAction(
//                R.drawable.back, "تم", pendingIntent
//            )  // if click on button Replay in notification

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


}