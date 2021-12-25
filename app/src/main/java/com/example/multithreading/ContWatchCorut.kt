package com.example.multithreading

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive


class ContWatchCorut : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private val shrStg = "settings"
    private val keyCounter = "counter"
    private var secondsElapsed: Int = 0
    private lateinit var textSecondsElapsed: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.continue_watch)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
        sharedPreferences = getSharedPreferences(shrStg, MODE_PRIVATE)
        secondsElapsed = sharedPreferences.getInt(keyCounter, 0)
        lifecycleScope.launchWhenStarted {
            while (isActive) {
                Log.i("Coroutines", "running")
                delay(1000)
                secondsElapsed++
                textSecondsElapsed.text = getString(R.string.seconds, secondsElapsed)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        val edit = sharedPreferences.edit()
        edit.putInt(keyCounter, secondsElapsed)
        edit.apply()
    }

}
