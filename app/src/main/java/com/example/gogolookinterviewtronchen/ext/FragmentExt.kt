package com.example.gogolookinterviewtronchen.ext

import androidx.fragment.app.Fragment
import com.example.gogolookinterviewtronchen.GogolookApplication
import com.example.gogolookinterviewtronchen.factory.InputStringViewModelFactory
import com.example.gogolookinterviewtronchen.factory.ViewModelFactory

fun Fragment.getVmFactory(): ViewModelFactory {
    val repository = (requireContext().applicationContext as GogolookApplication).repository
    return ViewModelFactory(repository)
}

fun Fragment.getVmFactory(inputString : String): InputStringViewModelFactory {
    val repository = (requireContext().applicationContext as GogolookApplication).repository
    return InputStringViewModelFactory(repository , inputString)
}

