package com.example.gogolookinterviewtronchen.imageR

import android.provider.Settings.Global.getString
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.appworks.school.stylish.network.LoadApiStatus
import com.example.gogolookinterviewtronchen.R
import com.example.gogolookinterviewtronchen.data.AppResult
import com.example.gogolookinterviewtronchen.data.SearchResult
import com.example.gogolookinterviewtronchen.data.source.GogolookRepository
import com.example.gogolookinterviewtronchen.data.source.remote.GogolookRemoteDataSource.getSearchResult
import com.example.gogolookinterviewtronchen.util.Util
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ImageRViewModel(
    private val repository: GogolookRepository,
    private val inputString : String
) : ViewModel () {

    var searchString : String? = null

    val searchItem = MutableLiveData<SearchResult>()

    // status: The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<LoadApiStatus>()

    val status: LiveData<LoadApiStatus>
        get() = _status

    // error: The internal MutableLiveData that stores the error of the most recent request
    private val _error = MutableLiveData<String>()

    val error: LiveData<String>
        get() = _error

    // status for the loading icon of swl
    private val _refreshStatus = MutableLiveData<Boolean>()

    val refreshStatus: LiveData<Boolean>
        get() = _refreshStatus


    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    /**
     * When the [ViewModel] is finished, we cancel our coroutine [viewModelJob], which tells the
     * Retrofit service to stop.
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


    init {
        getSearchResult(true,inputString)
    }

    private fun getSearchResult(isInitial: Boolean = false , inputString: String) {

        coroutineScope.launch {

            if (isInitial) _status.value = LoadApiStatus.LOADING

            val result = repository.getSearchResult(inputString)

            searchItem.value = when (result) {
                is AppResult.Success -> {
                    _error.value = null
                    if (isInitial) _status.value = LoadApiStatus.DONE
                    result.data
                }
                is AppResult.Fail -> {
                    _error.value = result.error
                    if (isInitial) _status.value = LoadApiStatus.ERROR
                    null
                }
                is AppResult.Error -> {
                    _error.value = result.exception.toString()
                    if (isInitial) _status.value = LoadApiStatus.ERROR
                    null
                }
                else -> {
                    _error.value = Util.getString(R.string.you_know_nothing)
                    if (isInitial) _status.value = LoadApiStatus.ERROR
                    null
                }
            }
            _refreshStatus.value = false
        }
    }

}