package com.example.multithreading

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.example.multithreading.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

class TaskWithPicCor : AppCompatActivity() {
    private val img = "https://i.pinimg.com/564x/97/e2/8f/97e28f089c7b4438d458dfd5b51245e2.jpg"
    private lateinit var bitmap: Bitmap
    private var state = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val newUrl = URL(img)
        val picture = binding.image
        setContentView(binding.root)

        lifecycleScope.launchWhenStarted {
            withContext(Dispatchers.IO) {
                bitmap = BitmapFactory.decodeStream(newUrl.openConnection().getInputStream())
            }
        }

        binding.button.setOnClickListener {
            if (state) {
                picture.setImageBitmap(bitmap)
                binding.button.text = getString(R.string.clear)
                state = false
            } else {
                picture.setImageBitmap(null)
                binding.button.text = getString(R.string.download_picture)
                state = true
            }
        }

    }
}

