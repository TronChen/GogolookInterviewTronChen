package com.example.gogolookinterviewtronchen.ext

import android.app.Activity
import android.view.Gravity
import android.widget.Toast
import com.example.gogolookinterviewtronchen.GogolookApplication
import com.example.gogolookinterviewtronchen.factory.ViewModelFactory

fun Activity.getVmFactory(): ViewModelFactory {
    val repository = (applicationContext as GogolookApplication).repository
    return ViewModelFactory(repository)
}

fun Activity?.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).apply {
        setGravity(Gravity.CENTER, 0, 0)
        show()
    }
}