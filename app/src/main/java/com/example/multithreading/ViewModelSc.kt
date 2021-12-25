package com.example.multithreading

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class ViewModelSc : ViewModel() {

    private val img = "https://i.pinimg.com/564x/97/e2/8f/97e28f089c7b4438d458dfd5b51245e2.jpg"
    private val newUrl = URL(img)
    val btData = MutableLiveData<Bitmap>()

    fun load() {
        viewModelScope.launch(Dispatchers.IO) {
            val bitmap = BitmapFactory.decodeStream(newUrl.openConnection().getInputStream())
            withContext(Dispatchers.Main) {
                btData.value = bitmap
            }
        }
    }
}