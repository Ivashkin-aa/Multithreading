package com.example.multithreading

import android.app.Application
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors.newSingleThreadExecutor

class ExecService : Application() {
    val executor: ExecutorService = newSingleThreadExecutor()
}