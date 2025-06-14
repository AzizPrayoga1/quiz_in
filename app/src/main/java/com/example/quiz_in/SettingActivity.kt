package com.example.quiz_in

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quiz_in.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val prefs = getSharedPreferences("music_prefs", Context.MODE_PRIVATE)

        // Ambil nilai sebelumnya
        val isMusicOn = prefs.getBoolean("music_on", true)
        val isSfxOn = prefs.getBoolean("sfx_on", true)

        // Set awal checkbox-nya
        binding.cbsound.isChecked = isMusicOn
        binding.cbsfx.isChecked = isSfxOn

        // Listener musik
        binding.cbsound.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("music_on", isChecked).commit()
            if (isChecked) {
                startService(Intent(this, musicService::class.java))
            } else {
                stopService(Intent(this, musicService::class.java))
            }
        }

        // Listener SFX (jika kamu ingin menambahkan logika nanti)
        binding.cbsfx.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("sfx_on", isChecked).apply()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }override fun onPause() {
        super.onPause()
        val prefs = getSharedPreferences("music_prefs", Context.MODE_PRIVATE)
        prefs.edit()
            .putBoolean("music_on", binding.cbsound.isChecked)
            .putBoolean("sfx_on", binding.cbsfx.isChecked)
            .apply()

        android.util.Log.d("SettingActivity", "onPause: saved music_on = ${binding.cbsound.isChecked}")
    }

}
