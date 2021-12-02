package com.example.multithreading

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ContWatchExec : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private val shrStg = "settings"
    private val keyCounter = "counter"
    private var secondsElapsed: Int = 0
    private lateinit var textSecondsElapsed: TextView


    private lateinit var backgroundThread: ExecutorService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.continue_watch)
        textSecondsElapsed = findViewById(R.id.textSecondsElapsed)
        sharedPreferences = getSharedPreferences(shrStg, MODE_PRIVATE)
        secondsElapsed = sharedPreferences.getInt(keyCounter, 0)

    }

    override fun onStop() {
        super.onStop()
        val edit = sharedPreferences.edit()
        edit.putInt(keyCounter, secondsElapsed)
        edit.apply()
        backgroundThread.shutdown()
        Log.i("EXEC", "stopped")
    }

    override fun onStart() {
        super.onStart()
        backgroundThread = Executors.newSingleThreadExecutor()
        backgroundThread.execute {
            while (!backgroundThread.isShutdown) {
                Log.i("EXEC", "running")
                Thread.sleep(1000)
                secondsElapsed++
                textSecondsElapsed.post {
                    textSecondsElapsed.text = getString(R.string.seconds, secondsElapsed)
                }
            }
        }
    }

}