package com.example.gogolookinterviewtronchen.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gogolookinterviewtronchen.data.source.GogolookRepository
import com.example.gogolookinterviewtronchen.imageR.ImageRViewModel

@Suppress("UNCHECKED_CAST")
class InputStringViewModelFactory(
    private val repository: GogolookRepository,
    private val inputString: String?
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ImageRViewModel::class.java)) {
            return inputString?.let {
                ImageRViewModel(
                    repository,
                    it
                )
            } as T
        }


        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}