package com.example.quiz_in

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

class musicService : Service() {
    private var mediaPlayer: MediaPlayer? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Kalau mediaPlayer belum dibuat
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.bgm).apply {
                // Biar musik diputar terus menerus
                isLooping = true
                start()
            }
        }
        // Kalau mediaPlayer sudah ada tapi sedang tidak diputar, mulai putar musiknya lagi
        else if (mediaPlayer?.isPlaying == false) {
            mediaPlayer?.start()
        }

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        // Kalau mediaPlayer ada, dan musik sedang diputar, hentikan dulu
        mediaPlayer?.run {
            if (isPlaying) stop()
            release()
        }
        // Kosongkan mediaPlayer
        mediaPlayer = null

        // service dihancurkan
        super.onDestroy()
    }


    override fun onBind(intent: Intent?): IBinder? = null
}

