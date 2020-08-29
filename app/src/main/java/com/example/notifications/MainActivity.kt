package com.example.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    val channelID="channelID"
    val channelName="channelName"
    val notificationID=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationChannel()

        val intent=Intent(this,MainActivity::class.java)
        val pendingIntent=TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT)
        }
        val notification=NotificationCompat.Builder(this,channelID)
                .setContentTitle("Awesome Notification")
                .setContentText("Drink Water")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_star_foreground)
                .setPriority(NotificationManager.IMPORTANCE_HIGH)
                .build()
        val notificationManager=NotificationManagerCompat.from(this)
        notificationManager.notify(notificationID,notification)
    }

    fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val channel=NotificationChannel(channelID,channelName,NotificationManager.IMPORTANCE_HIGH).apply {
                lightColor=Color.RED
                enableLights(true)
            }
            val manager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
}