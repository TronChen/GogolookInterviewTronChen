package com.example.gogolookinterviewtronchen.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gogolookinterviewtronchen.data.History
import com.example.gogolookinterviewtronchen.data.source.GogolookRepository
import com.example.gogolookinterviewtronchen.imageResult.ImageResultViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*

class SearchViewModel (private val repository: GogolookRepository
) : ViewModel()  {

    val inputString = MutableLiveData<String>()

    var histories : LiveData<List<History>> = repository.getAllHistory()

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        inputString.value = ""
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun navigateToImageResult(){
        inputString.value = ""
    }

    fun clearHistory() {
        coroutineScope.launch {
            repository.clearHistory()
        }
    }

    fun addHistory(history: History){
        coroutineScope.launch {
            repository.insertHistory(history)
        }
    }

    fun updateHistory(history: History) {
        history.date = Calendar.getInstance().time.time
        coroutineScope.launch {
            Log.d("AfterUpdateSearchDate", history.date.toString())
            repository.updateHistory(history)
        }
    }
}