package com.example.multithreading

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.example.multithreading.databinding.ActivityMainBinding
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

class TaskWithPic : AppCompatActivity() {

    private val img = "https://i.pinimg.com/564x/97/e2/8f/97e28f089c7b4438d458dfd5b51245e2.jpg"
    private lateinit var exc: Future<*>
    private val bitmap: MutableLiveData<Bitmap> = MutableLiveData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newUrl = URL(img)
        val picture = binding.image

        binding.button.setOnClickListener {

            if (bitmap.value == null) {
                loadPic(newUrl)
            } else {
                bitmap.value = null
            }
        }

        bitmap.observe(this) {
            picture.setImageBitmap(it)
            if (it != null) {
                binding.button.text = getString(R.string.clear)
            } else {
                binding.button.text = getString(R.string.download_picture)
            }
        }

    }

    private fun loadPic(url: URL) {
        exc = (application as ExecService).executor.submit {
            bitmap.postValue(BitmapFactory.decodeStream(url.openConnection().getInputStream()))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        exc.cancel(true)
    }

}