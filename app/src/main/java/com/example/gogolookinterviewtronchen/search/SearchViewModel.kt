package com.example.gogolookinterviewtronchen.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gogolookinterviewtronchen.data.source.GogolookRepository

class SearchViewModel (private val repository: GogolookRepository
) : ViewModel()  {

    val inputString = MutableLiveData<String>()

}