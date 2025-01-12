package com.example.readle

import ReadLeRepository
import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModelProvider
import com.example.readle.ui.ReadLeViewModel
import com.example.readle.ui.ReadLeViewModelFactory
import com.example.readle.ui.theme.ReadLeTheme

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: ReadLeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(MainActivity::class.java.simpleName, "onCreate()")
        super.onCreate(savedInstanceState)
        createNotificationChannel()
        enableEdgeToEdge()
        val repository = (application as ReadLeApplication).repository.readLeRepository
        val viewModelFactory = ReadLeViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ReadLeViewModel::class.java)


        setContent {
            ReadLeTheme(
                darkTheme = false
            ) {
                setContent {
                    ReadLeApp(viewModel = viewModel)
                }
            }
        }
    }


    override fun onRestart() {
        super.onRestart()
        Log.i(MainActivity::class.java.simpleName, "onRestart()")
    }
    override fun onStart() {
        super.onStart()
        Log.i(MainActivity::class.java.simpleName, "onStart()")
    }
    override fun onResume() {
        super.onResume()
        Log.i(MainActivity::class.java.simpleName, "onResume()")
    }
    override fun onPause() {
        super.onPause()
        Log.i(MainActivity::class.java.simpleName, "onPause()")
        sendPushNotification("Taking a break?", "Currently reading <book_name>")
    }

    override fun onStop() {
        super.onStop()
        Log.i(MainActivity::class.java.simpleName, "onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(MainActivity::class.java.simpleName, "onDestroy()")
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





