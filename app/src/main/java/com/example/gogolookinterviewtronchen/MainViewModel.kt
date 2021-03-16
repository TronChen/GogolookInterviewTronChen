package com.example.gogolookinterviewtronchen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gogolookinterviewtronchen.data.source.GogolookRepository
import com.example.gogolookinterviewtronchen.util.CurrentFragmentType

class MainViewModel(
    private val repository: GogolookRepository
): ViewModel() {

    val currentFragmentType = MutableLiveData<CurrentFragmentType>()

}