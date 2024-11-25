package com.example.readle

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.readle.ui.theme.ReadLeTheme
import com.example.readle.ui.Logger

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Logger().info("MainActivity", "onCreate()")
        super.onCreate(savedInstanceState)
        createNotificationChannel()
        enableEdgeToEdge()
        setContent {
            ReadLeTheme(
                darkTheme = false
            ) {
                ReadLeApp()
            }
        }
    }



    override fun onRestart() {
        super.onRestart()
        Logger().info("MainActivity", "onRestart()")
    }
    override fun onStart() {
        super.onStart()
        Logger().info("MainActivity", "onStart()")

    }
    override fun onResume() {
        super.onResume()
        Logger().info("MainActivity", "onResume()")
    }
    override fun onPause() {
        super.onPause()
        Logger().info("MainActivity", "onPause()")
        sendPushNotification("Taking a break?", "Currently reading <book_name>")
    }

    override fun onStop() {
        super.onStop()
        Logger().info("MainActivity", "onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Logger().info("MainActivity", "onDestroy()")
    }




    private val CHANNEL_ID = "close_app_notification"
    private val NOTIFICATION_ID = 1

    private fun sendPushNotification(title: String, description: String) {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            this, 0, intent, PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.icon)
            .setContentTitle(title)
            .setContentText(description)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        with(NotificationManagerCompat.from(this)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                requestPermissions(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 0)
            }
            if (ActivityCompat.checkSelfPermission(this@MainActivity, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                notify(NOTIFICATION_ID, notification)
                return
            }
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Taking a break?"
            val descriptionText = "Currently reading <book_name>"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

}





