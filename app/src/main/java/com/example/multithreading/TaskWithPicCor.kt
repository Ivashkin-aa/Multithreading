package com.example.multithreading

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.multithreading.databinding.ActivityMainBinding

class TaskWithPicCor : AppCompatActivity() {

    private val viewModel: ViewModelSc by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val picture = binding.image
        setContentView(binding.root)
        binding.button.setOnClickListener {
            if (viewModel.btData.value == null) {
                viewModel.load()
            } else {
                viewModel.btData.value = null
            }
        }
        viewModel.btData.observe(this) {
            picture.setImageBitmap(it)
            if (it != null) {
                binding.button.text = getString(R.string.clear)
            } else {
                binding.button.text = getString(R.string.download_picture)
            }
        }
    }
}


