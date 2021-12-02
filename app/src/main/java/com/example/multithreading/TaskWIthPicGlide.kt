package com.example.multithreading

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.multithreading.databinding.ActivityMainBinding
import java.net.URL
import kotlin.properties.Delegates

class TaskWIthPicGlide: AppCompatActivity() {

    private val img = "https://i.pinimg.com/564x/97/e2/8f/97e28f089c7b4438d458dfd5b51245e2.jpg"
    private var state = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val picture = binding.image
        val newUrl = URL(img)
        setContentView(binding.root)

        binding.button.setOnClickListener {


            if (state) {
                Glide.with(this).load(newUrl)
                    .apply(RequestOptions().override(1600, 1200))
                    .into(picture)
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