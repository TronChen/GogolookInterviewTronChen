package com.example.gogolookinterviewtronchen.ext

import androidx.fragment.app.Fragment
import com.example.gogolookinterviewtronchen.GogolookApplication
import com.example.gogolookinterviewtronchen.factory.ViewModelFactory

fun Fragment.getVmFactory(): ViewModelFactory {
    val repository = (requireContext().applicationContext as GogolookApplication).repository
    return ViewModelFactory(repository)
}

