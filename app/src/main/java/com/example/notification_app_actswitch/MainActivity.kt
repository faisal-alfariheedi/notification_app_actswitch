package com.example.notification_app_actswitch

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    private val channelId = "myapp.notifications"
    private val description = "Notification App Example"
    var anynumber = 1
    lateinit var tvTime: TextView
    lateinit var nm: NotificationManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var v=findViewById<Button>(R.id.button)
        tvTime=findViewById<TextView>(R.id.textView2)
        nm = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        var time = object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tvTime.text = "Time: ${millisUntilFinished / 1000}"

            }

            override fun onFinish() {
                tvTime.text = "Time: --"
                noty(nm)
            }
        }
        v.setOnClickListener{
            time.start()
        }



    }

    fun noty(nm: NotificationManager) {
        val intent = Intent(this, Notif_act::class.java)
        val pendingIntent =
            PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        var builder: Notification.Builder

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var notificationChannel = NotificationChannel(
                channelId,
                description,
                NotificationManager.IMPORTANCE_HIGH
            )
            nm.createNotificationChannel(notificationChannel)
            builder = Notification.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        this.resources,
                        R.drawable.ic_launcher_foreground
                    )
                )
                .setContentIntent(pendingIntent)
                .setContentTitle("your eggs are readyyyyyyyyyyyyyyyyyyy")
//                    .setContentText("Hello")
        } else {
            builder = Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        this.resources,
                        R.drawable.ic_launcher_background
                    )
                )
                .setContentIntent(pendingIntent)
                .setContentTitle("your eggs are readyyyyyyyyyyyyyyyyyyy")
//                    .setContentText("Hello")
        }
        nm.notify(anynumber, builder.build())

    }
}