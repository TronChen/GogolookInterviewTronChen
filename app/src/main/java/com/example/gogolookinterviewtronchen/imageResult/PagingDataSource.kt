package com.example.gogolookinterviewtronchen.imageResult

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import app.appworks.school.stylish.network.LoadApiStatus
import com.example.gogolookinterviewtronchen.GogolookApplication
import com.example.gogolookinterviewtronchen.R
import com.example.gogolookinterviewtronchen.data.AppResult
import com.example.gogolookinterviewtronchen.data.SearchImage
import com.example.gogolookinterviewtronchen.util.Util.getString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PagingDataSource(val inputString: String) : PageKeyedDataSource<String, SearchImage>() {

    // init load status for observe

    var page = 1

    private val _statusInitialLoad = MutableLiveData<LoadApiStatus>()

    val statusInitialLoad: LiveData<LoadApiStatus>
        get() = _statusInitialLoad

    // init load error for observe
    private val _errorInitialLoad = MutableLiveData<String>()

    val errorInitialLoad: LiveData<String>
        get() = _errorInitialLoad

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(Job() + Dispatchers.Main)

    /**
     * Initial load api
     */
    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, SearchImage>) {
//        Logger.d("[${type.value}] loadInitial") // open it if you want to observe status

        coroutineScope.launch {

            _statusInitialLoad.value = LoadApiStatus.LOADING

            val result = GogolookApplication.INSTANCE.repository
                .getSearchResult(inputString = inputString , page = page.toString())
            when (result) {
                is AppResult.Success -> {
                    _errorInitialLoad.value = null
                    _statusInitialLoad.value = LoadApiStatus.DONE
//                    Log.d("Tron","[${type.value}] loadInitial.result=${result.data.products}") // open it if you want to observe status
                    Log.d("Tron","[] loadInitial.paging=${page}") // open it if you want to observe status
                    result.data.hits?.let { callback.onResult(it, null, page ++.toString()) }
                }
                is AppResult.Fail -> {
                    _errorInitialLoad.value = result.error
                    _statusInitialLoad.value = LoadApiStatus.ERROR
                }
                is AppResult.Error -> {
                    _errorInitialLoad.value = result.exception.toString()
                    _statusInitialLoad.value = LoadApiStatus.ERROR
                }
                else -> {
                    _errorInitialLoad.value = getString(R.string.you_know_nothing)
                    _statusInitialLoad.value = LoadApiStatus.ERROR
                }
            }
        }
    }

    /**
     * After initial load, it will according to paging key to load api
     */
    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, SearchImage>) {
//        Logger.d("[${type.value}] loadAfter.key=${params.key}") // open it if you want to observe status

        coroutineScope.launch {
            val result = GogolookApplication.INSTANCE.repository
                .getSearchResult(inputString = inputString, page = page.toString())
            when (result) {
                is AppResult.Success -> {
//                    Logger.d("[${type.value}] loadAfter.result=${result.data}") // open it if you want to observe status
//                    Logger.d("[${type.value}] loadAfter.paging=${result.data.paging}") // // open it if you want to observe status
                    Log.d("Tron","[] loadAfter.paging=${page}") // open it if you want to observe status
                    result.data.hits?.let { callback.onResult(it, page ++ .toString()) }
                }
            }
        }
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, SearchImage>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}