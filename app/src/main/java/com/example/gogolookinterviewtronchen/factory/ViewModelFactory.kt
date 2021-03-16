package com.example.gogolookinterviewtronchen.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gogolookinterviewtronchen.MainViewModel
import com.example.gogolookinterviewtronchen.data.source.GogolookRepository
import com.example.gogolookinterviewtronchen.search.SearchViewModel


@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
    private val repository: GogolookRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(MainViewModel::class.java) ->
                    MainViewModel(repository)

                isAssignableFrom(SearchViewModel::class.java) ->
                    SearchViewModel(repository)


                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}