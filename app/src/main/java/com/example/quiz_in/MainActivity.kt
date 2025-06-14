package com.example.quiz_in

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import android.media.MediaPlayer
import androidx.core.view.WindowInsetsCompat
import com.example.quiz_in.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //    definisikan binding
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAbout.setOnClickListener {
            // membuka aboutactivity
            val intent = Intent(this, AboutActivity::class.java)
            startActivity(intent)
        }

        binding.btnStart.setOnClickListener {
            // membuka soal
            val intent = Intent(this, SoalActivity::class.java)
            startActivity(intent)
        }

        binding.btnOption.setOnClickListener {
            // membuka soal
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


    }
    override fun onResume() {
        super.onResume()
        val prefs = getSharedPreferences("music_prefs", Context.MODE_PRIVATE)
        val isMusicOn = prefs.getBoolean("music_on", true)

        if (isMusicOn) {
            startService(Intent(this, musicService::class.java))
        } else {
            stopService(Intent(this, musicService::class.java))
        }
    }

}