package com.example.gogolookinterviewtronchen.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gogolookinterviewtronchen.data.source.GogolookRepository
import com.example.gogolookinterviewtronchen.imageResult.ImageResultViewModel

@Suppress("UNCHECKED_CAST")
class InputStringViewModelFactory(
    private val repository: GogolookRepository,
    private val inputString: String?
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ImageResultViewModel::class.java)) {
            return inputString?.let {
                ImageResultViewModel(
                    repository,
                    it
                )
            } as T
        }


        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}