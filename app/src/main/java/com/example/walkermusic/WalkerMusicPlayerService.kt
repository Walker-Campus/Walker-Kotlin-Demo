package com.example.walkermusic

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationBuilderWithBuilderAccessor
import androidx.core.app.NotificationCompat

class WalkerMusicPlayerService : Service() {

    override fun onCreate() {
        super.onCreate()
        Log.d("music","create executed!")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("music","destroy executed!")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("music","start command executed!")

        val pendingIntent =
            PendingIntent.getActivity(this, 0,
                Intent(this, MainActivity::class.java), 0)
        val notification =
            NotificationCompat.Builder(this, "normal")
            .setContentTitle("music is playing!")
            .setContentText("the music is your favourite.")
            .setSmallIcon(R.drawable.ic_play)
            .setContentIntent(pendingIntent)
            .build()
        val manager
                = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(1, notification)
        return super.onStartCommand(intent, flags, startId)
    }


    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    private val binder = PlayBinder()
    public class PlayBinder : Binder() {
        fun play() {
            Log.d("$this", "play!")
        }
        fun pause() {
            Log.d("$this", "pause!")
        }
        fun stop() {
            Log.d("$this", "stop!")
        }
    }
}